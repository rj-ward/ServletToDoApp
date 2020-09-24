package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

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
		// Checks for duplicate usernames before creating entry.
		User test = searchForUserByUsername(s.getUsername());
		if (test == null) {
			em.persist(s);
		}
		em.close();
	}

	/**
	 * Finds entry based on ID
	 * 
	 * @param id ID primary key number for entry
	 * @return Todo object of entry
	 */
	public User searchForUserById(int id) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		User found = em.find(User.class, id);
		em.close();
		return found;
	}

	/**
	 * Removes item from database
	 * 
	 * @param toDelete
	 */
	public void deleteItem(User toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		// Searches for item based on ID passed from form.
		TypedQuery<User> typedQuery = em.createQuery("select li from User li where li.id = :selectedId", User.class);
		typedQuery.setParameter("selectedId", toDelete.getId());
		User result = typedQuery.getSingleResult();
		// Removes entry
		em.remove(result);
		// commits to database
		em.getTransaction().commit();
		em.close();
	}

	/**
	 * Helper method to check if a query has only one result. Used to avoid errors
	 * with getSingleResult().
	 * 
	 * @param query TypedQuery result from database search
	 * @return true if only one entry exists
	 */
	private boolean checkForSingleResult(TypedQuery<User> query) {
		List<User> results = query.getResultList();
		if (results.isEmpty()) {
			return false;
		} else if (results.size() < 1) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Searches for a given user by username.
	 * 
	 * @param username User to be located
	 * @return User object if found, null otherwise
	 * @throws NullPointerException Returns null if no user by that name exists.
	 */
	public User searchForUserByUsername(String username) throws NullPointerException {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		// Searches for item based on ID passed from form.
		TypedQuery<User> typedQuery = em.createQuery("select li from User li where li.username = :selectedUsername",
				User.class);
		typedQuery.setParameter("selectedUsername", username);
		User result = null;
		if (checkForSingleResult(typedQuery)) {
			result = typedQuery.getSingleResult();
		}
		em.close();
		return result;
	}

}
