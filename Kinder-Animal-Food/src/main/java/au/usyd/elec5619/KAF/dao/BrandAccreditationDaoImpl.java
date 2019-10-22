package au.usyd.elec5619.KAF.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import au.usyd.elec5619.KAF.model.BrandAccreditation;

@Repository
public class BrandAccreditationDaoImpl implements BrandAccreditationDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public BrandAccreditation searchBrandAccreditation(Integer brandAccreditation_id) {
	
		
		Session currentSession = sessionFactory.getCurrentSession();

		Query<BrandAccreditation> theQuery = currentSession.createQuery("from BrandAccreditation where brandAccreditation_id=:baID", BrandAccreditation.class);
		theQuery.setParameter("baID", brandAccreditation_id);

		BrandAccreditation brandAccreditation = null;
		try {
			brandAccreditation = theQuery.getSingleResult();
		} catch (Exception e) {
			brandAccreditation = null;
		}

		return brandAccreditation;
	}
	
	@Override
	@Transactional
	public BrandAccreditation searchBrandAccreditation(Integer brand_id, Integer accreditation_id) {

		Session currentSession = sessionFactory.getCurrentSession();

		Query<BrandAccreditation> theQuery = currentSession.createQuery("from BrandAccreditation where brand_id=:bID AND accreditation_id=:aID", BrandAccreditation.class);
		theQuery.setParameter("bID", brand_id);
		theQuery.setParameter("aID", accreditation_id);

		BrandAccreditation brandAccreditation = null;
		try {
			brandAccreditation = theQuery.getSingleResult();
		} catch (Exception e) {
			brandAccreditation = null;
		}

		return brandAccreditation;
	}

	@Override
	@Transactional
	public List<BrandAccreditation> brandAccreditationList(){
		
		Session currentSession = sessionFactory.getCurrentSession();

		Query<BrandAccreditation> theQuery = currentSession.createQuery("from BrandAccreditation", BrandAccreditation.class);

		List<BrandAccreditation> brandAccreditations = null;
		try {
			brandAccreditations = theQuery.getResultList();
		} catch (Exception e) {
			brandAccreditations = null;
		}

		return brandAccreditations;
	}
	
	@Override
	@Transactional
	public List<Integer> searchBrandByAccreditation(Integer accreditation_id){
		
		Session currentSession = sessionFactory.getCurrentSession();

		Query theQuery = currentSession.createQuery("select brand_id from BrandAccreditation where accreditation_id=:aID");
		theQuery.setParameter("aID", accreditation_id);

		List<Integer> brand_ids = null;
		try {
			brand_ids = theQuery.getResultList();
		} catch (Exception e) {
			brand_ids = null;
		}

		return brand_ids;
	}
	
	@Override
	@Transactional
	public List<Integer> searchAccreditationByBrand(Integer brand_id){
		
		Session currentSession = sessionFactory.getCurrentSession();

		Query theQuery = currentSession.createQuery("select accreditation_id from BrandAccreditation where brand_id=:bID");
		theQuery.setParameter("bID", brand_id);

		List<Integer> accreditation_ids = null;
		try {
			accreditation_ids = theQuery.getResultList();
		} catch (Exception e) {
			accreditation_ids = null;
		}

		return accreditation_ids;
	}
	
	@Override
	@Transactional
	public boolean insertBrandAccreditation(BrandAccreditation brandAccreditation) {
		
		Session currentSession = sessionFactory.getCurrentSession();

		try {
			currentSession.save(brandAccreditation);
		} catch (Exception e) {
			return false;
		}

		return true;
	}
	
	@Override
	@Transactional
	public boolean deleteBrandAccreditation(Integer brandAccreditation_id) {

		Session currentSession = sessionFactory.getCurrentSession();

		Query theQuery = currentSession.createQuery("delete BrandAccreditation where brandAccreditation_id = :baID");
		theQuery.setParameter("baID", brandAccreditation_id);

		try {
			theQuery.executeUpdate();
		} catch (Exception e) {
			return false;
		}

		return true;
	}
	
	@Override
	@Transactional
	public boolean editBrandAccreditation(BrandAccreditation brandAccreditation) {

		Session currentSession = sessionFactory.getCurrentSession();
				
		Query theQuery = currentSession.createQuery(
				"update BrandAccreditation set brand_id=:bID AND accreditation_id=:aID where brandAccreditation_id=:baID");
		theQuery.setParameter("bID", brandAccreditation.getBrand_id());
		theQuery.setParameter("aID", brandAccreditation.getAccreditation_id());
		theQuery.setParameter("baID", brandAccreditation.getBrandAccreditation_id());
		
		try {
			theQuery.executeUpdate();
		} catch (Exception e) {
			return false;
		}

		return true;
	}
}
