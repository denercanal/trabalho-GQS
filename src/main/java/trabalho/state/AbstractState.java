package trabalho.state;

import trabalho.enums.TipoCestaEnum;
import trabalho.exception.StateException;

public abstract class AbstractState {

	private static final String ESTADO_INVALIDO = "Não é possível realizar a operação no estado atual!";

	public void incluirItemPedido( int idProduto, double quantidade ) throws StateException {
		throw new StateException( ESTADO_INVALIDO );
	}

	public void incluirCesta( TipoCestaEnum tipoCesta ) throws StateException {
		throw new StateException( ESTADO_INVALIDO );
	}

	public void removerItemPedido( int idProduto, double quantidade ) throws StateException {
		throw new StateException( ESTADO_INVALIDO );
	}

	public AbstractState concluirPedido() throws StateException {
		throw new StateException( ESTADO_INVALIDO );
	}

	public AbstractState cancelarPedido() throws StateException {
		throw new StateException( ESTADO_INVALIDO );
	}

	public AbstractState pagarPedido() throws StateException {
		throw new StateException( ESTADO_INVALIDO );
	}

	public AbstractState prepararPedido() throws StateException {
		throw new StateException( ESTADO_INVALIDO );
	}

	public AbstractState sairParaEntregarPedido() throws StateException {
		throw new StateException( ESTADO_INVALIDO );
	}

	public AbstractState entregarPedido() throws StateException {
		throw new StateException( ESTADO_INVALIDO );
	}

	public AbstractState reembolsarPedido() throws StateException {
		throw new StateException( ESTADO_INVALIDO );
	}

	public void avaliarAtendimentoPedido() throws StateException {
		throw new StateException( ESTADO_INVALIDO );
	}

}
