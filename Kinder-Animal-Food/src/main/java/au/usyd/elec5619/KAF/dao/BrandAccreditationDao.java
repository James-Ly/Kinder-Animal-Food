package au.usyd.elec5619.KAF.dao;

import java.util.List;

import au.usyd.elec5619.KAF.model.BrandAccreditation;

public interface BrandAccreditationDao {
	
	BrandAccreditation searchBrandAccreditation(Integer brandAccreditation_id);
	
	BrandAccreditation searchBrandAccreditation(Integer brand_id, Integer accreditation_id);
	
	List<BrandAccreditation> searchBrandAccreditationByRating(String brand_rating);
	
	List<BrandAccreditation> brandAccreditationList();
	
	List<Integer> searchBrandByAccreditation(Integer accreditation_id);
	
	List<Integer> searchAccreditationByBrand(Integer brand_id);
	
	boolean insertBrandAccreditation(BrandAccreditation brandAccreditation);
	
	boolean deleteBrandAccreditation(Integer brandAccreditation_id);
	
	boolean editBrandAccreditation(BrandAccreditation brandAccreditation);
}
