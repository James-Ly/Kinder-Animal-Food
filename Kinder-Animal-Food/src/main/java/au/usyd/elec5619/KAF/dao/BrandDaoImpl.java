package au.usyd.elec5619.KAF.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import au.usyd.elec5619.KAF.model.Brand;
import au.usyd.elec5619.KAF.model.BrandAccreditation;
import au.usyd.elec5619.KAF.service.BrandAccreditationService;

@Repository
public class BrandDaoImpl implements BrandDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private BrandAccreditationService brandAccreditaionService;

	@Override
	@Transactional
	public List<Brand> searchBrand(String brand_name) {
		
		Session currentSession = sessionFactory.getCurrentSession();

		Query<Brand> theQuery = currentSession.createQuery("from Brand where brand_name LIKE:bName", Brand.class);
		theQuery.setParameter("bName","%"+brand_name+"%" );

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
	public List<Brand> searchBrandByNameCategoryRating(String brand_name, String brand_category, String brand_rating) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		/***************************
		 * Handle the Brand query
		 ***************************/
		
		List<BrandAccreditation> baList = brandAccreditaionService.searchBrandAccreditationByRating(brand_rating);
		System.out.println("BrandDaoImpl================================");
		for(int i = 0 ; i<baList.size();i++) {
			System.out.println(baList.get(i));
		}
		System.out.println("BrandDaoImpl================================");
		
		List<Integer> baIdList = new ArrayList<Integer>();
		for(int i = 0 ; i < baList.size(); i++) {
			baIdList.add(baList.get(i).getBrand_id());
		}
		
		boolean nullName = brand_name == null;
		boolean nullCategory = brand_category == null;
		boolean nullRating = baIdList.size() == 0;
		System.out.println("nullRating "+nullRating);
		String queryString = "";
		if(nullName && nullCategory && nullRating) {
			queryString += "from Brand ";
		} else {
			queryString += "from Brand where ";
		}
		if(!nullName) {
			queryString += "brand_name LIKE:bName ";
			if(!nullCategory) {
				queryString += "AND brand_category LIKE:bCategory ";
			}
			if(!nullRating) {
				queryString += "AND brand_id in :ids ";
			}
		} else if (!nullCategory) {
			queryString += "brand_category LIKE:bCategory ";
			if(!nullRating) {
				queryString += "AND brand_id in :ids ";
			}
		} else if (!nullRating) {
			queryString += "brand_id IN :ids ";
		}
		Query<Brand> theQuery = currentSession.createQuery(queryString, Brand.class);
		if(!nullName) {
			theQuery.setParameter("bName", "%"+brand_name+"%");
		} 
		if (!nullCategory) {
			theQuery.setParameter("bCategory", "%"+brand_category+"%");
		}
		if (!nullRating) {
			theQuery.setParameterList("ids", baIdList);
		}
		System.out.println("Query String is "+queryString);

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
	public Brand searchBrand(Integer brand_id) {
		
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
	public List<Brand> searchBrand(String brand_name, String brand_category) {
		
		Session currentSession = sessionFactory.getCurrentSession();

		Query<Brand> theQuery = currentSession.createQuery("from Brand where brand_name=:bName AND brand_category=:bCategory", Brand.class);
		theQuery.setParameter("bName", brand_name);
		theQuery.setParameter("bCategory", brand_category);

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
				"update Brand set brand_name=:bName, brand_category=:bAddress, image=:bImage where brand_id=:bID");
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
