package trabalho.enums;

public enum TipoDescontoEnum {

	PROMOCAO_NATAL( "Promoção de Natal" ), DESCONTO_POR_TIPO_PRODUTO_PAPELARIA( "Desconto por tipo de produto(Papelaria)" ), DESCONTO_INCENTIVO_EDUCACAO_LAPIS( "Desconto incentivo educação(Lápis)" );

	private String descricao;

	private TipoDescontoEnum( String descricao ) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao( String descricao ) {
		this.descricao = descricao;
	}

}
