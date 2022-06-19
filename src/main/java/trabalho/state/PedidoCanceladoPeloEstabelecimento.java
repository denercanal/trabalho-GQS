package trabalho.state;

import trabalho.business.PedidoBusiness;
import trabalho.exception.StateException;

public class PedidoCanceladoPeloEstabelecimento implements IPedidoState {

	private static final String STATE_INVALIDO = "Operação em um Estado Inválido!";

	private PedidoBusiness pedidoBusiness;

	public PedidoCanceladoPeloEstabelecimento( PedidoBusiness pedidoBusiness ) {
		this.pedidoBusiness = pedidoBusiness;
	}

	@Override
	public IPedidoState criarPedido() throws StateException {
		throw new StateException( STATE_INVALIDO );
	}

	@Override
	public void incluirItemPedido( int idProduto, double quantidade ) throws StateException {
		throw new StateException( STATE_INVALIDO );
	}

	@Override
	public void removerItemPedido( int idProduto, double quantidade ) throws StateException {
		throw new StateException( STATE_INVALIDO );
	}

	@Override
	public IPedidoState concluirPedido() throws StateException {
		throw new StateException( STATE_INVALIDO );
	}

	@Override
	public IPedidoState cancelarPedido() throws StateException {
		throw new StateException( STATE_INVALIDO );
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
		return new PedidoReembolsado( pedidoBusiness );
	}

	@Override
	public void avaliarAtendimentoPedido() throws StateException {
		throw new StateException( STATE_INVALIDO );
	}

}
