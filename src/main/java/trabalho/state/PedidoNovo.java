package trabalho.state;

import trabalho.exception.StateException;

public class PedidoNovo implements IPedidoState {

	@Override
	public IPedidoState criarPedido() throws StateException {
		return this;
	}

	@Override
	public IPedidoState incluirRemoverItemPedido() throws StateException {
		return new PedidoIncluirRemoverItem();
	}

	@Override
	public IPedidoState concluirPedido() throws StateException {
		return new PedidoAguardandoPagamento();
	}

	@Override
	public IPedidoState cancelarPedido() throws StateException {
		return new PedidoCanceladoPeloCliente();
	}

	@Override
	public IPedidoState pagarPedido() throws StateException {
		throw new StateException( "Não é possível pagar o Pedido no estado Novo. Deve-se concluir/cancelar o Pedido primeiro." );
	}

	@Override
	public IPedidoState prepararPedido() throws StateException {
		throw new StateException( "Não é possível preparar o Pedido no estado Novo. Deve-se concluir/cancelar o Pedido primeiro." );
	}

	@Override
	public IPedidoState sairParaEntregarPedido() throws StateException {
		throw new StateException( "Não é possível sair para entregar o Pedido no estado Novo. Deve-se concluir/cancelar o Pedido primeiro." );
	}

	@Override
	public IPedidoState entregarPedido() throws StateException {
		throw new StateException( "Não é possível entregar o Pedido no estado Novo. Deve-se concluir/cancelar o Pedido primeiro." );
	}

	@Override
	public IPedidoState reembolsarPedido() throws StateException {
		throw new StateException( "Não é possível reembolsar/repor o estoque do Pedido no estado Novo. Deve-se concluir/cancelar o Pedido primeiro." );
	}

	@Override
	public int avaliarAtendimentoPedido() throws StateException {
		throw new StateException( "Não é possível avaliar o atendimento do Pedido no estado Novo. Deve-se concluir/cancelar o Pedido primeiro." );
	}

}
