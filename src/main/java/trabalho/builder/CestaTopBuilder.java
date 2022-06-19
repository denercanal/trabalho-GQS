package trabalho.builder;

import java.util.ArrayList;
import java.util.List;

import trabalho.dao.ProdutoDAO;
import trabalho.enums.TipoCestaEnum;
import trabalho.model.Cesta;
import trabalho.model.ItemPedido;

public class CestaTopBuilder implements ICestaBuilder {

	private Cesta cesta;
	private List<ItemPedido> itensPedido;

	public CestaTopBuilder() {
		this.cesta = new Cesta();
		this.itensPedido = new ArrayList<>();
		cesta.setTipoCesta( TipoCestaEnum.CESTA_TOP );
	}

	@Override
	public Cesta getCesta() {
		return cesta;
	}

	@Override
	public void adicionarOrigemAnimal() {
		itensPedido.add( new ItemPedido( ProdutoDAO.getInstance().buscaProdutoPorId( 28 ), 1 ) );
		itensPedido.add( new ItemPedido( ProdutoDAO.getInstance().buscaProdutoPorId( 21 ), 1 ) );
		itensPedido.add( new ItemPedido( ProdutoDAO.getInstance().buscaProdutoPorId( 32 ), 2 ) );
		cesta.setItensPedido( itensPedido );
	}

	@Override
	public void adicionarGraos() {
		itensPedido.add( new ItemPedido( ProdutoDAO.getInstance().buscaProdutoPorId( 3 ), 2 ) );
		itensPedido.add( new ItemPedido( ProdutoDAO.getInstance().buscaProdutoPorId( 6 ), 1 ) );
		itensPedido.add( new ItemPedido( ProdutoDAO.getInstance().buscaProdutoPorId( 9 ), 1 ) );
		itensPedido.add( new ItemPedido( ProdutoDAO.getInstance().buscaProdutoPorId( 11 ), 3 ) );
		itensPedido.add( new ItemPedido( ProdutoDAO.getInstance().buscaProdutoPorId( 12 ), 1 ) );
		cesta.setItensPedido( itensPedido );
	}

	@Override
	public void adicionarIndustrializados() {
		itensPedido.add( new ItemPedido( ProdutoDAO.getInstance().buscaProdutoPorId( 2 ), 1 ) );
		itensPedido.add( new ItemPedido( ProdutoDAO.getInstance().buscaProdutoPorId( 4 ), 1 ) );
		itensPedido.add( new ItemPedido( ProdutoDAO.getInstance().buscaProdutoPorId( 14 ), 1 ) );
		itensPedido.add( new ItemPedido( ProdutoDAO.getInstance().buscaProdutoPorId( 15 ), 1 ) );
		itensPedido.add( new ItemPedido( ProdutoDAO.getInstance().buscaProdutoPorId( 17 ), 2 ) );
		itensPedido.add( new ItemPedido( ProdutoDAO.getInstance().buscaProdutoPorId( 20 ), 1 ) );
		cesta.setItensPedido( itensPedido );
	}

	@Override
	public void adicionarLegumesFrutas() {
		itensPedido.add( new ItemPedido( ProdutoDAO.getInstance().buscaProdutoPorId( 19 ), 1 ) );
		cesta.setItensPedido( itensPedido );
	}

}
