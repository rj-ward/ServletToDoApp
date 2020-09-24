package web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.LoginDao;
import model.LoginBean;

/**
 * This servlet handles login actions for the application.
 * 
 * @author Remy Ward
 * @version 1.0
 * @since 2020-09-23
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LoginDao loginDao = new LoginDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Redirects to login page
		response.sendRedirect("login/login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		authenticate(request, response);
	}

	/**
	 * This method validates the username and password against the database entries.
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void authenticate(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// Stores username and password from form to a LoginBean object
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		LoginBean loginBean = new LoginBean();
		loginBean.setUsername(username);
		loginBean.setPassword(password);

		// If a valid user is found and passwords match
		if (loginDao.validate(loginBean)) {
			// Forwards user to todo-list page
			RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-list.jsp");
			HttpSession session = request.getSession();
			// Saves username for further use in application
			session.setAttribute("loggedInUser_userName", username);
			dispatcher.forward(request, response);
			// Else redirects to login page to try again.
		} else {
			HttpSession session = request.getSession();
			response.sendRedirect("login/login.jsp");
		}

	}
}
