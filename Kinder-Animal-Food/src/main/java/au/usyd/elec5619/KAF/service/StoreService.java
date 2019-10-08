package au.usyd.elec5619.KAF.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import au.usyd.elec5619.domain.Store;

/**
 * Interface for Store service.
 * 
 * @author Kai
 *
 */

@Repository
public interface StoreService {

	List<Store> validateStore(String store_name);
	
	List<Store> storeList();
	
	boolean insertStore(Store store);
	
	boolean deleteStore(Store store);
	
	boolean editStore(Store store);
	
	Integer countStore();
}
