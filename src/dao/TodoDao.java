package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Todo;

public class TodoDao {
	/**
	 * Creates a manager connecting to MySQL database
	 */
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("ServletToDo");
	
	public void insertItem(Todo li) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(li);
		em.getTransaction().commit();
		em.close();
	}
	
	public Todo searchForItemById(int idToEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		Todo found = em.find(Todo.class, idToEdit);
		em.close();
		return found;
	}
	
	public List<Todo> showAllItems() {
		EntityManager em = emfactory.createEntityManager();
		List<Todo> allItems = em.createQuery("SELECT i FROM Todo i").getResultList();
		return allItems;
	}
	
	public List<Todo> showAllItemsByUser(String user) {
		EntityManager em = emfactory.createEntityManager();
		TypedQuery<Todo> typedQuery = em.createQuery("select li from Todo li where li.username = :selectedUsername",
				Todo.class);
		typedQuery.setParameter("selectedUsername", user);
		List<Todo> allItems = typedQuery.getResultList();
		return allItems;
	}
	
	public void deleteItem(Todo toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		// Searches for item based on ID passed from form.
		TypedQuery<Todo> typedQuery = em.createQuery("select li from Todo li where li.id = :selectedId",
				Todo.class);
		typedQuery.setParameter("selectedId", toDelete.getId());
		Todo result = typedQuery.getSingleResult();
		// Removes entry
		em.remove(result);
		// commits to database
		em.getTransaction().commit();
		em.close();
	}

	public Todo selectTodo(int id) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		Todo found = em.find(Todo.class, id);
		em.close();
		return found;
	}

	public void updateTodo(Todo updateTodo) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(updateTodo);
		em.getTransaction().commit();
		em.close();
		
	}


}
