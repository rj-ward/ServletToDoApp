package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.User;

/**
 * Database connection methods for use in servlets.
 * 
 * @author Remy Ward
 * @version 1.0
 * @since 2020-09-23
 */
public class UserDao {
	/**
	 * Creates a manager connecting to MySQL database
	 */
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("ServletToDo");

	/**
	 * Registers a new user in the database
	 * 
	 * @param s User to be registered
	 */
	public void insertUser(User s) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(s);
		em.getTransaction().commit();
		em.close();
	}
}
