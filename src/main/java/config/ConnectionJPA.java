package config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionJPA {

	private static EntityManagerFactory entityManagerFactory = null;
	private static EntityManager entityManager = null;
	
	private ConnectionJPA() {
	}
	
	public static EntityManager getEntityManager(String PERSISTENCE_NAME) {
		if (entityManager == null || !entityManager.isOpen()) entityManager = initEntityManager(PERSISTENCE_NAME);
		return entityManager;
	}
	private static EntityManager initEntityManager(String PERSISTENCE_NAME) {
		entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_NAME);
		return entityManagerFactory.createEntityManager();
	}
	
	public static void closeEntityManager(EntityManager eM) {
		if (eM != null && eM.isOpen()) {
			entityManagerFactory.close();
			eM.close();
		}
	}
}
