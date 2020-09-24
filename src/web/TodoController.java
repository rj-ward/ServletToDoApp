package web;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.TodoDao;
import model.Todo;

/**
 * This servlet handles to-do list actions for the application.
 * 
 * @author Remy Ward
 * @version 1.0
 * @since 2020-09-23
 */
@WebServlet("/")
public class TodoController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TodoDao todoDao = new TodoDao();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Retrieves path based on form action
		String action = request.getServletPath();

		switch (action) {
		case "/new":
			showNewForm(request, response);
			break;
		case "/insert":
			insertTodo(request, response);
			break;
		case "/delete":
			deleteTodo(request, response);
			break;
		case "/edit":
			showEditForm(request, response);
			break;
		case "/update":
			updateTodo(request, response);
			break;
		case "/list":
			listTodo(request, response);
			break;
		default:
			RequestDispatcher dispatcher = request.getRequestDispatcher("../login/login.jsp");
			dispatcher.forward(request, response);
			break;
		}
	}

	/**
	 * Deletes item by ID selected via page link
	 * 
	 * @param request
	 * @param response
	 */
	private void deleteTodo(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		Todo toDelete = todoDao.searchForItemById(id);
		todoDao.deleteItem(toDelete);
		try {
			response.sendRedirect("list");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Merges database edits to edit entry.
	 * 
	 * @param request
	 * @param response
	 */
	private void updateTodo(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));

		String title = request.getParameter("title");
		String username = request.getParameter("username");
		String description = request.getParameter("description");
		boolean isDone = Boolean.valueOf(request.getParameter("isDone"));
		Todo updateTodo = new Todo(id, title, username, description, isDone);

		todoDao.updateTodo(updateTodo);

		try {
			response.sendRedirect("list");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Selects item to edit via ID from link and redirects to edit form, with fields
	 * filled in.
	 * 
	 * @param request
	 * @param response
	 */
	private void showEditForm(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		Todo existingTodo = todoDao.selectTodo(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-form.jsp");
		request.setAttribute("todo", existingTodo);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Adds new entry via fields provided by form.
	 * 
	 * @param request
	 * @param response
	 */
	private void insertTodo(HttpServletRequest request, HttpServletResponse response) {
		String title = request.getParameter("title");
		String username = request.getParameter("username");
		String description = request.getParameter("description");
		boolean isDone = Boolean.valueOf(request.getParameter("isDone"));
		Todo newTodo = new Todo(title, username, description, isDone);
		todoDao.insertItem(newTodo);
		try {
			response.sendRedirect("list");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Loads empty form to add new item to database
	 * 
	 * @param request
	 * @param response
	 */
	private void showNewForm(HttpServletRequest request, HttpServletResponse response) {
		RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-form.jsp");
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Loads existing to-do entries for given user for display.
	 * 
	 * @param request
	 * @param response
	 */
	private void listTodo(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String username = session.getAttribute("loggedInUser_userName").toString();
		List<Todo> listTodo = todoDao.showAllItemsByUser(username);
		request.setAttribute("listTodo", listTodo);
		RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-list.jsp");
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}

	}
}
