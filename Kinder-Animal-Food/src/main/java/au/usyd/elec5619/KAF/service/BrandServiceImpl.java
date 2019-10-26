package au.usyd.elec5619.KAF.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.usyd.elec5619.KAF.dao.BrandDao;
import au.usyd.elec5619.KAF.model.Brand;
import au.usyd.elec5619.KAF.user.CrmBrandWithRating;

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
	public Brand searchBrand(Integer brand_id) {

		return brandDao.searchBrand(brand_id);
	}
	
	@Override
	@Transactional
	public List<CrmBrandWithRating> searchBrandByNameCategoryRating(String brand_name,String brand_category,String brand_rating){
		return brandDao.searchBrandByNameCategoryRating(brand_name, brand_category,brand_rating);
	}

	@Override
	@Transactional
	public List<Brand> brandList() {
		return brandDao.brandList();
	}

	@Override
	@Transactional
	public List<Brand> searchBrand(String brand_name, String brand_category) {
		return brandDao.searchBrand(brand_name, brand_category);
	}
	
	@Override
	@Transactional
	public List<String> searchDistinctCategory() {
		return brandDao.searchDistinctCategory();
	}

	@Override
	@Transactional
	public boolean insertBrand(Brand brand) {

		if (brandDao.searchBrand(brand.getBrand_name(), brand.getBrand_category()).size() != 0) {
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

		if (brandDao.searchBrand(brand.getBrand_id()) != null) {
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

	@Override
	@Transactional
	public Brand searchExactBrand(String brand_name) {
		return brandDao.searchExactBrand(brand_name);
	}
	
	
}
