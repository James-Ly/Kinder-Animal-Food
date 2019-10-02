package au.usyd.elec5619.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import au.usyd.elec5619.service.AdminService;
import au.usyd.elec5619.domain.AdminLogin;
import au.usyd.elec5619.domain.Admin;

/**
 * Controller for admin login.
 * 
 * @author Jeffery
 *
 */
@Controller
public class AdminLoginController {

  @Autowired
  AdminService adminService;

  @RequestMapping(value = "/admin", method = RequestMethod.GET)
  public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mav = new ModelAndView("admin");
    mav.addObject("admin", new AdminLogin());

    return mav;
  }

  @RequestMapping(value = "/adminProcess", method = RequestMethod.POST)
  public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response,
      @ModelAttribute("admin") AdminLogin adminLogin) {
    ModelAndView mav = null;

    Admin admin = adminService.validateAdmin(adminLogin);

    if (null != admin) {
      mav = new ModelAndView("continue");
      mav.addObject("name", admin.getId());
    } else {
      mav = new ModelAndView("admin");
      mav.addObject("message", "Username or Password is wrong!!");
    }

    return mav;
  }

}
