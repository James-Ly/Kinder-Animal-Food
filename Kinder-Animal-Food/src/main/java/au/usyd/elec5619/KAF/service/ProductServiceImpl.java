package au.usyd.elec5619.KAF.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.usyd.elec5619.KAF.dao.ProductDao;
import au.usyd.elec5619.KAF.model.Product;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDao productDao;

	@Override
	@Transactional
	public Product searchProduct(Integer product_id) {

		return productDao.searchProductByID(product_id);
	}
	@Override
	@Transactional
	public Product searchProduct(Integer store_id, Integer brand_id) {
		
		return productDao.searchProduct(store_id, brand_id);
	}

	@Override
	@Transactional
	public List<Product> productList() {
		return productDao.productList();
	}
	
	@Override
	@Transactional
	public List<Integer> searchBrandByStore(Integer store_id){
		return productDao.searchBrandByStore(store_id);
	}
	
	@Override
	@Transactional
	public List<Integer> searchStoreByBrand(Integer brand_id){
		return productDao.searchStoreByBrand(brand_id);
	}

	@Override
	@Transactional
	public boolean insertProduct(Product product) {

		if (productDao.searchProduct(product.getStore_id(), product.getBrand_id()) != null) {
			return false;
		} else {
			productDao.insertProduct(product);
			return true;
		}
	}

	@Override
	@Transactional
	public boolean deleteProduct(Integer product_id) {

		if (productDao.deleteProduct(product_id)) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	@Transactional
	public boolean editProduct(Product product) {

		if (productDao.searchProduct(product.getStore_id(), product.getBrand_id()) != null) {
			return false;
		} else {
			productDao.editProduct(product);
			return true;
		}

	}

	@Override
	@Transactional
	public Integer countProduct() {

		Integer conut = productDao.productList().size();

		return conut;
	}
}
