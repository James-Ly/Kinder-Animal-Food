package au.usyd.elec5619.KAF.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.usyd.elec5619.KAF.dao.StoreDao;
import au.usyd.elec5619.KAF.model.Store;

@Service
public class StoreServiceImpl implements StoreService {

	@Autowired
	StoreDao storeDao;

	@Override
	public List<Store> validateStore(String store_name) {

		return storeDao.searchStoreByName(store_name);
	}
	
	@Override
	public List<Store> storeList(){
		return storeDao.storeList();
	}

	@Override
	public boolean insertStore(Store store) {

		if (storeDao.searchStore(store) != null) {
			return false;
		} else {
			storeDao.insertStore(store);
			return true;
		}
	}

	@Override
	public boolean deleteStore(Store store) {

		if (storeDao.searchStore(store) != null) {
			storeDao.deleteStore(store);
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean editStore(Store store) {

		if (storeDao.searchStore(store) != null) {
			storeDao.editStore(store);
			return true;
		} else {
			return false;
		}

	}
	
	@Override
	public Integer countStore() {
		
		Integer conut = storeDao.countStore();
		
		return conut;
	}

}
