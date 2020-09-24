package model;

/**
 * The class defines a working bean for use in login validation.
 * 
 * @author Remy Ward
 * @version 1.0
 * @since 2020-09-23
 */
public class LoginBean {
	private String username;
	private String password;

	public LoginBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginBean [username=" + username + ", password=" + password + "]";
	}
}
