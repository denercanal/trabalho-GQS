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

	public List<Desconto> getDescontos() {
		return Collections.unmodifiableList( this.descontos );
	}

}
