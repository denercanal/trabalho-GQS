package trabalho.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import trabalho.model.Produto;

public class ProdutoDAO {

	private List<Produto> produtos;
	private static ProdutoDAO instance;

	private ProdutoDAO() {
		produtos = new ArrayList<Produto>();
		produtos.add( new Produto( 1, "Achocolatado em Pó", 6, 11.99 ) );
		produtos.add( new Produto( 2, "Açúcar Refinado", 15, 29.9 ) );
		produtos.add( new Produto( 3, "Arroz Agulhinha Tipo 1", 8, 24.10 ) );
		produtos.add( new Produto( 4, "Biscoito Recheado (Sabores)", 5, 3.78 ) );
		produtos.add( new Produto( 5, "Biscoito Cream Cracker", 15, 4.95 ) );
		produtos.add( new Produto( 6, "Café Torrado e Moído", 7, 19.50 ) );
		produtos.add( new Produto( 7, "Creme de Leite", 13, 2.65 ) );
		produtos.add( new Produto( 8, "Ervilha em Conserva", 9, 19.30 ) );
		produtos.add( new Produto( 9, "Farinha de Trigo", 7, 3.55 ) );
		produtos.add( new Produto( 10, "Farinha de Mandioca Temperada", 6, 2.70 ) );
		produtos.add( new Produto( 11, "Feijão Carioca Tipo 1", 6, 6.65 ) );
		produtos.add( new Produto( 12, "Fubá Mimoso", 14, 3.71 ) );
		produtos.add( new Produto( 13, "Leite em Pó Integral", 5, 25.79 ) );
		produtos.add( new Produto( 14, "Macarrão Espaguete", 13, 3.15 ) );
		produtos.add( new Produto( 15, "Macarrão Parafuso", 10, 5.95 ) );
		produtos.add( new Produto( 16, "Mistura para Bolo (Sabores)", 12, 41.10 ) );
		produtos.add( new Produto( 17, "Óleo de Soja", 13, 7.89 ) );
		produtos.add( new Produto( 18, "Pó para Gelatina (Sabores)", 5, 1.45 ) );
		produtos.add( new Produto( 19, "Polpa de Tomate", 5, 33.95 ) );
		produtos.add( new Produto( 20, "Sal Refinado", 7, 1.29 ) );
		produtos.add( new Produto( 21, "Sardinha", 12, 18.80 ) );
		produtos.add( new Produto( 22, "Tempero Completo/ Alho e Sal", 15, 2.57 ) );
		produtos.add( new Produto( 23, "Amaciante Líquido (Fragrâncias)", 5, 10.48 ) );
		produtos.add( new Produto( 24, "Creme Dental", 9, 1.30 ) );
		produtos.add( new Produto( 25, "Desinfetante (Fragrâncias)", 10, 27.99 ) );
		produtos.add( new Produto( 26, "Papel Higiênico", 14, 11.19 ) );
		produtos.add( new Produto( 27, "Sabonete (Fragrâncias)", 11, 1.89 ) );
		produtos.add( new Produto( 28, "1 KG de Carne Bovina", 11, 31.89 ) );
		produtos.add( new Produto( 29, "1 KG de Carne Suína", 11, 21.39 ) );
		produtos.add( new Produto( 30, "1 KG de Carne de Frango", 11, 14.39 ) );
		produtos.add( new Produto( 31, "1 dúzia de ovos", 16, 7.19 ) );
		produtos.add( new Produto( 32, "Leite Integral UHT 1 Litro", 100, 3.69 ) );
		produtos.add( new Produto( 33, "Manteiga com Sal 100G", 32, 4.98 ) );
		produtos.add( new Produto( 34, "1 Kg Pão Francês", 20, 7.00 ) );
		produtos.add( new Produto( 35, "1 Kg batata", 200, 1.71 ) );
		produtos.add( new Produto( 36, "1 Kg tomate", 120, 6.00 ) );
		produtos.add( new Produto( 37, "1 dúzia de banana", 20, 2.50 ) );
	}

	public static ProdutoDAO getInstance() {
		if( instance == null ) {
			instance = new ProdutoDAO();
		}
		return instance;
	}

	public void verificaQuantidade( double quantidade ) {
		if( quantidade <= 0 ) {
			throw new RuntimeException( "Quantidade deve ser > 0" );
		}
	}

	public void adicionaEstoque( int id, double quantidade ) {
		verificaQuantidade( quantidade );
		Produto produto = buscaProdutoPorId( id );
		produto.incrementaEstoque( quantidade );
	}

	private double getQuantidadeEmEstoque( int id ) {
		return buscaProdutoPorId( id ).getQuantidadeEmEstoque();
	}

	public void baixaEstoque( int id, double quantidade ) {
		verificaQuantidade( quantidade );
		Produto produto = buscaProdutoPorId( id );
		double quantidadeEmEstoque = getQuantidadeEmEstoque( id );
		if( quantidadeEmEstoque >= quantidade ) {
			produto.decrementaEstoque( quantidade );
		} else {
			throw new RuntimeException( "Quantiade (" + quantidade + ") do produto " + id + "insuficiente em estoque (" + quantidadeEmEstoque + ")" );
		}

	}

	public Produto buscaProdutoPorId( int id ) {
		return produtos.stream().filter( p -> p.getId() == id ).findFirst().orElseThrow();
	}

	public Produto buscaProdutoPorNome( String nome ) {
		return produtos.stream().filter( p -> p.getNome().equalsIgnoreCase( nome ) ).findFirst().orElseThrow();
	}

	public List<Produto> getProdutos() {
		return Collections.unmodifiableList( produtos );
	}

	public int getNroProdutos() {
		return produtos.size();
	}

}
