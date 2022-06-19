package trabalho.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import trabalho.model.Imposto;

public class ImpostoDAO {

	private ArrayList<Imposto> impostos;
	private static ImpostoDAO instance;

	private ImpostoDAO() {
		impostos = new ArrayList<>();
		impostos.add( new Imposto( "ISS", 2.00 ) );
		impostos.add( new Imposto( "ICMS", 7.00 ) );
	}

	public static ImpostoDAO getInstance() {
		if( instance == null ) {
			instance = new ImpostoDAO();
		}
		return instance;
	}

	public void adicionaImposto( Imposto imposto ) {
		if( this.getImpostos().contains( imposto ) ) {
			throw new RuntimeException( "Imposto " + imposto.getNome() + " já existente na lista!" );
		}
		impostos.add( imposto );
	}

	public void removeImposto( Imposto imposto ) {
		if( !this.getImpostos().contains( imposto ) ) {
			throw new RuntimeException( "Imposto " + imposto.getNome() + " não existente na lista!" );
		}
		impostos.remove( imposto );
	}

	public Imposto buscaImpostoPorNome( String nome ) {
		return impostos.stream().filter( i -> i.getNome().equalsIgnoreCase( nome ) ).findFirst().orElseThrow();
	}

	public List<Imposto> getImpostos() {
		return Collections.unmodifiableList( impostos );
	}

	public int getQuantidadeImpostos() {
		return impostos.size();
	}
}
