package br.com.mercado.test;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.mercado.dao.FabricanteDAO;
import br.com.mercado.domain.Fabricante;

public class FabricanteDAOTest {
	@Test
	@Ignore
	public void salvar(){
		Fabricante f1 = new Fabricante();
		f1.setDescricao("DESCRICAOA");
		
		Fabricante f2 = new Fabricante();
		f2.setDescricao("DESCRICAOB");
		
				
		FabricanteDAO dao = new FabricanteDAO();
		
		dao.salvar(f1);
		dao.salvar(f2);	
	
	}
	
	@Test
	@Ignore
	public void listar(){
		FabricanteDAO dao = new FabricanteDAO();
		List<Fabricante> fabricantes = dao.listar();
		
		System.out.println(fabricantes);
	}

	@Test
	@Ignore
	public void buscarPorCodigo(){
		FabricanteDAO dao = new FabricanteDAO();
		
		Fabricante f1 = dao.buscarPorCodigo(2L);
		Fabricante f2 = dao.buscarPorCodigo(1L);
		
		System.out.println(f1);
		System.out.println(f2);
	}
	
	@Test
	@Ignore
	public void excluir() { // tem que colocar todos os dados
		 FabricanteDAO dao = new FabricanteDAO();
		 
		 Fabricante fabricante = dao.buscarPorCodigo(2L);
		 
		 dao.excluir(fabricante);
	}
	
	@Test
	@Ignore
	public void editar(){
		FabricanteDAO dao = new FabricanteDAO();
		 
		 Fabricante fabricante = dao.buscarPorCodigo(3L);
		 
		 fabricante.setDescricao("DESCRICAOX");
		 
		 dao.editar(fabricante);
	}
}
