package trabalho;

import java.time.LocalDateTime;

import trabalho.exception.OperacaoInvalidaException;
import trabalho.exception.StateException;
import trabalho.model.Cliente;
import trabalho.model.Pedido;

public class Application {

	public static void main( String[] args ) {
		var pedido = new Pedido();

		pedido.setCliente( new Cliente( "Fulando de Tal" ) );

		try {
			var listaItemPedido = pedido.incluirRemoverItemPedido( pedido.getItensPedido() );
			pedido.setItensPedido( listaItemPedido );
		} catch ( OperacaoInvalidaException | StateException e ) {
			e.printStackTrace();
		}

		pedido.setData( LocalDateTime.parse( "2022-02-15T10:28:00" ) );
		pedido.setNumero( 0001 );
		pedido.setUf( "MG" );
		pedido.setValor( 500.00 );
		pedido.setValorFinalAPagar( 500.00 );
		pedido.setValorTotalDescontos( 0 );
		pedido.setValorTotalImpostos( 0 );

		try {
			pedido.criarPedido();
			pedido.concluirPedido( pedido );
			pedido.pagarPedido();
			pedido.prepararPedido();
			pedido.sairParaEntregarPedido();
			pedido.entregarPedido();
			var avaliacao = pedido.avaliarAtendimentoPedido();
			System.out.println( "Pedido realizado com sucesso! Avaliação: " + avaliacao );
		} catch ( StateException se ) {
			se.printStackTrace();
		} catch ( OperacaoInvalidaException oie ) {
			oie.printStackTrace();
		}

	}

}
