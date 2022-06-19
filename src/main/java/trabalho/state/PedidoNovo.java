package trabalho.state;

import trabalho.business.PedidoBusiness;
import trabalho.exception.StateException;

public class PedidoNovo implements IPedidoState {

	private static final String STATE_INVALIDO = "Operação em um Estado Inválido!";

	private PedidoBusiness pedidoBusiness;

	public PedidoNovo( PedidoBusiness pedidoBusiness ) {
		this.pedidoBusiness = pedidoBusiness;
	}

	@Override
	public IPedidoState criarPedido() throws StateException {
		return this;
	}

	@Override
	public void incluirItemPedido( int idProduto, double quantidade ) throws StateException {
		pedidoBusiness.incluirItemPedido( idProduto, quantidade );
	}

	@Override
	public void removerItemPedido( int idProduto, double quantidade ) throws StateException {
		pedidoBusiness.removerItemPedido( idProduto, quantidade );
	}

	@Override
	public IPedidoState concluirPedido() throws StateException {
		return new PedidoAguardandoPagamento( pedidoBusiness );
	}

	@Override
	public IPedidoState cancelarPedido() throws StateException {
		return new PedidoCanceladoPeloCliente( pedidoBusiness );
	}

	@Override
	public IPedidoState pagarPedido() throws StateException {
		throw new StateException( STATE_INVALIDO );
	}

	@Override
	public IPedidoState prepararPedido() throws StateException {
		throw new StateException( STATE_INVALIDO );
	}

	@Override
	public IPedidoState sairParaEntregarPedido() throws StateException {
		throw new StateException( STATE_INVALIDO );
	}

	@Override
	public IPedidoState entregarPedido() throws StateException {
		throw new StateException( STATE_INVALIDO );
	}

	@Override
	public IPedidoState reembolsarPedido() throws StateException {
		throw new StateException( STATE_INVALIDO );
	}

	@Override
	public void avaliarAtendimentoPedido() throws StateException {
		throw new StateException( STATE_INVALIDO );
	}

}
