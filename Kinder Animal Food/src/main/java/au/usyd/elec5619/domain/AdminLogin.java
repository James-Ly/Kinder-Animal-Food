package au.usyd.elec5619.domain;

/**
 * A Login Bean Model using to connect JDBC DataSource.
 * 
 * @author Jeffery
 *
 */
public class AdminLogin {

	private String username;
	private String password;

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
