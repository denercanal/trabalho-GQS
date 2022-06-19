package trabalho.state;

import trabalho.business.PedidoBusiness;
import trabalho.exception.StateException;

public class ReembolsadoState extends AbstractState {

	private PedidoBusiness pedidoBusiness;

	public ReembolsadoState( PedidoBusiness pedidoBusiness ) {
		this.pedidoBusiness = pedidoBusiness;
	}

	@Override
	public void avaliarAtendimentoPedido() throws StateException {
		pedidoBusiness.avaliarAtendimentoPedido();
	}

}
