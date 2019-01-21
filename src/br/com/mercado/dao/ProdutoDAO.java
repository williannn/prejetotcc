package br.com.mercado.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.mercado.domain.Produto;
import br.com.mercado.util.HibernateUtil;

public class ProdutoDAO {
	public void salvar(Produto produto){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.save(produto);
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
	public List<Produto> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Produto> produtos = null;
		
		try{
			Query consulta = sessao.getNamedQuery("Produto.listar");
			produtos = consulta.list();
		}catch(RuntimeException ex){ // para capturar alguma exessão que venha ocorrer
			throw ex;
		}finally{
			sessao.close();
		}
		return produtos;
	}
	
	public Produto buscarPorCodigo(Long codigo) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Produto produto = null;
		
		try{
			Query consulta = sessao.getNamedQuery("Produto.buscarPorCodigo");
			consulta.setLong("codigo", codigo); // codigo esquerda = :codigo
			
			produto = (Produto) consulta.uniqueResult();
		}catch(RuntimeException ex){ // para capturar alguma exessão que venha ocorrer
			throw ex;
		}finally{
			sessao.close();
		}
		return produto;
	}

	public void exluir(Produto produto){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.delete(produto);
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
	
	public void editar(Produto produto){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.update(produto);
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
}
