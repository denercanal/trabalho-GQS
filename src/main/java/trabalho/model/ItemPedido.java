package trabalho.model;

public class ItemPedido {

	private int quantidade;
	private double valorUnitario;
	private double valorTotal;
	private Produto produto;

	public ItemPedido() {
		super();
	}

	public ItemPedido( int quantidade, double valorUnitario, double valorTotal, Produto produto ) {
		super();
		this.quantidade = quantidade;
		this.valorUnitario = valorUnitario;
		this.valorTotal = valorTotal;
		this.produto = produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade( int quantidade ) {
		this.quantidade = quantidade;
	}

	public double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario( double valorUnitario ) {
		this.valorUnitario = valorUnitario;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal( double valorTotal ) {
		this.valorTotal = valorTotal;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto( Produto produto ) {
		this.produto = produto;
	}

}
