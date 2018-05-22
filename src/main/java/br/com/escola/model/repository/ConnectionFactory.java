package br.com.escola.model.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionFactory {
	
	private static ConnectionFactory instance = null;
	
	private EntityManager entityManager;
	
	public static ConnectionFactory getInstance() {
		if (instance == null) {
			instance = new ConnectionFactory();
		}

		return instance;
	}


	public EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("maindatabase");
		
		if (entityManager == null) {
			entityManager = factory.createEntityManager();
		}

		return entityManager;
	}
	
	public void close() {
		if(entityManager != null) {
			entityManager.close();
		}
	}
	
}
