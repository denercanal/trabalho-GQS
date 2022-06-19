package trabalho.state;

import trabalho.business.PedidoBusiness;
import trabalho.exception.StateException;

public class EmRotaDeEntregaState extends AbstractState {

	private PedidoBusiness pedidoBusiness;

	public EmRotaDeEntregaState( PedidoBusiness pedidoBusiness ) {
		this.pedidoBusiness = pedidoBusiness;
	}

	@Override
	public AbstractState entregarPedido() throws StateException {
		return new EntregueState( pedidoBusiness );
	}

}
