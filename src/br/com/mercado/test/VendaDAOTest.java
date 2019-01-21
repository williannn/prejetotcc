package br.com.mercado.test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.mercado.dao.FuncionarioDAO;
import br.com.mercado.dao.VendaDAO;
import br.com.mercado.domain.Funcionario;
import br.com.mercado.domain.Venda;

public class VendaDAOTest {
	
	@Test
	@Ignore
	public void salvar(){
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscarPorCodigo(3L);
		
		Venda venda = new Venda();
		venda.setFuncionario(funcionario);
		venda.setHorario(new Date());
		venda.setValor(new BigDecimal(12.34D));
		
		VendaDAO vendaDAO = new VendaDAO();
		vendaDAO.salvar(venda);
	}
	
	@Test
	@Ignore
	public void listar() {
		VendaDAO vendaDAO = new VendaDAO();
		
		List<Venda> vendas = vendaDAO.listar();
		
		System.out.println(vendas);
	}
	
	@Test
	@Ignore
	public void buscarPorCodigo() {
		VendaDAO vendaDAO = new VendaDAO();
		
		Venda venda = vendaDAO.buscarPorCodigo(4L);
		
		System.out.println(venda);
	}
	
	@Test
	@Ignore
	public void excluir(){
		VendaDAO vendaDAO = new VendaDAO();
		
		Venda venda = vendaDAO.buscarPorCodigo(4L);
		
		vendaDAO.exluir(venda);
		
	}
	
	@Test
	@Ignore
	public void editar(){
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscarPorCodigo(8L);
		
		VendaDAO vendaDAO = new VendaDAO();
		Venda venda = vendaDAO.buscarPorCodigo(4L);
		
		venda.setHorario(new Date());
		venda.setValor(new BigDecimal(321.34D));
		venda.setFuncionario(funcionario);
		
		vendaDAO.editar(venda);
	}

}
