package au.usyd.elec5619.KAF.dao;

import java.util.List;

import au.usyd.elec5619.KAF.model.Accreditation;

public interface AccreditationDao {
	
	Accreditation searchAccreditationByID(Integer accreditation_id);
	
	List<Accreditation> searchAccreditation(Accreditation accreditation);
	
	List<Accreditation> accreditationList();
	
	boolean insertAccreditation(Accreditation accreditation);
	
	boolean deleteAccreditation(Integer accreditation_id);
	
	boolean editAccreditation(Accreditation accreditation);
	 
}
