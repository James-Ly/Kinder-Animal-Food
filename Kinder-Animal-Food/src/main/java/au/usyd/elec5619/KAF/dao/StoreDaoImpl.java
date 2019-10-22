package au.usyd.elec5619.KAF.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import au.usyd.elec5619.KAF.model.Store;

@Repository
public class StoreDaoImpl implements StoreDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<Store> searchStore(String store_name) {

		Session currentSession = sessionFactory.getCurrentSession();

		Query<Store> theQuery = currentSession.createQuery("from Store where store_name=:sName", Store.class);
		theQuery.setParameter("sName", store_name);

		List<Store> stores = null;
		try {
			stores = theQuery.getResultList();
		} catch (Exception e) {
			stores = null;
		}

		return stores;
	}

	@Override
	@Transactional
	public Store searchStore(Integer store_id) {

		Session currentSession = sessionFactory.getCurrentSession();

		Query<Store> theQuery = currentSession.createQuery("from Store where store_id=:sID", Store.class);
		theQuery.setParameter("sID", store_id);

		Store store = null;
		try {
			store = theQuery.getSingleResult();
		} catch (Exception e) {
			store = null;
		}

		return store;
	}
	
	@Override
	@Transactional
	public List<Store> searchStore(Store store) {

		Session currentSession = sessionFactory.getCurrentSession();

		Query<Store> theQuery = currentSession.createQuery(
				"from Store where store_address=:sAddress AND store_state=:sState AND store_postcode=:sPostcode AND store_longitude=:sLongitude AND store_latitude=:sLatitude",
				Store.class);
		theQuery.setParameter("sAddress", store.getStore_address());
		theQuery.setParameter("sState", store.getStore_state());
		theQuery.setParameter("sPostcode", store.getStore_postcode());
		theQuery.setParameter("sLongitude", store.getStore_longitude());
		theQuery.setParameter("sLatitude", store.getStore_latitude());

		List<Store> stores = null;
		try {
			stores = theQuery.getResultList();
		} catch (Exception e) {
			stores = null;
		}

		return stores;
	}

	@Override
	@Transactional
	public List<Store> storeList() {

		Session currentSession = sessionFactory.getCurrentSession();

		Query<Store> theQuery = currentSession.createQuery("from Store", Store.class);

		List<Store> stores = null;
		try {
			stores = theQuery.getResultList();
		} catch (Exception e) {
			stores = null;
		}

		return stores;
	}

	@Override
	@Transactional
	public boolean insertStore(Store store) {

		Session currentSession = sessionFactory.getCurrentSession();

		try {
			currentSession.save(store);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	@Override
	@Transactional
	public boolean deleteStore(Integer store_id) {

		Session currentSession = sessionFactory.getCurrentSession();

		Query theQuery = currentSession.createQuery("delete Store where store_id = :sID");
		theQuery.setParameter("sID", store_id);

		try {
			theQuery.executeUpdate();
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	@Override
	@Transactional
	public boolean editStore(Store store) {

		Session currentSession = sessionFactory.getCurrentSession();

		Query theQuery = currentSession.createQuery(

				"update Store set store_name=:sName, store_address=:sAddress, store_state=:sState, store_postcode=:sPostcode, store_longitude=:sLongitude, store_latitude=:sLatitude where store_id=:sID");

		theQuery.setParameter("sName", store.getStore_name());
		theQuery.setParameter("sAddress", store.getStore_address());
		theQuery.setParameter("sState", store.getStore_state());
		theQuery.setParameter("sPostcode", store.getStore_postcode());
		theQuery.setParameter("sLongitude", store.getStore_longitude());
		theQuery.setParameter("sLatitude", store.getStore_latitude());
		theQuery.setParameter("sID", store.getStore_id());
		
		try {
			theQuery.executeUpdate();
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	
	@Override
	@Transactional
	public boolean setBrandsNum(Integer store_id, Integer brands_num) {
		
		Session currentSession = sessionFactory.getCurrentSession();

		Query theQuery = currentSession.createQuery("update Store set brands_num=:sBrandsNum where store_id=:sID");
		theQuery.setParameter("sBrandsNum", brands_num);
		theQuery.setParameter("sID", store_id);
		
		try {
			theQuery.executeUpdate();
		} catch (Exception e) {
			return false;
		}

		return true;
	}
}
