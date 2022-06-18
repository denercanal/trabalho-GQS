package trabalho.state;

import java.util.List;

import trabalho.enums.TipoDescontoEnum;
import trabalho.exception.StateException;
import trabalho.model.Desconto;
import trabalho.model.Imposto;
import trabalho.model.ItemPedido;
import trabalho.model.Pedido;

public class PedidoNovo implements IPedidoState {

	@Override
	public IPedidoState criarPedido() throws StateException {
		return this;
	}

	@Override
	public List<ItemPedido> incluirRemoverItemPedido( List<ItemPedido> itensPedido ) throws StateException {
		return new PedidoIncluirRemoverItem().incluirRemoverItemPedido( itensPedido );
	}

	@Override
	public IPedidoState concluirPedido( Pedido pedido ) throws StateException {
		var impostos = pedido.getImpostos();
		var descontos = pedido.getDescontos();
		var itensPedido = pedido.getItensPedido();

		var totalAPagar = calcularValorTotalAPagar( itensPedido );

		var totalImposto = calcularImpostos( impostos, totalAPagar );
		pedido.setValorTotalImpostos( totalImposto );
		var totalDesconto = calcularDescontos( descontos, totalAPagar );
		pedido.setValorTotalDescontos( totalDesconto );

		pedido.setValorFinalAPagar( totalAPagar + pedido.getValorTotalImpostos() - pedido.getValorTotalDescontos() );

		System.out.println( "O valor de imposto é: " + pedido.getValorTotalImpostos() );
		System.out.println( "O valor de desconto é: " + pedido.getValorTotalDescontos() );
		System.out.println( "O valor total a pagar é: " + pedido.getValorFinalAPagar() );
		return new PedidoAguardandoPagamento();
	}

	@Override
	public IPedidoState cancelarPedido() throws StateException {
		return new PedidoCanceladoPeloCliente();
	}

	@Override
	public IPedidoState pagarPedido( Pedido pedido ) throws StateException {
		throw new StateException( "Não é possível pagar o Pedido no estado Novo. Deve-se concluir/cancelar o Pedido primeiro." );
	}

	@Override
	public IPedidoState prepararPedido() throws StateException {
		throw new StateException( "Não é possível preparar o Pedido no estado Novo. Deve-se concluir/cancelar o Pedido primeiro." );
	}

	@Override
	public IPedidoState sairParaEntregarPedido() throws StateException {
		throw new StateException( "Não é possível sair para entregar o Pedido no estado Novo. Deve-se concluir/cancelar o Pedido primeiro." );
	}

	@Override
	public IPedidoState entregarPedido() throws StateException {
		throw new StateException( "Não é possível entregar o Pedido no estado Novo. Deve-se concluir/cancelar o Pedido primeiro." );
	}

	@Override
	public IPedidoState reembolsarPedido() throws StateException {
		throw new StateException( "Não é possível reembolsar/repor o estoque do Pedido no estado Novo. Deve-se concluir/cancelar o Pedido primeiro." );
	}

	@Override
	public int avaliarAtendimentoPedido() throws StateException {
		throw new StateException( "Não é possível avaliar o atendimento do Pedido no estado Novo. Deve-se concluir/cancelar o Pedido primeiro." );
	}

	private double calcularImpostos( List<Imposto> impostos, double totalAPagar ) {
		var imposto1 = new Imposto();
		imposto1.setNome( "ISS" );
		imposto1.setPercentual( 5.00 );
		imposto1.setValor( totalAPagar * ( imposto1.getPercentual() / 100 ) );
		impostos.add( imposto1 );
		var imposto2 = new Imposto();
		imposto2.setNome( "ICMS" );
		imposto2.setPercentual( 7.00 );
		imposto2.setValor( totalAPagar * ( imposto2.getPercentual() / 100 ) );
		impostos.add( imposto2 );

		var totalImposto = 0.00;
		for( var imposto : impostos ) {
			totalImposto += imposto.getValor();
		}

		return totalImposto;
	}

	private double calcularDescontos( List<Desconto> descontos, double totalAPagar ) {
		var desconto1 = new Desconto();
		desconto1.setPercentual( 3.00 );
		desconto1.setValorDesconto( totalAPagar * ( desconto1.getPercentual() / 100 ) );
		desconto1.setTipo( TipoDescontoEnum.PROMOCAO_NATAL );
		descontos.add( desconto1 );
		var desconto2 = new Desconto();
		desconto2.setPercentual( 1.00 );
		desconto2.setValorDesconto( totalAPagar * ( desconto2.getPercentual() / 100 ) );
		desconto2.setTipo( TipoDescontoEnum.DESCONTO_POR_TIPO_PRODUTO_PAPELARIA );
		descontos.add( desconto2 );
		var desconto3 = new Desconto();
		desconto3.setPercentual( 0.50 );
		desconto3.setValorDesconto( totalAPagar * ( desconto3.getPercentual() / 100 ) );
		desconto3.setTipo( TipoDescontoEnum.DESCONTO_INCENTIVO_EDUCACAO_LAPIS );
		descontos.add( desconto3 );
		var totalDesconto = 0.00;
		for( var desconto : descontos ) {
			totalDesconto += desconto.getValorDesconto();
		}
		return totalDesconto;
	}

	private double calcularValorTotalAPagar( List<ItemPedido> itensPedido ) {
		var totalAPagar = 0.00;
		for( var itemPedido : itensPedido ) {
			totalAPagar += itemPedido.getValorTotal();
		}
		return totalAPagar;
	}

}
