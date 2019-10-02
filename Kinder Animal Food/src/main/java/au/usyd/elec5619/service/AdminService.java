package au.usyd.elec5619.service;

import au.usyd.elec5619.domain.AdminLogin;
import au.usyd.elec5619.domain.Admin;

/**
 * Interface for admin service.
 * 
 * @author Jeffery
 *
 */
public interface AdminService {

	Admin validateAdmin(AdminLogin login);
}
