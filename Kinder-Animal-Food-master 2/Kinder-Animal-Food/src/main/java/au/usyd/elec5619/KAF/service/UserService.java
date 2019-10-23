package au.usyd.elec5619.KAF.service;

import au.usyd.elec5619.KAF.model.User;
import au.usyd.elec5619.KAF.user.CrmUser;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByUserName(String userName);

    void save(CrmUser crmUser);
}
