package au.usyd.elec5619.KAF.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.usyd.elec5619.KAF.dao.BrandDao;
import au.usyd.elec5619.KAF.model.Brand;

@Service
public class BrandServiceImpl implements BrandService {

	@Autowired
	BrandDao brandDao;

	@Override
	@Transactional
	public List<Brand> searchBrand(String brand_name) {

		return brandDao.searchBrand(brand_name);
	}
	
	@Override
	@Transactional
	public List<Brand> brandList(){
		return brandDao.brandList();
	}

	@Override
	@Transactional
	public boolean insertBrand(Brand brand) {
		
		if (brandDao.searchBrand(brand).size() != 0) {
			return false;
		} else {
			brandDao.insertBrand(brand);
			return true;
		}
	}

	@Override
	@Transactional
	public boolean deleteBrand(Brand brand) {

		if (brandDao.searchBrand(brand).size() != 0) {
			brandDao.deleteBrand(brand.getBrand_id());
			return true;
		} else {
			return false;
		}

	}

	@Override
	@Transactional
	public boolean editBrand(Brand brand) {

		if (brandDao.searchBrand(brand) != null) {
			brandDao.editBrand(brand);
			return true;
		} else {
			return false;
		}

	}
	
	@Override
	@Transactional
	public Integer countBrand() {
		
		Integer conut = brandDao.brandList().size();
		
		return conut;
	}
}
