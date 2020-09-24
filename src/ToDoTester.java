import java.time.LocalDate;

import javax.persistence.NoResultException;

import dao.TodoDao;
import dao.UserDao;
import model.User;

/**
 * ToDoTester.Java This file works as a scratchpad to test methods during
 * development.
 * 
 * @author Remy Ward
 * @version 1.0
 * @since 2020-09-23
 */
public class ToDoTester {
	public static void main(String[] args) {

		UserDao dao = new UserDao();
		TodoDao todoDao = new TodoDao();

		try {
			User test1 = dao.searchForUserByUsername("test1");
			System.out.println(test1.toString());
		}

		catch (Exception e) {
			e.printStackTrace();
		}

//	    try {
//	    	User test2 = dao.searchForUserByUsername("test2");
//	    	System.out.println(test2.toString());
//	    }
//	    
//	    catch (Exception e) {
//	    	System.out.println("Error");
//	    }

		LocalDate today = LocalDate.parse("2020-10-01");
		System.out.println(today);

		System.out.println(todoDao.showAllItemsByUser("test1").toString());
	}
}
