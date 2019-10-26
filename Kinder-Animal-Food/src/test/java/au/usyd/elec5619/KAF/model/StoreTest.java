package au.usyd.elec5619.KAF.model;

import junit.framework.TestCase;

public class StoreTest extends TestCase {
	
	private Store store;
	
	protected void setUp() throws Exception {
		store = new Store();
	}
	
	public void testSetandGetId() {
		assertNull(store.getStore_id());
		// test with random value
		Integer newId = (int) (Math.random() * 100);
		store.setStore_id(newId);
		assertEquals(newId,store.getStore_id());
	}
	
	public void testSetandGetStoreName() {
		assertNull(store.getStore_name());
		String storeName = "testingStoreName";
		store.setStore_name(storeName);
		assertEquals(storeName, store.getStore_name());
	}
	
	public void testSetandGetStoreAddress() {
		assertNull(store.getStore_address());
		String storeAddress = "testingStoreAddress";
		store.setStore_address(storeAddress);
		assertEquals(storeAddress, store.getStore_address());
	}
	
	public void testSetandGetStoreState() {
		assertNull(store.getStore_state());
		String storeState = "testingStoreState";
		store.setStore_state(storeState);
		assertEquals(storeState, store.getStore_state());
	}
	
	public void testSetandGetStorePostcode() {
		assertNull(store.getStore_postcode());
		String storePostcode = "testingStorePostcode";
		store.setStore_postcode(storePostcode);
		assertEquals(storePostcode, store.getStore_postcode());
	}
	
	public void testSetandGetStoreLongitude() {
		assertNull(store.getStore_longitude());
		String storeLongitude = "testingStoreLongitude";
		store.setStore_longitude(storeLongitude);
		assertEquals(storeLongitude, store.getStore_longitude());
	}
	
	public void testSetandGetStoreLatitude() {
		assertNull(store.getStore_latitude());
		String storeLatitude = "testingStoreLatitude";
		store.setStore_latitude(storeLatitude);
		assertEquals(storeLatitude, store.getStore_latitude());
	}
	
	public void testSetandGetStoreBrandsNum() {
		assertNull(store.getBrands_num());
		Integer storeBrandsNum = (int) (Math.random() * 100);
		store.setBrands_num(storeBrandsNum);
		assertEquals(storeBrandsNum, store.getBrands_num());
	}
}
