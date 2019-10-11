package au.usyd.elec5619.KAF.service;

import java.util.List;

import au.usyd.elec5619.KAF.model.Store;

public interface StoreService {

	List<Store> searchStore(String store_name);
	
	List<Store> storeList();
	
	boolean insertStore(Store store);
	
	boolean deleteStore(Store store);
	
	boolean editStore(Store store);
	
	Integer countStore();
}
