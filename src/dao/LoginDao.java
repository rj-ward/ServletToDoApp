package dao;

import model.LoginBean;
import model.User;

public class LoginDao {

	private UserDao userDao = new UserDao();

	public boolean validate(LoginBean loginBean) {
		User test = userDao.searchForUserByUsername(loginBean.getUsername());
		if (test != null && test.getPassword().equals(loginBean.getPassword())) {
			return true;
		} 
		return false;
	}

}
