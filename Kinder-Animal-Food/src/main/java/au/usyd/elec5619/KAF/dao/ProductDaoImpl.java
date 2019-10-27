package au.usyd.elec5619.KAF.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import au.usyd.elec5619.KAF.model.Product;

@Repository
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public Product searchProduct(Integer product_id) {
		
		Session currentSession = sessionFactory.getCurrentSession();

		Query<Product> theQuery = currentSession.createQuery("from Product where product_id=:pID", Product.class);
		theQuery.setParameter("pID", product_id);

		Product product = null;
		try {
			product = theQuery.getSingleResult();
		} catch (Exception e) {
			product = null;
		}

		return product;
	}

	@Override
	@Transactional
	public Product searchProduct(Integer store_id, Integer brand_id) {
		
		Session currentSession = sessionFactory.getCurrentSession();

		Query<Product> theQuery = currentSession.createQuery(
				"from Product where store_id=:pStoreID AND brand_id=:pBrandID",
				Product.class);
		theQuery.setParameter("pStoreID", store_id);
		theQuery.setParameter("pBrandID", brand_id);

		Product product = null;
		try {
			product = theQuery.getSingleResult();
		} catch (Exception e) {
			product = null;
		}

		return product;
	}
	
	@Override
	@Transactional
	public List<Product> productList() {

		Session currentSession = sessionFactory.getCurrentSession();

		Query<Product> theQuery = currentSession.createQuery("from Product", Product.class);

		List<Product> products = null;
		try {
			products = theQuery.getResultList();
		} catch (Exception e) {
			products = null;
		}

		return products;
	}
	
	@Override
	@Transactional
	public List<Integer> searchBrandByStore(Integer store_id) {
		
		Session currentSession = sessionFactory.getCurrentSession();

		Query theQuery = currentSession.createQuery("select brand_id from Product where store_id=:sID");
		theQuery.setParameter("sID", store_id);

		List<Integer> store_ids = null;
		try {
			store_ids = theQuery.getResultList();
		} catch (Exception e) {
			store_ids = null;
		}

		return store_ids;
	}
	
	@Override
	@Transactional
	public List<Integer> searchStoreByBrand(Integer brand_id){
		Session currentSession = sessionFactory.getCurrentSession();

		Query theQuery = currentSession.createQuery("select store_id from Product where brand_id=:bID");
		theQuery.setParameter("bID", brand_id);

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
	public boolean insertProduct(Product product) {
		
		Session currentSession = sessionFactory.getCurrentSession();

		try {
			currentSession.save(product);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	@Override
	@Transactional
	public boolean deleteProduct(Integer product_id) {
		
		Session currentSession = sessionFactory.getCurrentSession();

		Query<Integer> theQuery = currentSession.createQuery("delete Product where product_id = :pID");
		theQuery.setParameter("pID", product_id);

		try {
			theQuery.executeUpdate();
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	@Override
	@Transactional
	public boolean editProduct(Product product) {

		Session currentSession = sessionFactory.getCurrentSession();
				
		Query<Integer> theQuery = currentSession.createQuery(
				"update Product set store_id=:pStoreID AND brand_id=:pBrandID where product_id=:pID");
		theQuery.setParameter("pStoreID", product.getStore_id());
		theQuery.setParameter("pBrandID", product.getBrand_id());
		theQuery.setParameter("pID", product.getProduct_id());
		
		try {
			theQuery.executeUpdate();
		} catch (Exception e) {
			return false;
		}

		return true;
	}
}
