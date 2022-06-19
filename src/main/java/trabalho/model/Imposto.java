package trabalho.model;

public class Imposto {

	private String nome;
	private double percentual;
	private double valor;

	public Imposto() {}

	public Imposto( String nome, double percentual ) {
		this.nome = nome;
		this.percentual = percentual;
		this.valor = 0;
	}

	public String getNome() {
		return nome;
	}

	public void setNome( String nome ) {
		this.nome = nome;
	}

	public double getPercentual() {
		return percentual;
	}

	public void setPercentual( double percentual ) {
		this.percentual = percentual;
	}

	public double getValor() {
		return valor;
	}

	public void setValor( double valor ) {
		this.valor = valor;
	}

}
