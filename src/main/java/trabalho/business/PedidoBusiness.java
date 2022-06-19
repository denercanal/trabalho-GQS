package trabalho.business;

import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import trabalho.builder.Builder;
import trabalho.builder.CestaBasicaBuilder;
import trabalho.builder.CestaEconomicaBuilder;
import trabalho.builder.CestaTopBuilder;
import trabalho.dao.PedidoDAO;
import trabalho.dao.ProdutoDAO;
import trabalho.enums.TipoCestaEnum;
import trabalho.exception.DAOException;
import trabalho.exception.OperacaoInvalidaException;
import trabalho.exception.StateException;
import trabalho.model.Cesta;
import trabalho.model.Desconto;
import trabalho.model.Imposto;
import trabalho.model.ItemPedido;
import trabalho.model.Pedido;
import trabalho.state.AguardandoPagamentoState;
import trabalho.state.CanceladoPeloClienteState;
import trabalho.state.CanceladoPeloEstabelecimentoState;
import trabalho.state.ConfirmadoState;
import trabalho.state.EmRotaDeEntregaState;
import trabalho.state.EntregueState;
import trabalho.state.NovoState;
import trabalho.state.ProntoParaEntregaState;
import trabalho.state.ReembolsadoState;

public class PedidoBusiness {

	private static final int CEM = 100;
	private Logger LOGGER = LoggerFactory.getLogger( PedidoBusiness.class );

	private Pedido pedido;

	public PedidoBusiness() {
		this.pedido = new Pedido( this );
	}

	private Pedido getPedido() {
		return pedido;
	}

	public void incluirItemPedido( int idProduto, double quantidade ) throws OperacaoInvalidaException {
		if( !( pedido.getEstado() instanceof NovoState ) ) {
			operacaoInvalida();
		}
		incluirItemPedidoNaLista( idProduto, quantidade );
	}

	public void incluirCesta( TipoCestaEnum tipoCesta ) throws OperacaoInvalidaException {
		if( !( pedido.getEstado() instanceof NovoState ) ) {
			operacaoInvalida();
		}
		incluirCestaNoPedido( tipoCesta );
	}

	public void removerItemPedido( int idProduto, double quantidade ) throws OperacaoInvalidaException {
		if( !( pedido.getEstado() instanceof NovoState ) ) {
			operacaoInvalida();
		}
		removerItemPedidoDaLista( idProduto, quantidade );
	}

	public void concluirPedido() throws OperacaoInvalidaException, StateException {
		if( !( pedido.getEstado() instanceof NovoState ) ) {
			operacaoInvalida();
		}
		calcularValoresItensPedido();
		imprimirValoresPedido();
		adicionaPedido();
		pedido.setEstado( pedido.getEstado().concluirPedido() );
	}

	public void cancelarPedido() throws OperacaoInvalidaException, StateException {
		boolean isOperacaoValida = !( pedido.getEstado() instanceof NovoState ) || !( pedido.getEstado() instanceof AguardandoPagamentoState ) || !( pedido.getEstado() instanceof ProntoParaEntregaState ) || !( pedido.getEstado() instanceof ConfirmadoState );
		if( !isOperacaoValida ) {
			operacaoInvalida();
		} else {
			if( pedido.getEstado() instanceof NovoState ) {
				cancelarPedidoEstado();
			}

			if( pedido.getEstado() instanceof AguardandoPagamentoState ) {
				cancelarPedidoEstado();
			}

			if( pedido.getEstado() instanceof ProntoParaEntregaState ) {
				cancelarPedidoEstado();
			}

			if( pedido.getEstado() instanceof ConfirmadoState ) {
				cancelarPedidoEstado();
			}
		}
	}

	public void pagarPedido() throws OperacaoInvalidaException, StateException {
		if( !( pedido.getEstado() instanceof AguardandoPagamentoState ) ) {
			operacaoInvalida();
		}
		baixarQuantidadeEstoque();
		pedido.setEstado( pedido.getEstado().pagarPedido() );
	}

	public void prepararPedido() throws OperacaoInvalidaException, StateException {
		if( !( pedido.getEstado() instanceof ConfirmadoState ) ) {
			operacaoInvalida();
		}
		pedido.setEstado( pedido.getEstado().prepararPedido() );
	}

	public void sairParaEntregarPedido() throws OperacaoInvalidaException, StateException {
		if( !( pedido.getEstado() instanceof ProntoParaEntregaState ) ) {
			operacaoInvalida();
		}
		pedido.setEstado( pedido.getEstado().sairParaEntregarPedido() );
	}

	public void entregarPedido() throws OperacaoInvalidaException, StateException {
		if( !( pedido.getEstado() instanceof EmRotaDeEntregaState ) ) {
			operacaoInvalida();
		}
		pedido.setEstado( pedido.getEstado().entregarPedido() );
	}

	public void reembolsarPedido() throws OperacaoInvalidaException, StateException {
		boolean isOperacaoValida = !( pedido.getEstado() instanceof CanceladoPeloEstabelecimentoState ) || !( pedido.getEstado() instanceof CanceladoPeloClienteState );
		if( !isOperacaoValida ) {
			operacaoInvalida();
		} else {
			if( pedido.getEstado() instanceof CanceladoPeloEstabelecimentoState ) {
				reporEstoque();
				esvaziarListaItens();
				pedido.setEstado( pedido.getEstado().reembolsarPedido() );
			}

			if( pedido.getEstado() instanceof CanceladoPeloClienteState ) {
				PedidoDAO.getInstance().adicionaPedido( pedido );
				pedido.setEstado( pedido.getEstado().reembolsarPedido() );
			}
		}
	}

	public void avaliarAtendimentoPedido() throws OperacaoInvalidaException, StateException {
		boolean isOperacaoValida = !( pedido.getEstado() instanceof EntregueState ) || !( pedido.getEstado() instanceof ReembolsadoState );
		if( !isOperacaoValida ) {
			operacaoInvalida();
		} else {

			if( pedido.getEstado() instanceof EntregueState ) {
				avaliarAtendimento();
				pedido.setEstado( pedido.getEstado().avaliarAtendimentoPedido() );
			}

			if( pedido.getEstado() instanceof ReembolsadoState ) {
				avaliarAtendimento();
				pedido.setEstado( pedido.getEstado().avaliarAtendimentoPedido() );
			}
		}
	}

	private void adicionaPedido() {
		PedidoDAO.getInstance().adicionaPedido( pedido );
	}

	private void cancelarPedidoEstado() throws StateException {
		pedido.setEstado( pedido.getEstado().cancelarPedido() );
	}

	private void reporEstoque() {
		var itensPedido = pedido.getItensPedido();
		for( var itemPedido : itensPedido ) {
			ProdutoDAO.getInstance().adicionaEstoque( itemPedido.getProduto().getId(), itemPedido.getQuantidade() );
		}
	}

	private void calcularValoresItensPedido() {
		var itensPedidoCesta = pedido.getCestas();
		var itensPedido = itensPedidoCesta.stream().map( Cesta::getItensPedido ).flatMap( item -> item.stream().map( ItemPedido::getValorTotal ) ).collect( Collectors.toList() );

		var valorTotalCesta = itensPedido.stream().reduce( 0.0, ( subtotal, element ) -> subtotal + element );
		double valorFinalAPagar;
		if( pedido.getValorFinalAPagar() == 0.00 ) {
			valorFinalAPagar = pedido.getValorTotal() + valorTotalCesta + pedido.getValorFinalAPagar();
		} else {
			valorFinalAPagar = valorTotalCesta + pedido.getValorFinalAPagar();
		}
		var valorTotalDescontos = calculaDescontos( valorFinalAPagar );
		var valorTotalImpostos = calculaImpostos( valorFinalAPagar );

		pedido.setValorTotalDescontos( pedido.getValorTotalDescontos() + valorTotalDescontos );
		pedido.setValorTotalImpostos( pedido.getValorTotalImpostos() + valorTotalImpostos );
		valorFinalAPagar = valorFinalAPagar + pedido.getValorTotalImpostos() - pedido.getValorTotalDescontos();
		pedido.setValorFinalAPagar( valorFinalAPagar );
	}

	private double calculaDescontos( double valorFinalAPagar ) {
		var perc = 0;
		for( Desconto desconto : this.pedido.getDescontos() ) {
			perc += desconto.getPercentual();
		}
		return getValorCalculado( valorFinalAPagar, perc );
	}

	private double calculaImpostos( double valorFinalAPagar ) {
		var perc = 0;
		for( Imposto imposto : this.pedido.getImpostos() ) {
			perc += imposto.getPercentual();
		}
		return getValorCalculado( valorFinalAPagar, perc );
	}

	private void imprimirValoresPedido() {
		LOGGER.info( pedido.toString() );
		LOGGER.info( "O valor final do pedido é: " + pedido.getValorFinalAPagar() );
		LOGGER.info( "O valor do desconto do pedido é: " + pedido.getValorTotalDescontos() );
		LOGGER.info( "O valor do imposto do pedido é: " + pedido.getValorTotalImpostos() );
	}

	private double getValorCalculado( double valorFinalAPagar, int perc ) {
		return ( valorFinalAPagar * perc ) / CEM;
	}

	private void avaliarAtendimento() {
		LOGGER.info( "Por favor, avalie o Atendimento do Pedido com um número de 1 a 5" );
		int avaliacao;
		do {
			LOGGER.info( "Digite um número: " );
//			Scanner scanner = new Scanner( System.in );
//			avaliacao = scanner.nextInt();
//			scanner.close();
			avaliacao = 5;
		} while( avaliacao < 1 || avaliacao > 5 );

		LOGGER.info( "Avaliação do pedido: " + avaliacao );
	}

	private void incluirItemPedidoNaLista( int idProduto, double quantidade ) {
		var produto = ProdutoDAO.getInstance().buscaProdutoPorId( idProduto );
		ProdutoDAO.getInstance().baixaEstoque( idProduto, quantidade );
		this.adicionarItem( new ItemPedido( produto, quantidade ) );
	}

	private void removerItemPedidoDaLista( int idProduto, double quantidade ) {
		var produto = ProdutoDAO.getInstance().buscaProdutoPorId( idProduto );
		var itemPedido = this.getPedido().buscaItemPorProdutoItens( produto );

		if( !pedido.getItensPedido().contains( itemPedido ) ) {
			throw new DAOException( "Produto com o código " + itemPedido.getProduto().getId() + " não encontrado na lista de itens!" );
		}
		if( itemPedido.getQuantidade() == quantidade ) {
			pedido.getItensPedido().remove( itemPedido );
			pedido.setValorTotal( pedido.getValorTotal() - ( itemPedido.getProduto().getPrecoUnitario() * itemPedido.getQuantidade() ) );
			calcularValoresItensPedido();
		} else if( itemPedido.getQuantidade() > quantidade ) {
			itemPedido.diminuirQuantidade( quantidade );
			pedido.setValorTotal( pedido.getValorTotal() - itemPedido.getProduto().getPrecoUnitario() * quantidade );
			calcularValoresItensPedido();
		} else {
			throw new DAOException( "Não é possivel remover mais produtos do tipo " + itemPedido.getProduto().getNome() + "do que constam na lista!" );
		}
	}

	private void baixarQuantidadeEstoque() {
		var itensPedido = pedido.getItensPedido();
		for( var itemPedido : itensPedido ) {
			ProdutoDAO.getInstance().baixaEstoque( itemPedido.getProduto().getId(), itemPedido.getQuantidade() );
		}
	}

	private void operacaoInvalida() {
		throw new OperacaoInvalidaException( "Operação inválida!" );
	}

	private void esvaziarListaItens() {
		var itensPedido = pedido.getItensPedido();
		if( !itensPedido.isEmpty() ) {
			itensPedido.clear();
		}
	}

	private void incluirCestaNoPedido( TipoCestaEnum tipoCesta ) {
		Cesta cesta;
		switch ( tipoCesta ) {
			case CESTA_BASICA: {
				var iCestaBuilder = new CestaBasicaBuilder();
				var builder = new Builder( iCestaBuilder );
				builder.builderCesta();
				cesta = iCestaBuilder.getCesta();
				break;
			}
			case CESTA_ECONOMICA: {
				var iCestaBuilder = new CestaEconomicaBuilder();
				var builder = new Builder( iCestaBuilder );
				builder.builderCesta();
				cesta = iCestaBuilder.getCesta();
				break;
			}
			case CESTA_TOP: {
				var iCestaBuilder = new CestaTopBuilder();
				var builder = new Builder( iCestaBuilder );
				builder.builderCesta();
				cesta = iCestaBuilder.getCesta();
				break;
			}
			default:
				throw new RuntimeException( "Tipo inválido de cesta!" );
		}
		for( var item : cesta.getItensPedido() ) {
			ProdutoDAO.getInstance().baixaEstoque( item.getProduto().getId(), item.getQuantidade() );
		}

		adicionarCesta( cesta );
		LOGGER.info( "Cesta " + tipoCesta.name() + " adicionada com sucesso!" );
	}

	private void adicionarCesta( Cesta cesta ) {
		pedido.getCestas().add( cesta );
		calcularValoresItensPedido();
	}

	public void adicionarItem( ItemPedido item ) {
		pedido.getItensPedido().add( item );
		double valorTotal = pedido.getValorTotal();
		valorTotal += item.getProduto().getPrecoUnitario() * item.getQuantidade();
		pedido.setValorTotal( valorTotal );
	}

}
