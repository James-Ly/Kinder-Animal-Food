package au.usyd.elec5619.KAF.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import au.usyd.elec5619.domain.Brand;


@Repository
public interface BrandService {

	List<Brand> validateBrand(String brand_name);
	
	boolean insertBrand(Brand brand);
	
	boolean deleteBrand(Brand brand);
	
	boolean editBrand(Brand brand);
	
	Integer countBrand();
}