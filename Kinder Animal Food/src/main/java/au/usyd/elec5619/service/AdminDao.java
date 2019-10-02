package au.usyd.elec5619.service;

import au.usyd.elec5619.domain.AdminLogin;
import au.usyd.elec5619.domain.Admin;

/**
 * Interface for connecting admin database table.
 * 
 * @author Jeffery
 *
 */
public interface AdminDao {

	Admin validateAdmin(AdminLogin login);

}
