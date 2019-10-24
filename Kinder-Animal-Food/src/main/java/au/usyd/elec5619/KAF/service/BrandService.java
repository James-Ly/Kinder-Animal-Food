package au.usyd.elec5619.KAF.service;

import java.util.List;

import au.usyd.elec5619.KAF.model.Brand;

public interface BrandService {

	List<Brand> searchBrand(String brand_name);
	
	List<Brand> searchBrand(String brand_name, String brand_category);
	
	Brand searchBrand(Integer brand_id);
	
	public List<Brand> searchBrandByNameCategoryRating(String brand_name,String brand_category,String brand_rating);
	
	List<Brand> brandList();
	
	boolean insertBrand(Brand brand);
	
	boolean deleteBrand(Integer brand_id);
	
	boolean editBrand(Brand brand);
	
	Integer countBrand();
}