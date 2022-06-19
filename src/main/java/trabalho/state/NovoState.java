package trabalho.state;

import trabalho.business.PedidoBusiness;
import trabalho.exception.StateException;

public class NovoState extends AbstractState {

	private PedidoBusiness pedidoBusiness;

	public NovoState( PedidoBusiness pedidoBusiness ) {
		this.pedidoBusiness = pedidoBusiness;
	}

	@Override
	public void incluirItemPedido( int idProduto, double quantidade ) throws StateException {
		pedidoBusiness.incluirItemPedido( idProduto, quantidade );
	}

	@Override
	public void removerItemPedido( int idProduto, double quantidade ) throws StateException {
		pedidoBusiness.removerItemPedido( idProduto, quantidade );
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
