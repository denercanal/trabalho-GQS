package trabalho.model;

import java.time.LocalDateTime;

import trabalho.exception.OperacaoInvalidaException;
import trabalho.exception.StateException;
import trabalho.state.IPedidoState;
import trabalho.state.PedidoAguardandoPagamento;
import trabalho.state.PedidoCanceladoPeloCliente;
import trabalho.state.PedidoCanceladoPeloEstabelecimento;
import trabalho.state.PedidoConfirmado;
import trabalho.state.PedidoEmRotaDeEntrega;
import trabalho.state.PedidoEntregue;
import trabalho.state.PedidoIncluirRemoverItem;
import trabalho.state.PedidoNovo;
import trabalho.state.PedidoProntoParaEntrega;
import trabalho.state.PedidoReembolsado;

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

	public void criarPedido() throws StateException {
		if( this.estado == null ) {
			this.estado = new PedidoNovo().criarPedido();
		}
	}

	public void incluirRemoverItemPedido() throws StateException {
		if( !( this.estado instanceof PedidoIncluirRemoverItem ) ) {
			throw new OperacaoInvalidaException( "Operação inválida!" );
		}
		this.estado = new PedidoIncluirRemoverItem().incluirRemoverItemPedido();
	}

	public void concluirPedido() throws StateException {
		if( !( this.estado instanceof PedidoNovo ) ) {
			throw new OperacaoInvalidaException( "Operação inválida!" );
		}
		this.estado = new PedidoNovo().concluirPedido();
	}

	public void cancelarPedido() throws StateException {
		if( !( this.estado instanceof PedidoNovo ) || !( this.estado instanceof PedidoAguardandoPagamento ) || !( this.estado instanceof PedidoProntoParaEntrega ) || !( this.estado instanceof PedidoConfirmado ) ) {
			throw new OperacaoInvalidaException( "Operação inválida!" );
		}
		if( this.estado instanceof PedidoNovo ) {
			this.estado = new PedidoNovo().cancelarPedido();
		}

		if( this.estado instanceof PedidoAguardandoPagamento ) {
			this.estado = new PedidoAguardandoPagamento().cancelarPedido();
		}

		if( this.estado instanceof PedidoProntoParaEntrega ) {
			this.estado = new PedidoProntoParaEntrega().cancelarPedido();
		}

		if( this.estado instanceof PedidoConfirmado ) {
			this.estado = new PedidoConfirmado().cancelarPedido();
		}
	}

	public void pagarPedido() throws StateException {
		if( !( this.estado instanceof PedidoAguardandoPagamento ) ) {
			throw new OperacaoInvalidaException( "Operação inválida!" );
		}
		this.estado = new PedidoAguardandoPagamento().pagarPedido();
	}

	public void prepararPedido() throws StateException {
		if( !( this.estado instanceof PedidoConfirmado ) ) {
			throw new OperacaoInvalidaException( "Operação inválida!" );
		}
		this.estado = new PedidoConfirmado().prepararPedido();
	}

	public void sairParaEntregarPedido() throws StateException {
		if( !( this.estado instanceof PedidoProntoParaEntrega ) ) {
			throw new OperacaoInvalidaException( "Operação inválida!" );
		}
		this.estado = new PedidoProntoParaEntrega().sairParaEntregarPedido();
	}

	public void entregarPedido() throws StateException {
		if( !( this.estado instanceof PedidoEmRotaDeEntrega ) ) {
			throw new OperacaoInvalidaException( "Operação inválida!" );
		}
		this.estado = new PedidoEmRotaDeEntrega().entregarPedido();
	}

	public void reembolsarPedido() throws StateException {
		if( !( this.estado instanceof PedidoCanceladoPeloEstabelecimento ) || !( this.estado instanceof PedidoCanceladoPeloCliente ) ) {
			throw new OperacaoInvalidaException( "Operação inválida!" );
		}
		if( this.estado instanceof PedidoCanceladoPeloEstabelecimento ) {
			this.estado = new PedidoCanceladoPeloEstabelecimento().reembolsarPedido();
		}

		if( this.estado instanceof PedidoCanceladoPeloCliente ) {
			this.estado = new PedidoCanceladoPeloCliente().reembolsarPedido();
		}
	}

	public int avaliarAtendimentoPedido() throws StateException {
		if( !( this.estado instanceof PedidoEntregue ) || !( this.estado instanceof PedidoReembolsado ) ) {
			throw new OperacaoInvalidaException( "Operação inválida!" );
		}
		if( this.estado instanceof PedidoEntregue ) {
			return new PedidoEntregue().avaliarAtendimentoPedido();
		}

		if( this.estado instanceof PedidoReembolsado ) {
			return new PedidoReembolsado().avaliarAtendimentoPedido();
		}
		throw new OperacaoInvalidaException( "Operação inválida!" );
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
