package au.usyd.elec5619.KAF.model;

import java.util.Date;

import junit.framework.TestCase;

public class ReportTest extends TestCase {
	
	private Report report;
	
	protected void setUp() throws Exception {
		report = new Report();
	}
	
	public void testSetandGetReportId() {
		assertNull(report.getReport_id());
		// test with random value
		Integer newId = (int) (Math.random() * 100);
		report.setReport_id(newId);
		assertEquals(newId,report.getReport_id());
	}
	
	public void testSetandGetUserId() {
		assertNull(report.getUser_id());
		// test with random value
		Integer newId = (int) (Math.random() * 100);
		report.setUser_id(newId);
		assertEquals(newId,report.getUser_id());
	}
	
	public void testSetandGetReportTime() {
		assertNull(report.getReport_time());
		Date time = new Date();
		report.setReport_time(time);
		assertEquals(time, report.getReport_time());
	}
	
	public void testSetandGetBrandId() {
		assertNull(report.getBrand_id());
		// test with random value
		Integer newId = (int) (Math.random() * 100);
		report.setBrand_id(newId);
		assertEquals(newId,report.getBrand_id());
	}
	
	public void testSetandGetStoreName() {
		assertNull(report.getStore_name_by_user());
		String storeName = "testingStoreName";
		report.setStore_name_by_user(storeName);
		assertEquals(storeName, report.getStore_name_by_user());
	}
	
	public void testSetandGetStoreAddress() {
		assertNull(report.getStore_address_by_user());
		String storeAddress = "testingStoreAddress";
		report.setStore_address_by_user(storeAddress);
		assertEquals(storeAddress, report.getStore_address_by_user());
	}
	
	public void testSetandGetStoreState() {
		assertNull(report.getStore_state_by_user());
		// testing varchar(3)
		String storeState = "NSW";
		report.setStore_state_by_user(storeState);
		assertEquals(storeState, report.getStore_state_by_user());
	}
	
	public void testSetandGetStorePostcode() {
		assertNull(report.getStore_postcode_by_user());
		// testing varchar(4)
		String storePostcode = "1000";
		report.setStore_postcode_by_user(storePostcode);
		assertEquals(storePostcode, report.getStore_postcode_by_user());
	}
	
	public void testSetandGetStatus() {
		assertNull(report.getStatus());
		// testing varchar(1)
		String storeLongitude = "P";
		report.setStatus(storeLongitude);
		assertEquals(storeLongitude, report.getStatus());
	}
}
