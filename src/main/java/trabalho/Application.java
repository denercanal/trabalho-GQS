package trabalho;

import java.time.LocalDateTime;

import trabalho.exception.StateException;
import trabalho.model.Pedido;

public class Application {

	public static void main( String[] args ) {
		var teste = new Pedido();

		teste.setData( LocalDateTime.now() );
		teste.setNumero( 0 );
		teste.setValor( 500.00 );
		teste.setValorFinalAPagar( 500.00 );
		teste.setValorTotalDescontos( 0 );
		teste.setValorTotalImpostos( 0 );

		try {
			teste.criarPedido();
			teste.concluirPedido();
			teste.sairParaEntregarPedido();
			teste.pagarPedido();
			teste.prepararPedido();
			teste.sairParaEntregarPedido();
			teste.entregarPedido();
			var avaliacao = teste.avaliarAtendimentoPedido();
			System.out.println( "Pedido realizado com sucesso! Avaliação: " + avaliacao );
		} catch ( StateException se ) {
			se.printStackTrace();
		}

	}

}
