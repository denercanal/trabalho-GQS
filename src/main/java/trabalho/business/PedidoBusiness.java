package trabalho.business;

import java.util.Scanner;

import trabalho.dao.PedidoDAO;
import trabalho.dao.ProdutoDAO;
import trabalho.exception.DAOException;
import trabalho.exception.OperacaoInvalidaException;
import trabalho.exception.StateException;
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
		calcularValores();
		imprimirValoresPedido();
		adicionaPedido();
		pedido.setEstado( pedido.getEstado().concluirPedido() );
	}

	public void cancelarPedido() throws OperacaoInvalidaException, StateException {
		boolean isOperacaoInvalida = !( pedido.getEstado() instanceof NovoState ) || !( pedido.getEstado() instanceof AguardandoPagamentoState ) || !( pedido.getEstado() instanceof ProntoParaEntregaState ) || !( pedido.getEstado() instanceof ConfirmadoState );
		if( isOperacaoInvalida ) {
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
		boolean isOperacaoInvalida = !( pedido.getEstado() instanceof CanceladoPeloEstabelecimentoState ) || !( pedido.getEstado() instanceof CanceladoPeloClienteState );
		if( isOperacaoInvalida ) {
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

	public void avaliarAtendimentoPedido() throws OperacaoInvalidaException {
		boolean isOperacaoInvalida = !( pedido.getEstado() instanceof EntregueState ) || !( pedido.getEstado() instanceof ReembolsadoState );
		if( isOperacaoInvalida ) {
			operacaoInvalida();
		} else {

			if( pedido.getEstado() instanceof EntregueState ) {
				avaliarAtendimento();
			}

			if( pedido.getEstado() instanceof ReembolsadoState ) {
				avaliarAtendimento();
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

	private void calcularValores() {
		pedido.setValorTotalDescontos( pedido.getValorTotalDescontos() + calculaDescontos( pedido.getValorTotal() ) );
		pedido.setValorTotalImpostos( pedido.getValorTotalImpostos() + calculaImpostos( pedido.getValorTotal() ) );
		pedido.setValorFinalAPagar( pedido.getValorTotal() + pedido.getValorTotalImpostos() - pedido.getValorTotalDescontos() );
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
		System.out.println( pedido.toString() );
		System.out.println( "O valor final do pedido é: " + pedido.getValorFinalAPagar() );
		System.out.println( "O valor do desconto do pedido é: " + pedido.getValorTotalDescontos() );
		System.out.println( "O valor do imposto do pedido é: " + pedido.getValorTotalImpostos() );
	}

	private double getValorCalculado( double valorFinalAPagar, int perc ) {
		return ( valorFinalAPagar * perc ) / CEM;
	}

	private void avaliarAtendimento() {
		System.out.println( "Por favor, avalie o Atendimento do Pedido com um número de 1 a 5" );
		int avaliacao;
		do {
			System.out.print( "Digite um número: " );
			Scanner scanner = new Scanner( System.in );
			avaliacao = scanner.nextInt();
			scanner.close();
		} while( avaliacao < 1 || avaliacao > 5 );

		System.out.println( "Avaliação do pedido: " + avaliacao );
	}

	private void incluirItemPedidoNaLista( int idProduto, double quantidade ) {
		ProdutoDAO.getInstance().verificaQuantidade( quantidade );
		var produto = ProdutoDAO.getInstance().buscaProdutoPorId( idProduto );
		var itensPedido = pedido.getItensPedido();
		itensPedido.add( new ItemPedido( produto, quantidade ) );
		var valorTotal = pedido.getValorTotal();
		for( var itemPedido : itensPedido ) {
			valorTotal += itemPedido.getValorTotal();
		}
		pedido.setValorTotal( valorTotal );
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
			calcularValores();
		} else if( itemPedido.getQuantidade() > quantidade ) {
			itemPedido.diminuirQuantidade( quantidade );
			pedido.setValorTotal( pedido.getValorTotal() - itemPedido.getProduto().getPrecoUnitario() * quantidade );
			calcularValores();
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
		if( itensPedido.isEmpty() ) {
			throw new DAOException( "Não se pode esvaziar uma lista de produtos vazia!" );
		}
		itensPedido.clear();
	}

}
