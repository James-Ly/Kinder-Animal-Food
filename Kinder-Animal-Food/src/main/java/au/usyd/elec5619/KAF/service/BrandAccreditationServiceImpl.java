package au.usyd.elec5619.KAF.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.usyd.elec5619.KAF.dao.BrandAccreditationDao;
import au.usyd.elec5619.KAF.model.BrandAccreditation;

@Service
public class BrandAccreditationServiceImpl implements BrandAccreditationService{

	@Autowired
	BrandAccreditationDao brandAccreditationDao;

	@Override
	@Transactional
	public BrandAccreditation searchBrandAccreditation(Integer brandAccreditation_id) {
		
		return brandAccreditationDao.searchBrandAccreditation(brandAccreditation_id);
	}
	
	@Override
	@Transactional
	public BrandAccreditation searchBrandAccreditation(Integer brand_id, Integer accreditation_id) {
		
		return brandAccreditationDao.searchBrandAccreditation(brand_id, accreditation_id);
	}
	
	@Override
	@Transactional
	public List<BrandAccreditation> brandAccreditationList(){
		
		return brandAccreditationDao.brandAccreditationList();
	}
	
	@Override
	@Transactional
	public List<Integer> searchBrandByAccreditation(Integer accreditation_id){
		
		return brandAccreditationDao.searchBrandByAccreditation(accreditation_id);
	}
	
	@Override
	@Transactional
	public List<Integer> searchAccreditationByBrand(Integer brand_id){
		
		return brandAccreditationDao.searchAccreditationByBrand(brand_id);
	}
	
	@Override
	@Transactional
	public boolean insertBrandAccreditation(BrandAccreditation brandAccreditation) {
		
		if (brandAccreditationDao.searchBrandAccreditation(brandAccreditation.getBrand_id(), brandAccreditation.getBrandAccreditation_id()) != null) {
			return false;
		} else {
			brandAccreditationDao.insertBrandAccreditation(brandAccreditation);
			return true;
		}
	}
	
	@Override
	@Transactional
	public boolean deleteBrandAccreditation(Integer brandAccreditation_id) {
		
		if (brandAccreditationDao.searchBrandAccreditation(brandAccreditation_id) != null) {
			brandAccreditationDao.deleteBrandAccreditation(brandAccreditation_id);
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	@Transactional
	public boolean deleteBrandAccreditation(Integer brand_id, Integer accreditation_id) {
		
		if (brandAccreditationDao.searchBrandAccreditation(brand_id, accreditation_id) != null) {
			
			brandAccreditationDao.deleteBrandAccreditation(brandAccreditationDao.searchBrandAccreditation(brand_id, accreditation_id).getBrandAccreditation_id());
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	@Transactional
	public boolean editBrandAccreditation(BrandAccreditation brandAccreditation) {
		
		if (brandAccreditationDao.searchBrandAccreditation(brandAccreditation.getBrandAccreditation_id()) != null) {
			
			brandAccreditationDao.editBrandAccreditation(brandAccreditation);
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	@Transactional
	public Integer countBrandAccreditation() {
		
		return brandAccreditationDao.brandAccreditationList().size();
	}
}