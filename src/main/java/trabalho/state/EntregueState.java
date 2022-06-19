package trabalho.state;

import trabalho.business.PedidoBusiness;
import trabalho.exception.StateException;

public class EntregueState extends AbstractState {

	public EntregueState( PedidoBusiness pedidoBusiness ) {}

	@Override
	public AbstractState avaliarAtendimentoPedido() throws StateException {
		return null;
	}

}
