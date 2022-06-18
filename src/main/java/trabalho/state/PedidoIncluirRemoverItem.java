package trabalho.state;

import java.util.List;
import java.util.Scanner;

import trabalho.dao.ProdutoDAO;
import trabalho.exception.OperacaoInvalidaException;
import trabalho.exception.StateException;
import trabalho.model.ItemPedido;
import trabalho.model.Pedido;
import trabalho.model.Produto;

public class PedidoIncluirRemoverItem implements IPedidoState {

	@Override
	public IPedidoState criarPedido() throws StateException {
		throw new StateException( "Não é possível criar o Pedido no estado Incluir Remover Item. Deve-se concluir/cancelar o Pedido primeiro." );
	}

	@Override
	public List<ItemPedido> incluirRemoverItemPedido( List<ItemPedido> itensPedido ) throws StateException {

		var produtos = ProdutoDAO.getInstance().getProdutos();
		produtos.stream().forEach( System.out::println );
		var sc = new Scanner( System.in );
		int entrada;
		do {
			var itemPedido = new ItemPedido();
			System.out.println( "Insira o id do produto que deseja incluir ao pedido." );
//			entrada = sc.nextInt();
			entrada = 1;
			var produto = ProdutoDAO.getInstance().buscaProdutoPorId( entrada );
			if( produto.getQuantidadeEmEstoque() <= 0 ) {
				validarEstoqueProduto( produto );
			}
			System.out.println( "Insira a quantidade do produto que deseja adicionar ao pedido: " );
//			entrada = sc.nextInt();
			entrada = 10;
			if( entrada > produto.getQuantidadeEmEstoque() ) {
				validarEstoqueProduto( produto );
			}
			itemPedido.setQuantidade( entrada );
			itemPedido.setValorUnitario( produto.getPrecoUnitario() );
			itemPedido.setValorTotal( itemPedido.getValorUnitario() * itemPedido.getQuantidade() );
			itemPedido.setProduto( produto );
			itensPedido.add( itemPedido );
			System.out.println( "Deseja incluir outro produto ao pedido? 1 - sim | 2 - não: " );
//			entrada = sc.nextInt();
			entrada = 2;

		} while( entrada == 1 );
		sc.close();
		return itensPedido;
	}

	private void validarEstoqueProduto( Produto produto ) {
		int entrada;
		System.out.println( "O produto não possui estoque disponível, deseja adicionar? 1 - sim | 2 - não: " );
//				entrada = sc.nextInt();
		entrada = 1;
		if( entrada == 1 ) {
			System.out.println( "Insira a quantidade que deseja aumentar no estoque: " );
//					entrada = sc.nextInt();
			entrada = 10;
			while( entrada <= 0 ) {
				System.out.println( "Insira uma quantidade maior que 0: " );
//						entrada = sc.nextInt();
				entrada = 10;
			}
			produto.incrementaEstoque( entrada );
		} else {
			throw new OperacaoInvalidaException( "Não é possível inserir um produto sem estoque ao pedido!" );
		}
	}

	@Override
	public IPedidoState concluirPedido( Pedido pedido ) throws StateException {
		throw new StateException( "Não é possível avaliar o atendimento do Pedido no estado Incluir Remover Item. Deve-se concluir/cancelar o Pedido primeiro." );
	}

	@Override
	public IPedidoState cancelarPedido() throws StateException {
		throw new StateException( "Não é possível avaliar o atendimento do Pedido no estado Incluir Remover Item. Deve-se concluir/cancelar o Pedido primeiro." );
	}

	@Override
	public IPedidoState pagarPedido( Pedido pedido ) throws StateException {
		throw new StateException( "Não é possível avaliar o atendimento do Pedido no estado Incluir Remover Item. Deve-se concluir/cancelar o Pedido primeiro." );
	}

	@Override
	public IPedidoState prepararPedido() throws StateException {
		throw new StateException( "Não é possível avaliar o atendimento do Pedido no estado Incluir Remover Item. Deve-se concluir/cancelar o Pedido primeiro." );
	}

	@Override
	public IPedidoState sairParaEntregarPedido() throws StateException {
		throw new StateException( "Não é possível avaliar o atendimento do Pedido no estado Incluir Remover Item. Deve-se concluir/cancelar o Pedido primeiro." );
	}

	@Override
	public IPedidoState entregarPedido() throws StateException {
		throw new StateException( "Não é possível avaliar o atendimento do Pedido no estado Incluir Remover Item. Deve-se concluir/cancelar o Pedido primeiro." );
	}

	@Override
	public IPedidoState reembolsarPedido() throws StateException {
		throw new StateException( "Não é possível avaliar o atendimento do Pedido no estado Incluir Remover Item. Deve-se concluir/cancelar o Pedido primeiro." );
	}

	@Override
	public int avaliarAtendimentoPedido() throws StateException {
		throw new StateException( "Não é possível avaliar o atendimento do Pedido no estado Incluir Remover Item. Deve-se concluir/cancelar o Pedido primeiro." );
	}

}
