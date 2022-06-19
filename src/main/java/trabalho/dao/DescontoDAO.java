package trabalho.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import trabalho.enums.TipoDescontoEnum;
import trabalho.model.Desconto;

public class DescontoDAO {

	private List<Desconto> descontos;
	private static DescontoDAO instance;

	private DescontoDAO() {
		descontos = new ArrayList<>();
		descontos.add( new Desconto( TipoDescontoEnum.PROMOCAO_NATAL, 3 ) );
		descontos.add( new Desconto( TipoDescontoEnum.DESCONTO_POR_TIPO_PRODUTO_PAPELARIA, 1 ) );
		descontos.add( new Desconto( TipoDescontoEnum.DESCONTO_INCENTIVO_EDUCACAO_LAPIS, 0.5 ) );
	}

	public static DescontoDAO getInstance() {
		if( instance == null ) {
			instance = new DescontoDAO();
		}
		return instance;
	}

	public void adicionaDesconto( Desconto desconto ) {
		if( this.getDescontos().contains( desconto ) ) {
			throw new RuntimeException( "Desconto " + desconto.getTipo() + " já existente na lista!" );
		}
		this.descontos.add( desconto );
	}

	public void removeDesconto( Desconto desconto ) {
		if( !this.getDescontos().contains( desconto ) ) {
			throw new RuntimeException( "Desconto " + desconto.getTipo() + " não existente na lista!" );
		}
		this.descontos.remove( desconto );
	}

	public Desconto buscaImpostoPorNome( String nome ) {
		return descontos.stream().filter( i -> i.getTipo().name().equalsIgnoreCase( nome ) ).findFirst().orElseThrow();
	}

	public List<Desconto> getDescontos() {
		return Collections.unmodifiableList( this.descontos );
	}

	public int getQuantidadeDescontos() {
		return this.descontos.size();
	}

}
