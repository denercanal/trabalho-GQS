package trabalho.model;

import trabalho.enums.TipoDescontoEnum;

public class Desconto {

	private TipoDescontoEnum tipo;
	private double valorDesconto;
	private double percentual;

	public Desconto() {
		super();
	}

	public Desconto( TipoDescontoEnum tipo, double valorDesconto, double percentual ) {
		super();
		this.tipo = tipo;
		this.valorDesconto = valorDesconto;
		this.percentual = percentual;
	}

	public TipoDescontoEnum getTipo() {
		return tipo;
	}

	public void setTipo( TipoDescontoEnum tipo ) {
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
