package model;

import java.io.Serializable;

/**
 * Bean class for use in JSP action tags.
 * 
 * @author Remy Ward
 * @version 1.0
 * @since 2020-09-23
 */
public class User implements Serializable {

	/**
	 * Default ID field
	 */
	private static final long serialVersionUID = 1L;
	private String firstName; // First name of user
	private String lastName; // Last name of user
	private String username; // Username for account
	private String password; // Password for account

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

}
