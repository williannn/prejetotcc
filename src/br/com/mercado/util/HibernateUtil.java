package br.com.mercado.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			// Cria um SessionFactory a partir do hibernate.cfg.xml
			Configuration configuration = new Configuration();
			configuration.configure();

			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();
			
			SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			
			return sessionFactory;

		} catch (Throwable ex) {
			// Exibe uma mensagem de erro
			System.err.println("Falha ao tentar criar o SessionFactory." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}