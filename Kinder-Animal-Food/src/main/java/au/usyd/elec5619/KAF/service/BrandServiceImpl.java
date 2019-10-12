package au.usyd.elec5619.KAF.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.usyd.elec5619.KAF.dao.AccreditationDao;
import au.usyd.elec5619.KAF.dao.BrandDao;
import au.usyd.elec5619.KAF.model.Brand;
import au.usyd.elec5619.KAF.model.BrandWithAccreditation;
import au.usyd.elec5619.KAF.model.BrandWithAllAccreditations;

@Service
public class BrandServiceImpl implements BrandService {

	@Autowired
	BrandDao brandDao;
	
	@Autowired
	AccreditationDao accreditationDao;

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
			return false;
		} else {
			brandDao.editBrand(brand);
			return true;
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
	public BrandWithAccreditation setBrandWithAccreditation(Brand brand) {
		
		BrandWithAccreditation brandWithAccreditation = new BrandWithAccreditation();
		
		brandWithAccreditation.setAccreditation(accreditationDao.searchAccreditationByID(brand.getAccreditation_id()));
		brandWithAccreditation.setBrand_id(brand.getBrand_id());
		brandWithAccreditation.setBrand_name(brand.getBrand_name());
		brandWithAccreditation.setBrand_category(brand.getBrand_category());
		brandWithAccreditation.setImage(brand.getImage());
		
		return brandWithAccreditation;
	}
	
	@Override
	@Transactional
	public BrandWithAllAccreditations setBrandWithAllAccreditations(Brand brand) {
		
		BrandWithAllAccreditations brandWithAllAccreditations = new BrandWithAllAccreditations();
		
		brandWithAllAccreditations.setAccreditation(accreditationDao.searchAccreditationByID(brand.getAccreditation_id()));
		brandWithAllAccreditations.setBrand_ids(brand.getBrand_id());
		brandWithAllAccreditations.setBrand_name(brand.getBrand_name());
		brandWithAllAccreditations.setBrand_category(brand.getBrand_category());
		brandWithAllAccreditations.setImage(brand.getImage());
		
		return brandWithAllAccreditations;
	}
	
	@Override
	@Transactional
	public BrandWithAllAccreditations setBrandWithAllAccreditations(BrandWithAllAccreditations brandWithAllAccreditations, Brand brand) {
		
		brandWithAllAccreditations.setAccreditation(accreditationDao.searchAccreditationByID(brand.getAccreditation_id()));
		brandWithAllAccreditations.setBrand_ids(brand.getBrand_id());
		
		return brandWithAllAccreditations;
	}
	
	@Override
	@Transactional
	public List<Brand> searchBrandByNameCategory(String brand_name, String brand_category) {
		return brandDao.searchBrandByNameCategory(brand_name, brand_category);
	}
}
