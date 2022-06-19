package trabalho.model;

public class ItemPedido {

	private double quantidade;
	private double valorUnitario;
	private double valorTotal;
	private Produto produto;

	public ItemPedido() {}

	public ItemPedido( Produto produto, double quantidade ) {
		this.produto = produto;
		this.quantidade = quantidade;
		this.valorUnitario = produto.getPrecoUnitario();
		this.valorTotal = this.quantidade * this.valorUnitario;
	}

	public double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade( double quantidade ) {
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

	public void aumentarQuantidade( double quantidade ) {
		if( quantidade > 0 ) {
			this.quantidade += quantidade;
		} else {
			throw new RuntimeException( "Não pode-se adicionar 0 ou menos elementos no pedido!" );
		}
	}

	public void diminuirQuantidade( double quantidade ) {
		if( quantidade <= this.quantidade ) {
			this.quantidade -= quantidade;
		} else {
			throw new RuntimeException( "Não se pode remover um valor maior que existe no pedido!" );
		}
	}

}
