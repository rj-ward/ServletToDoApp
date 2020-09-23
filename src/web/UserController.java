package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import model.User;

/**
 * Processes HTTP requests for servlets.
 * 
 * @author Remy Ward
 * @version 1.0
 * @since 2020-09-23
 */
@WebServlet("/register")
public class UserController extends HttpServlet{

	/**
	 * Default serial ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor
	 */
	public UserController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Registers a user from the given form
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        User employee = new User();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setUsername(username);
        employee.setPassword(password);
        
        UserDao dao = new UserDao();
        
        try {
        	dao.insertUser(employee);
        }
        
        catch (Exception e) {
        	e.printStackTrace();
        }
        
        getServletContext().getRequestDispatcher("register/register.jsp").forward(request, response);
	}
	
}
