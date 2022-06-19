package trabalho.state;

import trabalho.business.PedidoBusiness;
import trabalho.exception.StateException;

public class PedidoProntoParaEntrega implements IPedidoState {

	private final String EXCEPTION = "Operação em um Estado Inválido!";

	private PedidoBusiness pedidoBusiness;

	public PedidoProntoParaEntrega( PedidoBusiness pedidoBusiness ) {
		this.pedidoBusiness = pedidoBusiness;
	}

	@Override
	public IPedidoState criarPedido() throws StateException {
		throw new StateException( EXCEPTION );
	}

	@Override
	public void incluirItemPedido( int idProduto, double quantidade ) throws StateException {
		throw new StateException( EXCEPTION );
	}

	@Override
	public void removerItemPedido( int idProduto, double quantidade ) throws StateException {
		throw new StateException( EXCEPTION );
	}

	@Override
	public IPedidoState concluirPedido() throws StateException {
		throw new StateException( EXCEPTION );
	}

	@Override
	public IPedidoState cancelarPedido() throws StateException {
		return new PedidoCanceladoPeloCliente( pedidoBusiness );
	}

	@Override
	public IPedidoState pagarPedido() throws StateException {
		throw new StateException( EXCEPTION );
	}

	@Override
	public IPedidoState prepararPedido() throws StateException {
		throw new StateException( EXCEPTION );
	}

	@Override
	public IPedidoState sairParaEntregarPedido() throws StateException {
		return new PedidoEmRotaDeEntrega( pedidoBusiness );
	}

	@Override
	public IPedidoState entregarPedido() throws StateException {
		throw new StateException( EXCEPTION );
	}

	@Override
	public IPedidoState reembolsarPedido() throws StateException {
		throw new StateException( EXCEPTION );
	}

	@Override
	public void avaliarAtendimentoPedido() throws StateException {
		throw new StateException( EXCEPTION );
	}

}
