package trabalho.state;

import java.util.List;

import trabalho.exception.StateException;
import trabalho.model.ItemPedido;
import trabalho.model.Pedido;

public interface IPedidoState {

	public IPedidoState criarPedido() throws StateException;

	public List<ItemPedido> incluirRemoverItemPedido( List<ItemPedido> itensPedido ) throws StateException;

	public IPedidoState concluirPedido( Pedido pedido ) throws StateException;

	public IPedidoState cancelarPedido() throws StateException;

	public IPedidoState pagarPedido() throws StateException;

	public IPedidoState prepararPedido() throws StateException;

	public IPedidoState sairParaEntregarPedido() throws StateException;

	public IPedidoState entregarPedido() throws StateException;

	public IPedidoState reembolsarPedido() throws StateException;

	public int avaliarAtendimentoPedido() throws StateException;

}
