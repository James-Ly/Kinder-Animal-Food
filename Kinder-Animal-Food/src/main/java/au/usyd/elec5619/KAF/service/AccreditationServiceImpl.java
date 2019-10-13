package au.usyd.elec5619.KAF.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.usyd.elec5619.KAF.dao.AccreditationDao;
import au.usyd.elec5619.KAF.model.Accreditation;

@Service
public class AccreditationServiceImpl implements AccreditationService {

	@Autowired
	AccreditationDao accreditationDao;

	@Override
	@Transactional
	public Accreditation searchAccreditationByID(Integer accreditation_id) {

		return accreditationDao.searchAccreditationByID(accreditation_id);
	}
	
	@Override
	@Transactional
	public Accreditation searchAccreditation(Accreditation accreditation) {

		return accreditationDao.searchAccreditation(accreditation);
	}

	@Override
	@Transactional
	public List<Accreditation> accreditationList() {
		return accreditationDao.accreditationList();
	}

	@Override
	@Transactional
	public boolean insertAccreditation(Accreditation accreditation) {

		if (accreditationDao.insertAccreditation(accreditation)) {
			return true;
		}
		return false;
		
	}

	@Override
	@Transactional
	public boolean deleteAccreditation(Integer accreditation_id) {

		if (accreditationDao.deleteAccreditation(accreditation_id)) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	@Transactional
	public boolean editAccreditation(Accreditation accreditation) {

		if (accreditationDao.searchAccreditation(accreditation) != null) {
			accreditationDao.editAccreditation(accreditation);
			return true;
		} else {
			return false;
		}

	}

	@Override
	@Transactional
	public Integer countAccreditation() {

		Integer conut = accreditationDao.accreditationList().size();

		return conut;
	}
}
