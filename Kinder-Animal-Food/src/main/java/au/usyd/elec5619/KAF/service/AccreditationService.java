package au.usyd.elec5619.KAF.service;

import java.util.List;

import au.usyd.elec5619.KAF.model.Accreditation;

public interface AccreditationService {

	Accreditation searchAccreditation(Integer accreditation_id);
	
	Accreditation searchAccreditation(String accreditation_name);
	
	List<Accreditation> accreditationList();
	
	boolean insertAccreditation(Accreditation accreditation);
	
	boolean deleteAccreditation(Integer accreditation_id);
	
	boolean editAccreditation(Accreditation accreditation);
	
	Integer countAccreditation();
}