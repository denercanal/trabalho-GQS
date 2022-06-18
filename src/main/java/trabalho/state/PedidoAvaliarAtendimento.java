package trabalho.state;

import java.util.List;
import java.util.Scanner;

import trabalho.exception.StateException;
import trabalho.model.ItemPedido;
import trabalho.model.Pedido;

public class PedidoAvaliarAtendimento implements IPedidoState {

	@Override
	public IPedidoState criarPedido() throws StateException {
		throw new StateException( "Não é possível criar o Pedido no estado Avaliar o Atendimento. Deve-se pagar o Pedido primeiro." );
	}

	@Override
	public List<ItemPedido> incluirRemoverItemPedido( List<ItemPedido> itensPedido ) throws StateException {
		throw new StateException( "Não é possível incluir/remover item do Pedido no estado Aguardando Pagamento. Deve-se pagar/cancelar o Pedido primeiro." );
	}

	@Override
	public IPedidoState concluirPedido( Pedido pedido ) throws StateException {
		throw new StateException( "Não é possível avaliar o atendimento do Pedido no estado Aguardando Pagamento. Deve-se pagar o Pedido primeiro." );
	}

	@Override
	public IPedidoState cancelarPedido() throws StateException {
		throw new StateException( "Não é possível avaliar o atendimento do Pedido no estado Aguardando Pagamento. Deve-se pagar o Pedido primeiro." );
	}

	@Override
	public IPedidoState pagarPedido( Pedido pedido ) throws StateException {
		throw new StateException( "Não é possível avaliar o atendimento do Pedido no estado Aguardando Pagamento. Deve-se pagar o Pedido primeiro." );
	}

	@Override
	public IPedidoState prepararPedido() throws StateException {
		throw new StateException( "Não é possível avaliar o atendimento do Pedido no estado Aguardando Pagamento. Deve-se pagar o Pedido primeiro." );
	}

	@Override
	public IPedidoState sairParaEntregarPedido() throws StateException {
		throw new StateException( "Não é possível avaliar o atendimento do Pedido no estado Aguardando Pagamento. Deve-se pagar o Pedido primeiro." );
	}

	@Override
	public IPedidoState entregarPedido() throws StateException {
		throw new StateException( "Não é possível avaliar o atendimento do Pedido no estado Aguardando Pagamento. Deve-se pagar o Pedido primeiro." );
	}

	@Override
	public IPedidoState reembolsarPedido() throws StateException {
		throw new StateException( "Não é possível avaliar o atendimento do Pedido no estado Aguardando Pagamento. Deve-se pagar o Pedido primeiro." );
	}

	@Override
	public int avaliarAtendimentoPedido() throws StateException {
		System.out.println( "Por favor, avalie o Atendimento do Pedido: de 0 a 5." );
		var sc = new Scanner( System.in );
		int avaliacao;
		do {
			System.out.println( "Insira um número de 0 a 5!" );
//			avaliacao = sc.nextInt();
			avaliacao = 5;
		} while( avaliacao < 0 || avaliacao > 5 );

		sc.close();
		return avaliacao;
	}

}
