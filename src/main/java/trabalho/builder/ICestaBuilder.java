package trabalho.builder;

import trabalho.model.Cesta;

public interface ICestaBuilder {

	public Cesta getCesta();

	public void adicionarOrigemAnimal();

	public void adicionarGraos();

	public void adicionarIndustrializados();

	public void adicionarLegumesFrutas();
}
