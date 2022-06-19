package trabalho.enums;

public enum TipoCestaEnum {

	CESTA_BASICA( "Cesta Básica" ), CESTA_ECONOMICA( "Cesta Econômica" ), CESTA_TOP( "Cesta Top" );

	private String descricao;

	private TipoCestaEnum( String descricao ) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao( String descricao ) {
		this.descricao = descricao;
	}

}
