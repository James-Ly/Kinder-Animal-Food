package au.usyd.elec5619.KAF.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import au.usyd.elec5619.KAF.model.Brand;


@Repository
public interface BrandDao {
	
//	List<Brand> ListBrand(String brand_name);
	
	List<Brand> searchBrandByName(String brand_name);
	
	List<Brand> searchBrand(Brand brand);
	
	void insertBrand(Brand brand);
	
	void deleteBrand(Brand brand);
	
	void editBrand(Brand brand);
	 
	Integer countBrand();
}
