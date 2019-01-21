package br.com.mercado.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.mercado.domain.Venda;
import br.com.mercado.util.HibernateUtil;

public class VendaDAO {
	public void salvar(Venda venda){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.save(venda);
			transacao.commit();
		}catch(RuntimeException ex){ // para capturar alguma exessão que venha ocorrer
			if(transacao != null){
				transacao.rollback();
			}
			throw ex;
		}finally{
			sessao.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Venda> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Venda> vendas = null;
		
		try{
			Query consulta = sessao.getNamedQuery("Venda.listar");
			vendas = consulta.list();
		}catch(RuntimeException ex){ // para capturar alguma exessão que venha ocorrer
			throw ex;
		}finally{
			sessao.close();
		}
		return vendas;
	}
	
	public Venda buscarPorCodigo(Long codigo) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Venda venda = null;
		
		try{
			Query consulta = sessao.getNamedQuery("Venda.buscarPorCodigo");
			consulta.setLong("codigo", codigo); // codigo esquerda = :codigo
			
			venda = (Venda) consulta.uniqueResult();
		}catch(RuntimeException ex){ // para capturar alguma exessão que venha ocorrer
			throw ex;
		}finally{
			sessao.close();
		}
		return venda;
	}

	public void exluir(Venda venda){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.delete(venda);
			transacao.commit();
		}catch(RuntimeException ex){ // para capturar alguma exessão que venha ocorrer
			if(transacao != null){ // caso der errado ele verifica se a transassaõ foi aberta
				transacao.rollback();
			}
			throw ex;
		}finally{
			sessao.close();
		}
	}
	
	public void editar(Venda venda){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.update(venda);
			transacao.commit();
		} catch (RuntimeException ex){
			if(transacao != null){
				transacao.rollback();
			}
			throw ex;
		}finally{
			sessao.close();
		}
	}
}

