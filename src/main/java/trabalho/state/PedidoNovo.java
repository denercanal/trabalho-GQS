package trabalho.state;

import trabalho.exception.StateException;

public class PedidoNovo implements IPedidoState {

	public IPedidoState criarPedido() throws StateException {
		// TODO Auto-generated method stub
		return null;
	}

	public IPedidoState concluirPedido() throws StateException {
		// TODO Auto-generated method stub
		return null;
	}

	public IPedidoState cancelarPedido() throws StateException {
		// TODO Auto-generated method stub
		return null;
	}

	public IPedidoState pagarPedido() throws StateException {
		throw new StateException( "" );
	}

	public IPedidoState prepararPedido() throws StateException {
		throw new StateException( "" );
	}

	public IPedidoState enviarPedido() throws StateException {
		throw new StateException( "" );
	}

	public IPedidoState entregarPedido() throws StateException {
		throw new StateException( "" );
	}

	public IPedidoState reembolsarPedido() throws StateException {
		throw new StateException( "" );
	}

	public IPedidoState finalizarPedido() throws StateException {
		throw new StateException( "" );
	}

}
