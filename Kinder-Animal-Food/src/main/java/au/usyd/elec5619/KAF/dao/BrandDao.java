package au.usyd.elec5619.KAF.dao;

import java.util.List;

import au.usyd.elec5619.KAF.model.Brand;


public interface BrandDao {
	
	List<Brand> searchBrand(String brand_name);
	
	Brand searchBrand(Integer brand_id);
	
	List<Brand> searchBrand(String brand_name, String brand_category);
	
	List<Brand> searchBrandByNameCategoryRating(String brand_name,String brand_category,String brand_rating);
	
	List<Brand> brandList();
	
	boolean insertBrand(Brand brand);
	
	boolean deleteBrand(Integer brand_id);
	
	boolean editBrand(Brand brand);
	 
}
