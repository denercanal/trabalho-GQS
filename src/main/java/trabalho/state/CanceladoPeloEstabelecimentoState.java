package trabalho.state;

import trabalho.business.PedidoBusiness;
import trabalho.exception.StateException;

public class CanceladoPeloEstabelecimentoState extends AbstractState {

	private PedidoBusiness pedidoBusiness;

	public CanceladoPeloEstabelecimentoState( PedidoBusiness pedidoBusiness ) {
		this.pedidoBusiness = pedidoBusiness;
	}

	@Override
	public AbstractState reembolsarPedido() throws StateException {
		return new ReembolsadoState( pedidoBusiness );
	}

}
