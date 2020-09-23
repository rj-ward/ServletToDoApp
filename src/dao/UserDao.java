package dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UserDao {
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("ServletToDo");
	
	
}
