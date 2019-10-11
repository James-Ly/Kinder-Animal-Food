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
	public Product searchProductByID(Integer product_id) {
		
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
	public List<Product> searchProduct(Product product) {
		
		Session currentSession = sessionFactory.getCurrentSession();

		Query<Product> theQuery = currentSession.createQuery(
				"from Product where store_id=:pStoreID AND brand_id=:pBrandID",
				Product.class);
		theQuery.setParameter("pStoreID", product.getStore_id());
		theQuery.setParameter("pBrandID", product.getBrand_id());

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

		Query theQuery = currentSession.createQuery("delete Product where product_id = :pID");
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
				
		Query theQuery = currentSession.createQuery(
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
