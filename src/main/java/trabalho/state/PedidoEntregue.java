package trabalho.state;

import trabalho.business.PedidoBusiness;
import trabalho.exception.StateException;

public class PedidoEntregue implements IPedidoState {

	private final String EXCEPTION = "Operação em um Estado Inválido!";

	private PedidoBusiness pedidoBusiness;

	public PedidoEntregue( PedidoBusiness pedidoBusiness ) {
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
		throw new StateException( EXCEPTION );
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
		throw new StateException( EXCEPTION );
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
		pedidoBusiness.avaliarAtendimentoPedido();
	}

}
