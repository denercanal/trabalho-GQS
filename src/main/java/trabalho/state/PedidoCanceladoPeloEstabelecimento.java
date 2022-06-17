package trabalho.state;

import trabalho.exception.StateException;

public class PedidoCanceladoPeloEstabelecimento implements IPedidoState {

	@Override
	public IPedidoState criarPedido() throws StateException {
		throw new StateException( "Não é possível criar o Pedido no estado Aguardando Pagamento. Deve-se reembolsar/repor o estoque do Pedido primeiro." );
	}

	@Override
	public IPedidoState incluirRemoverItemPedido() throws StateException {
		throw new StateException( "Não é possível incluir/remover item do Pedido no estado Aguardando Pagamento. Deve-se pagar/cancelar o Pedido primeiro." );
	}

	@Override
	public IPedidoState concluirPedido() throws StateException {
		throw new StateException( "Não é possível concluir o Pedido no estado Aguardando Pagamento. Deve-se reembolsar/repor o estoque do Pedido primeiro." );
	}

	@Override
	public IPedidoState cancelarPedido() throws StateException {
		throw new StateException( "Não é possível cancelar o Pedido no estado Aguardando Pagamento. Deve-se reembolsar/repor o estoque do Pedido primeiro." );
	}

	@Override
	public IPedidoState pagarPedido() throws StateException {
		throw new StateException( "Não é possível pagar o Pedido no estado Aguardando Pagamento. Deve-se reembolsar/repor o estoque do Pedido primeiro." );
	}

	@Override
	public IPedidoState prepararPedido() throws StateException {
		throw new StateException( "Não é possível preparar o Pedido no estado Aguardando Pagamento. Deve-se reembolsar/repor o estoque do Pedido primeiro." );
	}

	@Override
	public IPedidoState sairParaEntregarPedido() throws StateException {
		throw new StateException( "Não é possível sair para entregar o Pedido no estado Aguardando Pagamento. Deve-se reembolsar/repor o estoque do Pedido primeiro." );
	}

	@Override
	public IPedidoState entregarPedido() throws StateException {
		throw new StateException( "Não é possível entregar o Pedido no estado Aguardando Pagamento. Deve-se reembolsar/repor o estoque do Pedido primeiro." );
	}

	@Override
	public IPedidoState reembolsarPedido() throws StateException {
		return new PedidoReembolsado();
	}

	@Override
	public int avaliarAtendimentoPedido() throws StateException {
		throw new StateException( "Não é possível avaliar o atendimento do Pedido no estado Aguardando Pagamento. Deve-se reembolsar/repor o estoque do Pedido primeiro." );
	}

}
