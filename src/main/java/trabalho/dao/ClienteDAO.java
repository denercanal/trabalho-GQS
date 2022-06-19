package trabalho.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import trabalho.exception.DAOException;
import trabalho.model.Cliente;

public class ClienteDAO {

	private List<Cliente> clientes;
	private static ClienteDAO instance;

	private ClienteDAO() {
		clientes = new ArrayList<>();
		clientes.add( new Cliente( "Dener0" ) );
		clientes.add( new Cliente( "Dener1" ) );
		clientes.add( new Cliente( "Dener2" ) );
		clientes.add( new Cliente( "Dener3" ) );
		clientes.add( new Cliente( "Dener4" ) );
	}

	public static ClienteDAO getInstance() {
		if( instance == null ) {
			instance = new ClienteDAO();
		}
		return instance;
	}

	public void adicionaCliente( Cliente cliente ) {
		if( this.getClientes().contains( cliente ) ) {
			throw new DAOException( "Cliente " + cliente.getNome() + " já existente na lista!" );
		}
		this.clientes.add( cliente );
	}

	public Cliente buscaClientePorNome( String nome ) {
		return clientes.stream().filter( c -> c.getNome().equalsIgnoreCase( nome ) ).findFirst().orElseThrow();
	}

	public Cliente buscaClienteAleatorio() {
		Collections.shuffle( clientes );
		return clientes.get( 0 );
	}

	public List<Cliente> getClientes() {
		return Collections.unmodifiableList( this.clientes );
	}

	public int getQuantidadeClientes() {
		return this.clientes.size();
	}
}
