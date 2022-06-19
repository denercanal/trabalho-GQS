package trabalho.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import trabalho.business.PedidoBusiness;
import trabalho.dao.ClienteDAO;
import trabalho.dao.DescontoDAO;
import trabalho.dao.ImpostoDAO;
import trabalho.dao.PedidoDAO;
import trabalho.dao.UfDAO;
import trabalho.exception.StateException;
import trabalho.state.IPedidoState;
import trabalho.state.PedidoNovo;

public class Pedido {

	private int numero;
	private LocalDateTime data;
	protected double valorTotal;
	protected double valorTotalImpostos;
	protected double valorFinalAPagar;
	protected double valorTotalDescontos;
	private String uf;
	private Cliente cliente;
	private List<Imposto> impostos = new ArrayList<Imposto>();
	private List<Desconto> descontos = new ArrayList<Desconto>();
	private List<ItemPedido> itensPedido = new ArrayList<ItemPedido>();
	protected IPedidoState estado;

	public Pedido( PedidoBusiness pedidoBusiness ) throws StateException {
		this.numero = PedidoDAO.getInstance().getQuantidadePedidos() + 1;
		this.data = LocalDateTime.now();
		this.valorTotal = 0.00;
		this.valorTotalImpostos = 0.00;
		this.valorFinalAPagar = 0.00;
		this.valorTotalDescontos = 0.00;
		this.uf = UfDAO.getInstance().buscaUfAleatorio();
		this.cliente = ClienteDAO.getInstance().buscaClienteAleatorio();
		this.impostos = ImpostoDAO.getInstance().getImpostos();
		this.descontos = DescontoDAO.getInstance().getDescontos();
		this.estado = new PedidoNovo( pedidoBusiness );
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero( int numero ) {
		this.numero = numero;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData( LocalDateTime data ) {
		this.data = data;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal( double valorTotal ) {
		this.valorTotal = valorTotal;
	}

	public double getValorTotalImpostos() {
		return valorTotalImpostos;
	}

	public void setValorTotalImpostos( double valorTotalImpostos ) {
		this.valorTotalImpostos = valorTotalImpostos;
	}

	public double getValorFinalAPagar() {
		return valorFinalAPagar;
	}

	public void setValorFinalAPagar( double valorFinalAPagar ) {
		this.valorFinalAPagar = valorFinalAPagar;
	}

	public double getValorTotalDescontos() {
		return valorTotalDescontos;
	}

	public void setValorTotalDescontos( double valorTotalDescontos ) {
		this.valorTotalDescontos = valorTotalDescontos;
	}

	public String getUf() {
		return uf;
	}

	public void setUf( String uf ) {
		this.uf = uf;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente( Cliente cliente ) {
		this.cliente = cliente;
	}

	public List<Imposto> getImpostos() {
		return impostos;
	}

	public void setImpostos( List<Imposto> impostos ) {
		this.impostos = impostos;
	}

	public List<Desconto> getDescontos() {
		return descontos;
	}

	public void setDescontos( List<Desconto> descontos ) {
		this.descontos = descontos;
	}

	public List<ItemPedido> getItensPedido() {
		return itensPedido;
	}

	public void setItensPedido( List<ItemPedido> itensPedido ) {
		this.itensPedido = itensPedido;
	}

	public IPedidoState getEstado() {
		return estado;
	}

	public void setEstado( IPedidoState estado ) {
		this.estado = estado;
	}

	public ItemPedido buscaItemPorProdutoItens( Produto produto ) {
		for( var itemPedido : this.getItensPedido() ) {
			if( itemPedido.getProduto().getId() == produto.getId() ) {
				return itemPedido;
			}
		}
		throw new RuntimeException( "Produto com o código " + produto.getId() + " não encontrado na lista de itens!" );
	}

	@Override
	public String toString() {
		return "Pedido [numero=" + numero + ", data=" + data + ", valorTotal=" + valorTotal + ", valorTotalImpostos=" + valorTotalImpostos + ", valorFinalAPagar=" + valorFinalAPagar + ", valorTotalDescontos=" + valorTotalDescontos + ", uf=" + uf + ", cliente=" + cliente + ", impostos=" + impostos
				+ ", descontos=" + descontos + ", itensPedido=" + itensPedido + ", estado=" + estado + "]";
	}

}
