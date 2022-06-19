package trabalho.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UfDAO {

	private List<String> ufs;
	private static UfDAO instance;

	private UfDAO() {
		ufs = new ArrayList<>();
		ufs.add( "ES" );
		ufs.add( "MG" );
		ufs.add( "RJ" );
		ufs.add( "SP" );

	}

	public static UfDAO getInstance() {
		if( instance == null ) {
			instance = new UfDAO();
		}
		return instance;
	}

	public String buscaUfAleatorio() {
		Collections.shuffle( ufs );
		return ufs.get( 0 );
	}

}
