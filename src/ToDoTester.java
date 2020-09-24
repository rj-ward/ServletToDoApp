import javax.persistence.NoResultException;

import dao.UserDao;
import model.User;

public class ToDoTester {
	public static void main(String[] args) {
		
	    UserDao dao = new UserDao();
	    
	    try {
	    	User test1 = dao.searchForUserByUsername("test1");
	    	System.out.println(test1.toString());
	    }
	    
	    catch (Exception e) {
	    	e.printStackTrace();
	    }
	    
	    try {
	    	User test2 = dao.searchForUserByUsername("test2");
	    	System.out.println(test2.toString());
	    }
	    
	    catch (Exception e) {
	    	System.out.println("Error");
	    }
	}
}
