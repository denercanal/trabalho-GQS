package trabalho.model;

import trabalho.enums.TipoDescontoEnum;

public class Desconto {

	private TipoDescontoEnum tipo;
	private double valorDesconto;
	private double percentual;

	public Desconto() {}

	public Desconto( TipoDescontoEnum tipo, double percentual ) {
		this.tipo = tipo;
		this.valorDesconto = 0;
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
