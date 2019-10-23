package au.usyd.elec5619.KAF.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import junit.framework.TestCase;

public class UserTest extends TestCase {
	
	private User user;
	
	protected void setUp() throws Exception {
		user = new User();
	}
	
	public void testSetandGetId() {
		assertNull(user.getId());
		// test with Max long value
		Long newId = Long.MAX_VALUE;
		user.setId(newId);
		assertEquals(newId,user.getId());
		// test with Max long value
		newId = Long.MIN_VALUE;
		user.setId(newId);
		assertEquals(newId,user.getId());
		// test with a random value
		newId = (long) 99;
		user.setId(newId);
		assertEquals(newId, user.getId());
	}
	
	public void testSetandGetUserName() {
		assertNull(user.getUserName());
		String userName = "testingUserName";
		user.setUserName(userName);
		assertEquals(userName, user.getUserName());
	}
	
	public void testSetandGetPassword() {
		assertNull(user.getPassword());
		String password = "testingPassword";
		user.setPassword(password);
		assertEquals(password, user.getPassword());
	}
	
	public void testSetandGetGender() {
		assertNull(user.getGender());
		String gender = "Not disclosed";
		user.setGender(gender);
		assertEquals(gender,user.getGender());
	}
	
	public void testSetandGetBirthDate() {
		assertNull(user.getBirthDate());
		Date newDate = new Date();
		user.setBirthDate(newDate);
		assertEquals(newDate,user.getBirthDate());
	}
	
	public void testSetandGetRole() {
		assertNull(user.getRoles());
		Collection<Role> Rolecollection = new ArrayList<Role>();
		Role newRole = new Role();
		newRole.setId((long) 1);
		newRole.setName("ADMIN");
		Rolecollection.add(newRole);
		newRole.setId((long) 2);
		newRole.setName("USER");
		Rolecollection.add(newRole);
		user.setRoles(Rolecollection);
		Rolecollection.getClass();
		assertTrue(Rolecollection.contains(newRole));
	}

}
