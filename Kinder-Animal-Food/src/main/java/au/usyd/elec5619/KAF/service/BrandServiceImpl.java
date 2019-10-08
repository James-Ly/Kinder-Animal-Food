package au.usyd.elec5619.KAF.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.usyd.elec5619.KAF.dao.BrandDao;
import au.usyd.elec5619.KAF.model.Brand;

@Service
public class BrandServiceImpl implements BrandService {

	@Autowired
	BrandDao brandDao;

	@Override
	public List<Brand> validateBrand(String brand_name) {

		return brandDao.searchBrandByName(brand_name);
	}

	@Override
	public boolean insertBrand(Brand brand) {

		if (brandDao.searchBrand(brand) != null) {
			return false;
		} else {
			brandDao.insertBrand(brand);
			return true;
		}
	}

	@Override
	public boolean deleteBrand(Brand brand) {

		if (brandDao.searchBrand(brand) != null) {
			brandDao.deleteBrand(brand);
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean editBrand(Brand brand) {

		if (brandDao.searchBrand(brand) != null) {
			brandDao.editBrand(brand);
			return true;
		} else {
			return false;
		}

	}
	
	public Integer countBrand() {
		
		Integer conut = brandDao.countBrand();
		
		return conut;
	}

}
