package trabalho.state;

import trabalho.business.PedidoBusiness;
import trabalho.exception.StateException;

public class AguardandoPagamentoState extends AbstractState {

	private PedidoBusiness pedidoBusiness;

	public AguardandoPagamentoState( PedidoBusiness pedidoBusiness ) {
		this.pedidoBusiness = pedidoBusiness;
	}

	@Override
	public AbstractState pagarPedido() throws StateException {
		return new ConfirmadoState( pedidoBusiness );
	}

}
