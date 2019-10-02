package au.usyd.elec5619.domain;

import junit.framework.TestCase;

/**
 * Unit tests for Admin class.
 * 
 * 
 * @author Jeffery
 *
 */
public class AdminTest extends TestCase{
	private Admin admin;
	
	protected void setUp() throws Exception{
		admin = new Admin();
	}
	
	public void testGetId() {
		assertNull(admin.getId());
	}
	
	public void testGetPassword() {
		assertNull(admin.getPassword());
	}
}