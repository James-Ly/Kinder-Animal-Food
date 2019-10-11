package au.usyd.elec5619.KAF.service;

import java.util.List;

import au.usyd.elec5619.KAF.model.Product;

public interface ProductService {

	Product searchProduct(Integer product_id);
	
	List<Product> productList();
	
	boolean insertProduct(Product product);
	
	boolean deleteProduct(Integer product_id);
	
	boolean editProduct(Product product);
	
	Integer countProduct();
}