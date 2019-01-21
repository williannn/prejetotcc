package br.com.mercado.test;

import org.junit.Test;

import br.com.mercado.util.HibernateUtil;

public class GerarTabelasTest {
	@Test
	public void gerar() {
		HibernateUtil.getSessionFactory();
		HibernateUtil.getSessionFactory().close();
	}
	
}
