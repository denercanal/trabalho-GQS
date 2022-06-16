package trabalho.state;

import trabalho.exception.StateException;

public interface IPedidoState {

	public IPedidoState criarPedido() throws StateException;

	public IPedidoState concluirPedido() throws StateException;

	public IPedidoState cancelarPedido() throws StateException;

	public IPedidoState pagarPedido() throws StateException;

	public IPedidoState prepararPedido() throws StateException;

	public IPedidoState enviarPedido() throws StateException;

	public IPedidoState entregarPedido() throws StateException;

	public IPedidoState reembolsarPedido() throws StateException;

	public IPedidoState finalizarPedido() throws StateException;

}
