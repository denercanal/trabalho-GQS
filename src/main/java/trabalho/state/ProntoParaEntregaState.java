package trabalho.state;

import trabalho.business.PedidoBusiness;
import trabalho.exception.StateException;

public class ProntoParaEntregaState extends AbstractState {

	private PedidoBusiness pedidoBusiness;

	public ProntoParaEntregaState( PedidoBusiness pedidoBusiness ) {
		this.pedidoBusiness = pedidoBusiness;
	}

	@Override
	public AbstractState cancelarPedido() throws StateException {
		return new CanceladoPeloEstabelecimentoState( pedidoBusiness );
	}

	@Override
	public AbstractState sairParaEntregarPedido() throws StateException {
		return new EmRotaDeEntregaState( pedidoBusiness );
	}

}
