package au.usyd.elec5619.KAF.service;

import org.springframework.beans.factory.annotation.Autowired;

import au.usyd.elec5619.KAF.model.Store;
import junit.framework.TestCase;

public class simpleStoreDaoTest extends TestCase {
	
	@Autowired
	private StoreService storeService;
	
	protected void setUp() throws Exception {
		storeService = new StoreServiceImpl();
	}
	
	
	public void testGetStoresWithNoStores() {
		storeService = new StoreServiceImpl();
		assertNull(storeService.storeList());
	}
	
	public void testDeleteStoresWithNoStores() {
		storeService = new StoreServiceImpl();
		assertNull(storeService.deleteStore(-1));
	}
	
	public void testEditStoresWithNoStores() {
		Store store = new Store();
		store.setStore_id(-1);
		storeService = new StoreServiceImpl();
		assertNull(storeService.editStore(store));
	}
	
	public void testCountStoresWithNoStores() {
		storeService = new StoreServiceImpl();
		assertNull(storeService.countStore());
	}
}
