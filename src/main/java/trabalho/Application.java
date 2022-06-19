package trabalho;

import trabalho.business.PedidoBusiness;
import trabalho.dao.PedidoDAO;
import trabalho.dao.ProdutoDAO;
import trabalho.enums.TipoCestaEnum;
import trabalho.exception.OperacaoInvalidaException;
import trabalho.exception.StateException;

public class Application {

	public static void main( String[] args ) throws OperacaoInvalidaException, StateException {
//		var pedidoBusiness1 = new PedidoBusiness();
//		pedidoBusiness1.incluirItemPedido( 1, 1 );
//		pedidoBusiness1.incluirCesta( TipoCestaEnum.CESTA_BASICA );
//		pedidoBusiness1.incluirItemPedido( 2, 1 );
//		pedidoBusiness1.incluirCesta( TipoCestaEnum.CESTA_TOP );
//		pedidoBusiness1.incluirCesta( TipoCestaEnum.CESTA_ECONOMICA );
//		pedidoBusiness1.removerItemPedido( 1, 1 );
//		pedidoBusiness1.concluirPedido();
//		pedidoBusiness1.cancelarPedido();
//		pedidoBusiness1.reembolsarPedido();
//		pedidoBusiness1.avaliarAtendimentoPedido();
//
		var pedidoBusiness2 = new PedidoBusiness();
		pedidoBusiness2.incluirItemPedido( 1, 1 );
		pedidoBusiness2.incluirCesta( TipoCestaEnum.CESTA_TOP );
		pedidoBusiness2.incluirCesta( TipoCestaEnum.CESTA_ECONOMICA );
		pedidoBusiness2.concluirPedido();
		pedidoBusiness2.pagarPedido();
		pedidoBusiness2.prepararPedido();
		pedidoBusiness2.sairParaEntregarPedido();
		pedidoBusiness2.entregarPedido();
		pedidoBusiness2.avaliarAtendimentoPedido();
		ProdutoDAO.getInstance().getProdutos().stream().forEach( System.out::println );
		PedidoDAO.getInstance().getPedidos().stream().forEach( System.out::println );
	}

}
