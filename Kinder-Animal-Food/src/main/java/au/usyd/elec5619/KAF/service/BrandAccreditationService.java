package au.usyd.elec5619.KAF.service;

import java.util.List;

import au.usyd.elec5619.KAF.model.BrandAccreditation;

public interface BrandAccreditationService {

	BrandAccreditation searchBrandAccreditation(Integer brandAccreditation_id);
	
	BrandAccreditation searchBrandAccreditation(Integer brand_id, Integer accreditation_id);
	
	List<BrandAccreditation> brandAccreditationList();
	
	List<Integer> searchBrandByAccreditation(Integer accreditation_id);
	
	List<Integer> searchAccreditationByBrand(Integer brand_id);
	
	boolean insertBrandAccreditation(BrandAccreditation brandAccreditation);
	
	boolean deleteBrandAccreditation(Integer brandAccreditation_id);
	
	boolean deleteBrandAccreditation(Integer brand_id, Integer accreditation_id);
	
	boolean editBrandAccreditation(BrandAccreditation brandAccreditation);
	
	Integer countBrandAccreditation();
}