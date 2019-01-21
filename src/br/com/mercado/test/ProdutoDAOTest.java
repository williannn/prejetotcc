package br.com.mercado.test;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.mercado.dao.FabricanteDAO;
import br.com.mercado.dao.ProdutoDAO;
import br.com.mercado.domain.Fabricante;
import br.com.mercado.domain.Produto;


public class ProdutoDAOTest {
	@Test
	@Ignore
	public void salvar(){
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscarPorCodigo(4L);
		
		Produto produto = new Produto(); // preenche o produto
		produto.setDescricao("DESCRICAOY"); // n�o pode deixar em branco
		produto.setPreco(new BigDecimal(17.63D)); // . � padr�o americano
		produto.setQuantidade(3);
		produto.setFabricante(fabricante);
		
		ProdutoDAO produtoDAO = new ProdutoDAO(); // manda gravar
		produtoDAO.salvar(produto);
		
	}
	
	@Test
	@Ignore
	public void buscarPorCodigo(){
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscarPorCodigo(2L);
		
		System.out.println(produto);
	}
	
	@Test
	@Ignore
	public void listar(){
		ProdutoDAO produtoDAO = new ProdutoDAO();
		
		List<Produto> produtos = produtoDAO.listar();
		
		System.out.println(produtos);
	}
	
	@Test
	@Ignore
	public void excluir(){
		ProdutoDAO produtoDAO = new ProdutoDAO();
		
		Produto produto = produtoDAO.buscarPorCodigo(2L);
		
		produtoDAO.exluir(produto);
	}
	
	@Test
	@Ignore
	public void editar(){
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscarPorCodigo(1L);
		produto.setDescricao("DESCRICAOY"); // para mudar s� uma coisa � s� colocar / nas outras
		produto.setPreco(new BigDecimal(6.37D));
		produto.setQuantidade(9);
		
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscarPorCodigo(1L);
		produto.setFabricante(fabricante);
		
		produtoDAO.editar(produto);
	}
	
}
