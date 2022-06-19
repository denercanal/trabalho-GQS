package trabalho.state;

import trabalho.business.PedidoBusiness;
import trabalho.exception.StateException;

public class NovoState extends AbstractState {

	private PedidoBusiness pedidoBusiness;

	public NovoState( PedidoBusiness pedidoBusiness ) {
		this.pedidoBusiness = pedidoBusiness;
	}

	@Override
	public AbstractState concluirPedido() throws StateException {
		return new AguardandoPagamentoState( pedidoBusiness );
	}

	@Override
	public AbstractState cancelarPedido() throws StateException {
		return new CanceladoPeloClienteState( pedidoBusiness );
	}

}
