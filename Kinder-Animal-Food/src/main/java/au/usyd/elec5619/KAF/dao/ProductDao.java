package au.usyd.elec5619.KAF.dao;

import java.util.List;

import au.usyd.elec5619.KAF.model.Product;

public interface ProductDao {
	
	Product searchProductByID(Integer product_id);
	
	List<Product> searchProduct(Product product);
	
	List<Product> productList();
	
	boolean insertProduct(Product product);
	
	boolean deleteProduct(Integer product_id);
	
	boolean editProduct(Product product);
	 
}
