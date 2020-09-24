package dao;

import model.LoginBean;
import model.User;

/**
 * The class contains helper methods for database actions..
 * 
 * @author Remy Ward
 * @version 1.0
 * @since 2020-09-23
 */
public class LoginDao {

	private UserDao userDao = new UserDao();

	/**
	 * This method validates a user login by comparing username and password to
	 * MySQL database entries.
	 * 
	 * @param loginBean object containing username and password information.
	 * @return Boolean of validation result.
	 */
	public boolean validate(LoginBean loginBean) {
		User test = userDao.searchForUserByUsername(loginBean.getUsername());
		if (test != null && test.getPassword().equals(loginBean.getPassword())) {
			return true;
		}
		return false;
	}

}
