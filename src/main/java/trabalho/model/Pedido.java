package trabalho.model;

import java.time.LocalDateTime;

import trabalho.state.IPedidoState;
import trabalho.state.PedidoNovo;

public class Pedido {

	private int numero;
	private LocalDateTime data;
	private double valor;
	private double valorTotalImpostos;
	private double valorFinalAPagar;
	private double valorTotalDescontos;
	protected IPedidoState estado;

	public Pedido() {
		super();
		this.estado = new PedidoNovo();
	}

	public Pedido( int numero, LocalDateTime data, double valor, double valorTotalImpostos, double valorFinalAPagar, double valorTotalDescontos ) {
		super();
		this.numero = numero;
		this.data = data;
		this.valor = valor;
		this.valorTotalImpostos = valorTotalImpostos;
		this.valorFinalAPagar = valorFinalAPagar;
		this.valorTotalDescontos = valorTotalDescontos;
		this.estado = new PedidoNovo();
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

	public double getValor() {
		return valor;
	}

	public void setValor( double valor ) {
		this.valor = valor;
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

}
