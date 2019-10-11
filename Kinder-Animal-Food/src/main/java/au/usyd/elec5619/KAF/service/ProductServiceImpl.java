package au.usyd.elec5619.KAF.service;

import java.util.List;

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
	public List<Product> productList() {
		return productDao.productList();
	}

	@Override
	@Transactional
	public boolean insertProduct(Product product) {

		if (productDao.searchProduct(product).size() != 0) {
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

		if (productDao.searchProduct(product) != null) {
			productDao.editProduct(product);
			return true;
		} else {
			return false;
		}

	}

	@Override
	@Transactional
	public Integer countProduct() {

		Integer conut = productDao.productList().size();

		return conut;
	}
}
