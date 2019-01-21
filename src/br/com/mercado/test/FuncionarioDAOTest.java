package br.com.mercado.test;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.mercado.dao.FuncionarioDAO;
import br.com.mercado.domain.Funcionario;

public class FuncionarioDAOTest {
	@Test

	public void salvar(){
		Funcionario funcionario = new Funcionario();
		funcionario.setFuncao("ADMINISTRADOR");
		funcionario.setNome("Fernando");
		funcionario.setSenha("12345678");
		
		FuncionarioDAO dao = new FuncionarioDAO();
		
		dao.salvar(funcionario);
		
	}
	
	@Test
	@Ignore
	public void listar(){
		FuncionarioDAO dao = new FuncionarioDAO();
		List<Funcionario> funcionarios = dao.listar();
		
		System.out.println(funcionarios);
	}

	@Test
	@Ignore
	public void buscarPorCodigo(){
		FuncionarioDAO dao = new FuncionarioDAO();
		
		Funcionario funcionario = dao.buscarPorCodigo(4L);
		
		System.out.println(funcionario);
	}
	
	@Test
	@Ignore
	public void excluir() { // tem que colocar todos os dados
		 FuncionarioDAO dao = new FuncionarioDAO();
		 
		 Funcionario funcionario = dao.buscarPorCodigo(3L);
		 
		 dao.excluir(funcionario);
	}
	
	@Test
	@Ignore
	public void editar(){
		FuncionarioDAO dao = new FuncionarioDAO();
		 
		 Funcionario funcionario = dao.buscarPorCodigo(4L);
		 
		 funcionario.setFuncao("Funcionario");
		 funcionario.setNome("Joao Kebler");
		 funcionario.setSenha("qlw2e3r4t5y6");
		 
		 dao.editar(funcionario);
	}
	
	@Test
	@Ignore
	public void autenticar(){
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		
		Funcionario funcionario = funcionarioDAO.autenticar("willian", "");
	
		System.out.println("Funcionario: " + funcionario);
	}
}
