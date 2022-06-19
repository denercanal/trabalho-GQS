package trabalho.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import trabalho.exception.DAOException;
import trabalho.model.Pedido;

public class PedidoDAO {

	private List<Pedido> pedidos;
	private static PedidoDAO instance;

	private PedidoDAO() {
		this.pedidos = new ArrayList<>();
	}

	public static PedidoDAO getInstance() {
		if( instance == null ) {
			instance = new PedidoDAO();
		}
		return instance;
	}

	public void adicionaPedido( Pedido pedido ) {
		if( this.getPedidos().contains( pedido ) ) {
			throw new DAOException( "Pedido " + pedido.getNumero() + " já existente na lista!" );
		}
		pedidos.add( pedido );
	}

	public Pedido buscaPedidoPorNumero( int numero ) {
		return pedidos.stream().filter( p -> p.getNumero() == numero ).findFirst().orElseThrow();
	}

	public List<Pedido> getPedidos() {
		return Collections.unmodifiableList( pedidos );
	}

	public int getQuantidadePedidos() {
		return pedidos.size();
	}

}
