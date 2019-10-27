package au.usyd.elec5619.KAF.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import au.usyd.elec5619.KAF.model.Report;

@Repository
public class ReportDaoImpl implements ReportDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public Report searchReportByReportID(Integer report_id) {

		Session currentSession = sessionFactory.getCurrentSession();

		Query<Report> theQuery = currentSession.createQuery("from Report where report_id=:rID", Report.class);
		theQuery.setParameter("rID", report_id);

		Report report = null;
		try {
			report = theQuery.getSingleResult();
		} catch (Exception e) {
			report = null;
		}

		return report;
	}

	@Override
	@Transactional
	public List<Report> searchReportByUserID(Integer user_id) {

		Session currentSession = sessionFactory.getCurrentSession();

		Query<Report> theQuery = currentSession.createQuery("from Report where user_id=:uID", Report.class);
		theQuery.setParameter("uID", user_id);

		List<Report> reports = null;
		try {
			reports = theQuery.getResultList();
		} catch (Exception e) {
			reports = null;
		}

		return reports;
	}

	@Override
	@Transactional
	public List<Report> searchReportByDate(Date startDate, Date endDate) {
		
		Session currentSession = sessionFactory.getCurrentSession();

		Query<Report> theQuery = currentSession
				.createQuery("from Report where report_time>=:startDate AND report_time<=:endDate", Report.class);
		theQuery.setParameter("startDate", startDate);
		theQuery.setParameter("endDate", endDate);

		List<Report> reports = null;
		try {
			reports = theQuery.getResultList();
		} catch (Exception e) {
			reports = null;
		}

		return reports;
	}

	@Override
	@Transactional
	public List<Report> ReportList() {

		Session currentSession = sessionFactory.getCurrentSession();

		Query<Report> theQuery = currentSession.createQuery("from Report", Report.class);

		List<Report> reports = null;
		try {
			reports = theQuery.getResultList();
		} catch (Exception e) {
			reports = null;
		}

		return reports;
	}

	@Override
	@Transactional
	public List<Report> searchReportByStatus(String status) {

		Session currentSession = sessionFactory.getCurrentSession();

		Query<Report> theQuery = currentSession.createQuery("from Report where status=:rstatus", Report.class);
		theQuery.setParameter("rstatus", status);

		List<Report> reports = null;
		try {
			reports = theQuery.getResultList();
		} catch (Exception e) {
			reports = null;
		}

		return reports;
	}

	@Override
	@Transactional
	public boolean insertReport(Report report) {

		Session currentSession = sessionFactory.getCurrentSession();

		try {
			currentSession.save(report);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	@Override
	@Transactional
	public boolean deleteReport(Integer report_id) {

		Session currentSession = sessionFactory.getCurrentSession();

		Query theQuery = currentSession.createQuery("delete Report where report_id=:rID");
		theQuery.setParameter("rID", report_id);

		try {
			theQuery.executeUpdate();
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	@Override
	@Transactional
	public boolean editReportStatus(Report report) {

		Session currentSession = sessionFactory.getCurrentSession();

		Query theQuery = currentSession.createQuery(
				"update Report set status=:rstatus where report_id=:rID");
		theQuery.setParameter("rstatus", report.getStatus());
		theQuery.setParameter("rID", report.getReport_id());

		try {
			theQuery.executeUpdate();
		} catch (Exception e) {
			return false;
		}

		return true;
	}
}
