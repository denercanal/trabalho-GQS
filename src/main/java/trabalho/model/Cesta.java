package trabalho.model;

import java.util.ArrayList;
import java.util.List;

import trabalho.enums.TipoCestaEnum;

public class Cesta {

	private List<ItemPedido> itensPedido = new ArrayList<>();
	private TipoCestaEnum tipoCesta;

	public Cesta() {}

	public Cesta( List<ItemPedido> itensPedido, TipoCestaEnum tipoCesta ) {
		this.itensPedido = itensPedido;
		this.tipoCesta = tipoCesta;
	}

	public List<ItemPedido> getItensPedido() {
		return itensPedido;
	}

	public void setItensPedido( List<ItemPedido> itensPedido ) {
		this.itensPedido = itensPedido;
	}

	public TipoCestaEnum getTipoCesta() {
		return tipoCesta;
	}

	public void setTipoCesta( TipoCestaEnum tipoCesta ) {
		this.tipoCesta = tipoCesta;
	}

}
