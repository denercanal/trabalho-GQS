package trabalho.model;

public class Desconto {

	private String tipo;
	private double valorDesconto;
	private double percentual;

	public Desconto() {
		super();
	}

	public Desconto( String tipo, double valorDesconto, double percentual ) {
		super();
		this.tipo = tipo;
		this.valorDesconto = valorDesconto;
		this.percentual = percentual;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo( String tipo ) {
		this.tipo = tipo;
	}

	public double getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto( double valorDesconto ) {
		this.valorDesconto = valorDesconto;
	}

	public double getPercentual() {
		return percentual;
	}

	public void setPercentual( double percentual ) {
		this.percentual = percentual;
	}

}
