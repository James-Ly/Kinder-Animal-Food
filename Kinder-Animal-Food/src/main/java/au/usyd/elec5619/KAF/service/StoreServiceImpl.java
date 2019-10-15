package au.usyd.elec5619.KAF.service;

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
	public List<Store> searchStoreByName(String store_name) {

		return storeDao.searchStoreByName(store_name);
	}

	@Override
	@Transactional
	public Store searchStoreByID(Integer store_id) {

		return storeDao.searchStoreByID(store_id);
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

		if (storeDao.deleteStore(store_id)) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	@Transactional
	public boolean editStore(Store store) {

		if (storeDao.searchStore(store).size() != 0) {
			return false;
		} else {
			storeDao.editStore(store);
			return true;
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

}
