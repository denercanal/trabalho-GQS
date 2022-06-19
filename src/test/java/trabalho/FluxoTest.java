package trabalho;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import trabalho.business.PedidoBusiness;
import trabalho.enums.TipoCestaEnum;
import trabalho.exception.OperacaoInvalidaException;
import trabalho.exception.StateException;

public class FluxoTest {

	@Nested
	@DisplayName( "Teste 1" )
	class Teste1 {

		@Test
		public void testeFluxoBusiness() throws OperacaoInvalidaException, StateException {
			PedidoBusiness pedidoBusiness = new PedidoBusiness();
			pedidoBusiness.incluirItemPedido( 5, 2 );
			pedidoBusiness.removerItemPedido( 5, 1 );
			pedidoBusiness.incluirCesta( TipoCestaEnum.CESTA_BASICA );
			pedidoBusiness.concluirPedido();
			pedidoBusiness.pagarPedido();
			pedidoBusiness.prepararPedido();
			pedidoBusiness.sairParaEntregarPedido();
			pedidoBusiness.entregarPedido();
			pedidoBusiness.avaliarAtendimentoPedido();
		}
	}

	@Nested
	@DisplayName( "Teste 2" )
	class Teste2 {

		@Test
		public void testeFluxoCancelarCliente01() throws OperacaoInvalidaException, StateException {
			PedidoBusiness pedidoBusiness = new PedidoBusiness();
			pedidoBusiness.incluirCesta( TipoCestaEnum.CESTA_ECONOMICA );
			pedidoBusiness.cancelarPedido();
			pedidoBusiness.reembolsarPedido();
			pedidoBusiness.avaliarAtendimentoPedido();
		}
	}

	@Nested
	@DisplayName( "Teste 3" )
	class Teste3 {

		@Test
		public void testeFluxoCancelarEstabelecimento01() throws OperacaoInvalidaException, StateException {
			PedidoBusiness pedidoBusiness = new PedidoBusiness();
			pedidoBusiness.incluirCesta( TipoCestaEnum.CESTA_TOP );
			pedidoBusiness.concluirPedido();
			pedidoBusiness.pagarPedido();
			pedidoBusiness.cancelarPedido();
			pedidoBusiness.reembolsarPedido();
			pedidoBusiness.avaliarAtendimentoPedido();
		}
	}

	@Nested
	@DisplayName( "Teste 4" )
	class Teste4 {

		@Test
		public void testeFluxoCancelarEstabelecimento02() throws OperacaoInvalidaException, StateException {
			PedidoBusiness pedidoBusiness = new PedidoBusiness();
			pedidoBusiness.incluirCesta( TipoCestaEnum.CESTA_BASICA );
			pedidoBusiness.concluirPedido();
			pedidoBusiness.pagarPedido();
			pedidoBusiness.prepararPedido();
			pedidoBusiness.cancelarPedido();
			pedidoBusiness.reembolsarPedido();
			pedidoBusiness.avaliarAtendimentoPedido();
		}
	}

	@Nested
	@DisplayName( "Teste 5" )
	class Teste5 {

		@Test
		public void testeFluxoCancelarEstabelecimento01() throws OperacaoInvalidaException, StateException {
			PedidoBusiness pedidoBusiness = new PedidoBusiness();
			pedidoBusiness.incluirCesta( TipoCestaEnum.CESTA_ECONOMICA );
			pedidoBusiness.concluirPedido();
			pedidoBusiness.cancelarPedido();
			pedidoBusiness.reembolsarPedido();
			pedidoBusiness.avaliarAtendimentoPedido();
		}
	}

}
