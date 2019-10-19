package au.usyd.elec5619.KAF.service;

import java.util.List;

import au.usyd.elec5619.KAF.model.Store;
import au.usyd.elec5619.KAF.user.CrmStoreLocation;

public interface StoreService {

	List<Store> searchStore(String store_name);
	
	Store searchStore(Integer store_id);
	
	List<Store> storeList();
	
	boolean insertStore(Store store);
	
	boolean deleteStore(Integer store_id);
	
	boolean editStore(Store store);
	
	Integer countStore();
	
	boolean setBrandsNum(Integer store_id, Integer brands_num);

	List<Store> searchStoreByDistance(String[] userCoordinates, List<Integer> storeId,int searchRadius);
}
