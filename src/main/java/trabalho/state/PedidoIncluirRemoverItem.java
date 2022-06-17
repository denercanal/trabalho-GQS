package trabalho.state;

import trabalho.exception.StateException;

public class PedidoIncluirRemoverItem implements IPedidoState {

	@Override
	public IPedidoState criarPedido() throws StateException {
		throw new StateException( "Não é possível criar o Pedido no estado Incluir Remover Item. Deve-se concluir/cancelar o Pedido primeiro." );
	}

	@Override
	public IPedidoState incluirRemoverItemPedido() throws StateException {
		// TODO
		return null;
	}

	@Override
	public IPedidoState concluirPedido() throws StateException {
		throw new StateException( "Não é possível avaliar o atendimento do Pedido no estado Incluir Remover Item. Deve-se concluir/cancelar o Pedido primeiro." );
	}

	@Override
	public IPedidoState cancelarPedido() throws StateException {
		throw new StateException( "Não é possível avaliar o atendimento do Pedido no estado Incluir Remover Item. Deve-se concluir/cancelar o Pedido primeiro." );
	}

	@Override
	public IPedidoState pagarPedido() throws StateException {
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
