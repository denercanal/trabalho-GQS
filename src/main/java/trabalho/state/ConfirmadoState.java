package trabalho.state;

import trabalho.business.PedidoBusiness;
import trabalho.exception.StateException;

public class ConfirmadoState extends AbstractState {

	private PedidoBusiness pedidoBusiness;

	public ConfirmadoState( PedidoBusiness pedidoBusiness ) {
		this.pedidoBusiness = pedidoBusiness;
	}

	@Override
	public AbstractState cancelarPedido() throws StateException {
		return new CanceladoPeloEstabelecimentoState( pedidoBusiness );
	}

	@Override
	public AbstractState prepararPedido() throws StateException {
		return new ProntoParaEntregaState( pedidoBusiness );
	}

}
