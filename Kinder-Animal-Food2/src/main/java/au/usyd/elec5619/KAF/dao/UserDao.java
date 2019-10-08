package au.usyd.elec5619.KAF.dao;

import au.usyd.elec5619.KAF.model.User;

public interface UserDao {

    User findByUserName(String userName);
    
    void save(User user);
    
}
