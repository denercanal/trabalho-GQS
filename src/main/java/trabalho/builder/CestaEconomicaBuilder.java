package trabalho.builder;

import java.util.ArrayList;
import java.util.List;

import trabalho.dao.ProdutoDAO;
import trabalho.enums.TipoCestaEnum;
import trabalho.model.Cesta;
import trabalho.model.ItemPedido;

public class CestaEconomicaBuilder implements ICestaBuilder {

	private Cesta cesta;
	private List<ItemPedido> itensPedido;

	public CestaEconomicaBuilder() {
		this.cesta = new Cesta();
		this.itensPedido = new ArrayList<>();
		cesta.setTipoCesta( TipoCestaEnum.CESTA_ECONOMICA );
	}

	@Override
	public Cesta getCesta() {
		return cesta;
	}

	@Override
	public void adicionarOrigemAnimal() {
		itensPedido.add( new ItemPedido( ProdutoDAO.getInstance().buscaProdutoPorId( 30 ), 1 ) );
		cesta.setItensPedido( itensPedido );
	}

	@Override
	public void adicionarGraos() {
		itensPedido.add( new ItemPedido( ProdutoDAO.getInstance().buscaProdutoPorId( 3 ), 1 ) );
		itensPedido.add( new ItemPedido( ProdutoDAO.getInstance().buscaProdutoPorId( 6 ), 1 ) );
		itensPedido.add( new ItemPedido( ProdutoDAO.getInstance().buscaProdutoPorId( 9 ), 1 ) );
		itensPedido.add( new ItemPedido( ProdutoDAO.getInstance().buscaProdutoPorId( 11 ), 1 ) );
		cesta.setItensPedido( itensPedido );
	}

	@Override
	public void adicionarIndustrializados() {
		itensPedido.add( new ItemPedido( ProdutoDAO.getInstance().buscaProdutoPorId( 1 ), 1 ) );
		itensPedido.add( new ItemPedido( ProdutoDAO.getInstance().buscaProdutoPorId( 2 ), 1 ) );
		itensPedido.add( new ItemPedido( ProdutoDAO.getInstance().buscaProdutoPorId( 4 ), 1 ) );
		itensPedido.add( new ItemPedido( ProdutoDAO.getInstance().buscaProdutoPorId( 13 ), 1 ) );
		itensPedido.add( new ItemPedido( ProdutoDAO.getInstance().buscaProdutoPorId( 14 ), 1 ) );
		itensPedido.add( new ItemPedido( ProdutoDAO.getInstance().buscaProdutoPorId( 17 ), 1 ) );
		itensPedido.add( new ItemPedido( ProdutoDAO.getInstance().buscaProdutoPorId( 20 ), 1 ) );
		cesta.setItensPedido( itensPedido );
	}

	@Override
	public void adicionarLegumesFrutas() {
		itensPedido.add( new ItemPedido( ProdutoDAO.getInstance().buscaProdutoPorId( 10 ), 1 ) );
		itensPedido.add( new ItemPedido( ProdutoDAO.getInstance().buscaProdutoPorId( 19 ), 1 ) );
		cesta.setItensPedido( itensPedido );
	}

}
