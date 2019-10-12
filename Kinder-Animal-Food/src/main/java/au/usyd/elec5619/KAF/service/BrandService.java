package au.usyd.elec5619.KAF.service;

import java.util.List;

import au.usyd.elec5619.KAF.model.Brand;
import au.usyd.elec5619.KAF.model.BrandWithAccreditation;
import au.usyd.elec5619.KAF.model.BrandWithAllAccreditations;

public interface BrandService {

	List<Brand> searchBrandByName(String brand_name);
	
	Brand searchBrandByID(Integer brand_id);
	
	List<Brand> brandList();
	
	boolean insertBrand(Brand brand);
	
	boolean deleteBrand(Integer brand_id);
	
	boolean editBrand(Brand brand);
	
	Integer countBrand();
	
	BrandWithAccreditation setBrandWithAccreditation(Brand brand);
	
	BrandWithAllAccreditations setBrandWithAllAccreditations(Brand brand);
	
	BrandWithAllAccreditations setBrandWithAllAccreditations(BrandWithAllAccreditations brandWithAllAccreditations, Brand brand);
	
	List<Brand> searchBrandByNameCategory(String brand_name, String brand_category);
	
}