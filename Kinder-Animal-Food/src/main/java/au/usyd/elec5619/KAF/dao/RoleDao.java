package au.usyd.elec5619.KAF.dao;

import au.usyd.elec5619.KAF.model.Role;

public interface RoleDao {

	public Role findRoleByName(String theRoleName);
	
}
