package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Todo;

/**
 * The class contains helper methods for database actions..
 * 
 * @author Remy Ward
 * @version 1.0
 * @since 2020-09-23
 */
public class TodoDao {
	/**
	 * Creates a manager connecting to MySQL database
	 */
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("ServletToDo");

	/**
	 * Inserts a new entry into the database
	 * 
	 * @param entry Todo entity object
	 */
	public void insertItem(Todo entry) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(entry);
		em.getTransaction().commit();
		em.close();
	}

	/**
	 * Finds entry based on ID
	 * 
	 * @param id ID primary key number for entry
	 * @return Todo object of entry
	 */
	public Todo searchForItemById(int id) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		Todo found = em.find(Todo.class, id);
		em.close();
		return found;
	}

	/**
	 * Lists all entries in database
	 * 
	 * @return list of todo items
	 */
	public List<Todo> showAllItems() {
		EntityManager em = emfactory.createEntityManager();
		List<Todo> allItems = em.createQuery("SELECT i FROM Todo i").getResultList();
		return allItems;
	}

	/**
	 * Lists all entries by a particular user
	 * 
	 * @param user username provided or extracted from object
	 * @return list of todo items
	 */
	public List<Todo> showAllItemsByUser(String user) {
		EntityManager em = emfactory.createEntityManager();
		TypedQuery<Todo> typedQuery = em.createQuery("select li from Todo li where li.username = :selectedUsername",
				Todo.class);
		typedQuery.setParameter("selectedUsername", user);
		List<Todo> allItems = typedQuery.getResultList();
		return allItems;
	}

	/**
	 * Deletes entry from database.
	 * 
	 * @param toDelete todo item to be removed
	 */
	public void deleteItem(Todo toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		// Searches for item based on ID passed from form.
		TypedQuery<Todo> typedQuery = em.createQuery("select li from Todo li where li.id = :selectedId", Todo.class);
		typedQuery.setParameter("selectedId", toDelete.getId());
		Todo result = typedQuery.getSingleResult();
		// Removes entry
		em.remove(result);
		// commits to database
		em.getTransaction().commit();
		em.close();
	}

	/**
	 * Finds entry based on ID
	 * 
	 * @param id ID primary key number for entry
	 * @return Todo object of entry
	 */
	public Todo selectTodo(int id) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		Todo found = em.find(Todo.class, id);
		em.close();
		return found;
	}

	/**
	 * Merges changes to entry to database
	 * 
	 * @param updateTodo item to update
	 */
	public void updateTodo(Todo updateTodo) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(updateTodo);
		em.getTransaction().commit();
		em.close();

	}

}
