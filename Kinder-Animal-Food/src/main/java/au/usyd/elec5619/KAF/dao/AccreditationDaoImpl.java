package au.usyd.elec5619.KAF.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import au.usyd.elec5619.KAF.model.Accreditation;

@Repository
public class AccreditationDaoImpl implements AccreditationDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public Accreditation searchAccreditation(Integer accreditation_id) {
		
		Session currentSession = sessionFactory.getCurrentSession();

		Query<Accreditation> theQuery = currentSession.createQuery("from Accreditation where accreditation_id=:aID", Accreditation.class);
		theQuery.setParameter("aID", accreditation_id);

		Accreditation accreditation = null;
		try {
			accreditation = theQuery.getSingleResult();
		} catch (Exception e) {
			accreditation = null;
		}

		return accreditation;
	}
	
	@Override
	@Transactional
	public Accreditation searchAccreditation(String accreditation_name) {
		
		Session currentSession = sessionFactory.getCurrentSession();

		Query<Accreditation> theQuery = currentSession.createQuery(
				"from Accreditation where accreditation_name=:aName",
				Accreditation.class);
		theQuery.setParameter("aName", accreditation_name);

		Accreditation accreditation = null;
		try {
			accreditation = theQuery.getSingleResult();
		} catch (Exception e) {
			accreditation = null;
		}

		return accreditation;
	}
	
	@Override
	@Transactional
	public List<Accreditation> accreditationList() {

		Session currentSession = sessionFactory.getCurrentSession();

		Query<Accreditation> theQuery = currentSession.createQuery("from Accreditation", Accreditation.class);

		List<Accreditation> accreditations = null;
		try {
			accreditations = theQuery.getResultList();
		} catch (Exception e) {
			accreditations = null;
		}

		return accreditations;
	}
	
	@Override
	@Transactional
	public boolean insertAccreditation(Accreditation accreditation) {
		
		Session currentSession = sessionFactory.getCurrentSession();

		try {
			currentSession.save(accreditation);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	@Override
	@Transactional
	public boolean deleteAccreditation(Integer accreditation_id) {
		
		Session currentSession = sessionFactory.getCurrentSession();

		Query theQuery = currentSession.createQuery("delete Accreditation where accreditation_id = :aID");
		theQuery.setParameter("aID", accreditation_id);

		try {
			theQuery.executeUpdate();
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	@Override
	@Transactional
	public boolean editAccreditation(Accreditation accreditation) {

		Session currentSession = sessionFactory.getCurrentSession();
				
		Query theQuery = currentSession.createQuery(
				"update Accreditation set accreditation_name=:aName, rating=:aRating where accreditation_id=:aID");
		theQuery.setParameter("aName", accreditation.getAccreditation_name());
		theQuery.setParameter("aRating", accreditation.getRating());
		theQuery.setParameter("aID", accreditation.getAccreditation_id());
		
		try {
			theQuery.executeUpdate();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
