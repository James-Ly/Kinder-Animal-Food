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
	public List<Brand> searchBrandByName(String brand_name) {

		return brandDao.searchBrandByName(brand_name);
	}

	@Override
	@Transactional
	public Brand searchBrandByID(Integer brand_id) {

		return brandDao.searchBrandByID(brand_id);
	}

	@Override
	@Transactional
	public List<Brand> brandList() {
		return brandDao.brandList();
	}

	@Override
	@Transactional
	public boolean searchBrand(Brand brand) {

		if (brandDao.searchBrand(brand).size() != 0) {
			return true;
		} else {
			return false;
		}
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
	public boolean deleteBrand(Integer brand_id) {

		if (brandDao.deleteBrand(brand_id)) {
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
