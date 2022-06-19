package trabalho.state;

import trabalho.exception.StateException;

public interface IPedidoState {

	public IPedidoState criarPedido() throws StateException;

	public void incluirItemPedido( int idProduto, double quantidade ) throws StateException;

	public void removerItemPedido( int idProduto, double quantidade ) throws StateException;

	public IPedidoState concluirPedido() throws StateException;

	public IPedidoState cancelarPedido() throws StateException;

	public IPedidoState pagarPedido() throws StateException;

	public IPedidoState prepararPedido() throws StateException;

	public IPedidoState sairParaEntregarPedido() throws StateException;

	public IPedidoState entregarPedido() throws StateException;

	public IPedidoState reembolsarPedido() throws StateException;

	public void avaliarAtendimentoPedido() throws StateException;

}
