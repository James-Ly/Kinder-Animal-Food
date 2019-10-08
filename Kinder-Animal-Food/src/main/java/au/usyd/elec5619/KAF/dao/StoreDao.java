package au.usyd.elec5619.KAF.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import au.usyd.elec5619.KAF.model.Store;


@Repository
public interface StoreDao {
	
//	List<Store> validateStore(String store_name);
	
	List<Store> searchStoreByName(String store_name);
	
	List<Store> searchStore(Store store);
	
	List<Store> storeList();
	
	void insertStore(Store store);
	
	void deleteStore(Store store);
	
	void editStore(Store store);
	 
	Integer countStore();
}
