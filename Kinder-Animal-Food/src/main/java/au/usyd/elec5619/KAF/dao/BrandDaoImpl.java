package au.usyd.elec5619.KAF.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import au.usyd.elec5619.KAF.model.Brand;

@Repository
public class BrandDaoImpl implements BrandDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<Brand> searchBrandByName(String brand_name) {
		
		Session currentSession = sessionFactory.getCurrentSession();

		Query<Brand> theQuery = currentSession.createQuery("from Brand where brand_name=:bName", Brand.class);
		theQuery.setParameter("bName", brand_name);

		List<Brand> brands = null;
		try {
			brands = theQuery.getResultList();
		} catch (Exception e) {
			brands = null;
		}

		return brands;
	}
	@Override
	@Transactional
	public 	Brand searchBrandByID(Integer brand_id) {
		
		Session currentSession = sessionFactory.getCurrentSession();

		Query<Brand> theQuery = currentSession.createQuery("from Brand where brand_id=:bID", Brand.class);
		theQuery.setParameter("bID", brand_id);

		Brand brand = null;
		try {
			brand = theQuery.getSingleResult();
		} catch (Exception e) {
			brand = null;
		}

		return brand;
	}

	@Override
	@Transactional
	public List<Brand> searchBrand(Brand brand) {
		
		Session currentSession = sessionFactory.getCurrentSession();

		Query<Brand> theQuery = currentSession.createQuery(
				"from Brand where brand_name=:bName AND brand_category=:bCategory",
				Brand.class);
		theQuery.setParameter("bName", brand.getBrand_name());
		theQuery.setParameter("bCategory", brand.getBrand_category());


		List<Brand> brands = null;
		try {
			brands = theQuery.getResultList();
		} catch (Exception e) {
			brands = null;
		}

		return brands;
	}
	
	@Override
	@Transactional
	public List<Brand> brandList() {

		Session currentSession = sessionFactory.getCurrentSession();

		Query<Brand> theQuery = currentSession.createQuery("from Brand", Brand.class);

		List<Brand> brands = null;
		try {
			brands = theQuery.getResultList();
		} catch (Exception e) {
			brands = null;
		}

		return brands;
	}
	
	@Override
	@Transactional
	public boolean insertBrand(Brand brand) {
		
		Session currentSession = sessionFactory.getCurrentSession();

		try {
			currentSession.save(brand);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	@Override
	@Transactional
	public boolean deleteBrand(Integer brand_id) {
		
		Session currentSession = sessionFactory.getCurrentSession();

		Query theQuery = currentSession.createQuery("delete Brand where brand_id = :bID");
		theQuery.setParameter("bID", brand_id);

		try {
			theQuery.executeUpdate();
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	@Override
	@Transactional
	public boolean editBrand(Brand brand) {
		
		Session currentSession = sessionFactory.getCurrentSession();
				
		Query theQuery = currentSession.createQuery(
				"update Brand set brand_name=:bName AND brand_category=:bAddress AND image=:bImage where brand_id=:bID");
		theQuery.setParameter("bName", brand.getBrand_name());
		theQuery.setParameter("bAddress", brand.getBrand_category());
		theQuery.setParameter("bImage", brand.getImage());
		theQuery.setParameter("bID", brand.getBrand_id());
		
		try {
			theQuery.executeUpdate();
		} catch (Exception e) {
			return false;
		}

		return true;
	}
}
