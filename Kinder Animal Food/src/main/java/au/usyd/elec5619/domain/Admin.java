package au.usyd.elec5619.domain;

/**
 * An Admin model which contains the information of the admin.
 * 
 * @author Jeffery
 *
 */
public class Admin {

	private String admin_id;
	private String admin_password;

	public String getId() {
		return admin_id;
	}

	public void setId(String admin_id) {
		this.admin_id = admin_id;
	}

	public String getPassword() {
		return admin_password;
	}

	public void setPassword(String admin_password) {
		this.admin_password = admin_password;
	}
}
