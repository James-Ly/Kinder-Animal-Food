package au.usyd.elec5619.KAF.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.usyd.elec5619.KAF.dao.StoreDao;
import au.usyd.elec5619.KAF.model.Store;

@Service
public class StoreServiceImpl implements StoreService {

	@Autowired
	StoreDao storeDao;

	@Override
	@Transactional
	public List<Store> searchStore(String store_name) {

		return storeDao.searchStore(store_name);
	}

	@Override
	@Transactional
	public Store searchStore(Integer store_id) {

		return storeDao.searchStore(store_id);
	}

	@Override
	@Transactional
	public Store searchStore(Store store) {
		return storeDao.searchStore(store).get(0);
	}
	
	@Override
	@Transactional
	public List<Store> storeList() {
		
		return storeDao.storeList();
	}

	@Override
	@Transactional
	public boolean insertStore(Store store) {

		if (storeDao.searchStore(store).size() != 0) {
			return false;
		} else {
			storeDao.insertStore(store);
			return true;
		}
	}

	@Override
	@Transactional
	public boolean deleteStore(Integer store_id) {

		if (storeDao.searchStore(store_id) != null) {
			storeDao.deleteStore(store_id);
			return true;
		} else {
			return false;
		}

	}

	@Override
	@Transactional
	public boolean editStore(Store store) {

		if (storeDao.searchStore(store.getStore_id()) != null) {
			storeDao.editStore(store);
			return true;
		} else {
			return false;
		}
	}

	@Override
	@Transactional
	public Integer countStore() {

		Integer conut = storeDao.storeList().size();

		return conut;
	}

	@Override
	@Transactional
	public boolean setBrandsNum(Integer store_id, Integer brands_num) {
		
		if (storeDao.setBrandsNum(store_id, brands_num)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	@Transactional
	public List<Store> searchStoreByDistance(String[] userCoordinates, List<Integer> storeId,int searchRadius) {
		List<Store> result = new ArrayList<Store>();
		for(int i = 0; i < storeId.size();i++) {
			Store validStore = storeDao.searchStore(storeId.get(i));
			if(validDistance(userCoordinates,validStore,searchRadius)) {
				result.add(validStore);
			}
		}
		return result;
	}

	private boolean validDistance(String[] userCoordinates, Store validStore,int searchRadius) {
		double lat1 = Double.parseDouble(userCoordinates[0]);
		double lat2 = Double.parseDouble(validStore.getStore_latitude());;
		double lng1 = Double.parseDouble(userCoordinates[1]);;
		double lng2 = Double.parseDouble(validStore.getStore_longitude());;
		double distance = distance(lat1,lat2,lng1,lng2,0.0,0.0);
		return distance <= searchRadius;
	}
	
	private double distance(double lat1, double lat2, double lon1,
	        double lon2, double el1, double el2) {

	    final int R = 6371; // Radius of the earth

	    double latDistance = Math.toRadians(lat2 - lat1);
	    double lonDistance = Math.toRadians(lon2 - lon1);
	    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
	            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
	            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    double distance = R * c * 1000; // convert to meters

	    double height = el1 - el2;

	    distance = Math.pow(distance, 2) + Math.pow(height, 2);

	    return Math.sqrt(distance);
	}
}
