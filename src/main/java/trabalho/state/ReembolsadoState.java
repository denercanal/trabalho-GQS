package trabalho.state;

import trabalho.business.PedidoBusiness;
import trabalho.exception.StateException;

public class ReembolsadoState extends AbstractState {

	public ReembolsadoState( PedidoBusiness pedidoBusiness ) {}

	@Override
	public AbstractState avaliarAtendimentoPedido() throws StateException {
		return null;
	}

}
