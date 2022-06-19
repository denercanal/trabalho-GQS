package trabalho.builder;

public class Builder {

	private ICestaBuilder iCestaBuilder;

	public Builder( ICestaBuilder builder ) {
		this.iCestaBuilder = builder;
	}

	public ICestaBuilder getBuilder() {
		return iCestaBuilder;
	}

	public void builderCesta() {
		iCestaBuilder.adicionarGraos();
		iCestaBuilder.adicionarIndustrializados();
		iCestaBuilder.adicionarLegumesFrutas();
		iCestaBuilder.adicionarOrigemAnimal();
	}
}
