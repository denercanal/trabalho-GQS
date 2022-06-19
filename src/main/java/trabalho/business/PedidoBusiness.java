package trabalho.business;

import java.util.Scanner;

import trabalho.dao.PedidoDAO;
import trabalho.dao.ProdutoDAO;
import trabalho.exception.OperacaoInvalidaException;
import trabalho.exception.StateException;
import trabalho.model.Desconto;
import trabalho.model.Imposto;
import trabalho.model.ItemPedido;
import trabalho.model.Pedido;
import trabalho.state.PedidoAguardandoPagamento;
import trabalho.state.PedidoCanceladoPeloCliente;
import trabalho.state.PedidoCanceladoPeloEstabelecimento;
import trabalho.state.PedidoConfirmado;
import trabalho.state.PedidoEmRotaDeEntrega;
import trabalho.state.PedidoEntregue;
import trabalho.state.PedidoNovo;
import trabalho.state.PedidoProntoParaEntrega;
import trabalho.state.PedidoReembolsado;

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
		if( !( pedido.getEstado() instanceof PedidoNovo ) ) {
			operacaoInvalida();
		}
		incluirItemPedidoNaLista( idProduto, quantidade );
	}

	public void removerItemPedido( int idProduto, double quantidade ) throws OperacaoInvalidaException {
		if( !( pedido.getEstado() instanceof PedidoNovo ) ) {
			operacaoInvalida();
		}
		removerItemPedidoDaLista( idProduto, quantidade );
	}

	public void concluirPedido() throws OperacaoInvalidaException, StateException {
		if( !( pedido.getEstado() instanceof PedidoNovo ) ) {
			operacaoInvalida();
		}
		calcularValores();
		imprimirValoresPedido();
		adicionaPedido();
		pedido.setEstado( pedido.getEstado().concluirPedido() );
	}

	public void cancelarPedido() throws OperacaoInvalidaException, StateException {
		boolean isOperacaoValida = !( pedido.getEstado() instanceof PedidoNovo ) || !( pedido.getEstado() instanceof PedidoAguardandoPagamento ) || !( pedido.getEstado() instanceof PedidoProntoParaEntrega ) || !( pedido.getEstado() instanceof PedidoConfirmado );
		if( !isOperacaoValida ) {
			operacaoInvalida();
		} else {
			if( pedido.getEstado() instanceof PedidoNovo ) {
				cancelarPedidoEstado();
			}

			if( pedido.getEstado() instanceof PedidoAguardandoPagamento ) {
				cancelarPedidoEstado();
			}

			if( pedido.getEstado() instanceof PedidoProntoParaEntrega ) {
				cancelarPedidoEstado();
			}

			if( pedido.getEstado() instanceof PedidoConfirmado ) {
				cancelarPedidoEstado();
			}
		}
	}

	public void pagarPedido() throws OperacaoInvalidaException, StateException {
		if( !( pedido.getEstado() instanceof PedidoAguardandoPagamento ) ) {
			operacaoInvalida();
		}
		baixarQuantidadeEstoque();
		pedido.setEstado( pedido.getEstado().pagarPedido() );
	}

	public void prepararPedido() throws OperacaoInvalidaException, StateException {
		if( !( pedido.getEstado() instanceof PedidoConfirmado ) ) {
			operacaoInvalida();
		}
		pedido.setEstado( pedido.getEstado().prepararPedido() );
	}

	public void sairParaEntregarPedido() throws OperacaoInvalidaException, StateException {
		if( !( pedido.getEstado() instanceof PedidoProntoParaEntrega ) ) {
			operacaoInvalida();
		}
		pedido.setEstado( pedido.getEstado().sairParaEntregarPedido() );
	}

	public void entregarPedido() throws OperacaoInvalidaException, StateException {
		if( !( pedido.getEstado() instanceof PedidoEmRotaDeEntrega ) ) {
			operacaoInvalida();
		}
		pedido.setEstado( pedido.getEstado().entregarPedido() );
	}

	public void reembolsarPedido() throws OperacaoInvalidaException, StateException {
		boolean isOperacaoValida = !( pedido.getEstado() instanceof PedidoCanceladoPeloEstabelecimento ) || !( pedido.getEstado() instanceof PedidoCanceladoPeloCliente );
		if( !isOperacaoValida ) {
			operacaoInvalida();
		} else {
			if( pedido.getEstado() instanceof PedidoCanceladoPeloEstabelecimento ) {
				reporEstoque();
				esvaziarListaItens();
				pedido.setEstado( pedido.getEstado().reembolsarPedido() );
			}

			if( pedido.getEstado() instanceof PedidoCanceladoPeloCliente ) {
				PedidoDAO.getInstance().adicionaPedido( pedido );
				pedido.setEstado( pedido.getEstado().reembolsarPedido() );
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

	public void avaliarAtendimentoPedido() throws OperacaoInvalidaException {
		boolean isOperacaoValida = !( pedido.getEstado() instanceof PedidoEntregue ) || !( pedido.getEstado() instanceof PedidoReembolsado );
		if( !isOperacaoValida ) {
			operacaoInvalida();
		} else {

			if( pedido.getEstado() instanceof PedidoEntregue ) {
				avaliarAtendimento();
			}

			if( pedido.getEstado() instanceof PedidoReembolsado ) {
				avaliarAtendimento();
			}
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
			throw new RuntimeException( "Produto com o código " + itemPedido.getProduto().getId() + " não encontrado na lista de itens!" );
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
			throw new RuntimeException( "Não é possivel remover mais produtos do tipo " + itemPedido.getProduto().getNome() + "do que constam na lista!" );
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
			throw new RuntimeException( "Não se pode esvaziar uma lista de produtos vazia!" );
		}
		itensPedido.clear();
	}

}
