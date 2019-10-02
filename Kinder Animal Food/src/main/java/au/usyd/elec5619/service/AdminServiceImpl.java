package au.usyd.elec5619.service;

import org.springframework.beans.factory.annotation.Autowired;

import au.usyd.elec5619.service.AdminDao;
import au.usyd.elec5619.domain.AdminLogin;
import au.usyd.elec5619.domain.Admin;

/**
 * Admin service between Dao and controller.
 * 
 * @author Jeffery
 *
 */
public class AdminServiceImpl implements AdminService {

  @Autowired
  public AdminDao adminDao;

  public Admin validateAdmin(AdminLogin login) {
    return adminDao.validateAdmin(login);
  }

}
