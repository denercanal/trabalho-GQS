package trabalho.state;

import trabalho.business.PedidoBusiness;
import trabalho.exception.StateException;

public class EntregueState extends AbstractState {

	private PedidoBusiness pedidoBusiness;

	public EntregueState( PedidoBusiness pedidoBusiness ) {
		this.pedidoBusiness = pedidoBusiness;
	}

	@Override
	public void avaliarAtendimentoPedido() throws StateException {
		pedidoBusiness.avaliarAtendimentoPedido();
	}

}
