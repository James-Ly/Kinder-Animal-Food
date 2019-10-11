package au.usyd.elec5619.KAF.service;

import java.util.List;

import au.usyd.elec5619.KAF.model.Brand;

public interface BrandService {

	List<Brand> searchBrand(String brand_name);
	
	List<Brand> brandList();
	
	boolean insertBrand(Brand brand);
	
	boolean deleteBrand(Brand brand);
	
	boolean editBrand(Brand brand);
	
	Integer countBrand();
}