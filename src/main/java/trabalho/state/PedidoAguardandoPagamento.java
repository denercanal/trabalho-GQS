package trabalho.state;

import java.util.List;

import trabalho.exception.StateException;
import trabalho.model.ItemPedido;
import trabalho.model.Pedido;

public class PedidoAguardandoPagamento implements IPedidoState {

	@Override
	public IPedidoState criarPedido() throws StateException {
		throw new StateException( "Não é possível criar o Pedido no estado Aguardando Pagamento. Deve-se pagar/cancelar o Pedido primeiro." );
	}

	@Override
	public List<ItemPedido> incluirRemoverItemPedido( List<ItemPedido> itensPedido ) throws StateException {
		throw new StateException( "Não é possível incluir/remover item do Pedido no estado Aguardando Pagamento. Deve-se pagar/cancelar o Pedido primeiro." );
	}

	@Override
	public IPedidoState concluirPedido( Pedido pedido ) throws StateException {
		throw new StateException( "Não é possível concluir o Pedido no estado Aguardando Pagamento. Deve-se pagar/cancelar o Pedido primeiro." );
	}

	@Override
	public IPedidoState cancelarPedido() throws StateException {
		return new PedidoCanceladoPeloCliente();
	}

	@Override
	public IPedidoState pagarPedido() throws StateException {
		return new PedidoConfirmado();
	}

	@Override
	public IPedidoState prepararPedido() throws StateException {
		throw new StateException( "Não é possível preparar o Pedido no estado Aguardando Pagamento. Deve-se pagar/cancelar o Pedido primeiro." );
	}

	@Override
	public IPedidoState sairParaEntregarPedido() throws StateException {
		throw new StateException( "Não é possível sair para entregar o Pedido no estado Aguardando Pagamento. Deve-se pagar/cancelar o Pedido primeiro." );
	}

	@Override
	public IPedidoState entregarPedido() throws StateException {
		throw new StateException( "Não é possível entregar o Pedido no estado Aguardando Pagamento. Deve-se pagar/cancelar o Pedido primeiro." );
	}

	@Override
	public IPedidoState reembolsarPedido() throws StateException {
		throw new StateException( "Não é possível reembolsar/repor o estoque do Pedido no estado Aguardando Pagamento. Deve-se pagar/cancelar o Pedido primeiro." );
	}

	@Override
	public int avaliarAtendimentoPedido() throws StateException {
		throw new StateException( "Não é possível avaliar o atendimento do Pedido no estado Aguardando Pagamento. Deve-se pagar/cancelar o Pedido primeiro." );
	}

}
