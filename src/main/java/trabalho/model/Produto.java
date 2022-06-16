package trabalho.model;

public class Produto {

	private int id;
	private String nome;
	private int quantidadeEmEstoque;
	private double precoUnitario;

	public Produto() {
		super();
	}

	public Produto( int id, String nome, int quantidadeEmEstoque, double precoUnitario ) {
		super();
		this.id = id;
		this.nome = nome;
		this.quantidadeEmEstoque = quantidadeEmEstoque;
		this.precoUnitario = precoUnitario;
	}

	public int getId() {
		return id;
	}

	public void setId( int id ) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome( String nome ) {
		this.nome = nome;
	}

	public int getQuantidadeEmEstoque() {
		return quantidadeEmEstoque;
	}

	public void setQuantidadeEmEstoque( int quantidadeEmEstoque ) {
		this.quantidadeEmEstoque = quantidadeEmEstoque;
	}

	public double getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario( double precoUnitario ) {
		this.precoUnitario = precoUnitario;
	}

	public void incrementaEstoque( double quantidade ) {
		this.quantidadeEmEstoque += quantidade;
	}

	public void decrementaEstoque( double quantidade ) {
		this.quantidadeEmEstoque -= quantidade;
	}

}
