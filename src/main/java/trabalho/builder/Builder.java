package trabalho.builder;

public class Builder {

	private ICestaBuilder builder;

	public Builder( ICestaBuilder builder ) {
		this.builder = builder;
	}

	public ICestaBuilder getBuilder() {
		return builder;
	}

	public void builderCesta() {
		builder.adicionarGraos();
		builder.adicionarIndustrializados();
		builder.adicionarLegumesFrutas();
		builder.adicionarOrigemAnimal();
	}
}
