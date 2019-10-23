package au.usyd.elec5619.KAF.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.usyd.elec5619.KAF.dao.ReportDao;
import au.usyd.elec5619.KAF.model.Report;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	ReportDao reportDao;

	@Override
	@Transactional
	public Report searchReportByReportID(Integer report_id) {

		return reportDao.searchReportByReportID(report_id);
	}

	@Override
	@Transactional
	public List<Report> searchReportByUserID(Integer user_id) {

		return reportDao.searchReportByUserID(user_id);
	}

	@Override
	@Transactional
	public List<Report> searchDailyReportByDate(Date date) {
		Instant instant = date.toInstant();
		Instant today = instant.truncatedTo(ChronoUnit.DAYS);
		System.out.println("Today's date:" + today);
		Instant nextDay = today.plus(1, ChronoUnit.DAYS);
		System.out.println("Next day's date:" + nextDay);
		Date todayDate = Date.from(today);
		Date nextDayDate = Date.from(nextDay);

		return reportDao.searchReportByDate(todayDate, nextDayDate);
	}

	@Override
	@Transactional
	public List<Report> getReportList() {
		return reportDao.ReportList();
	}

	@Override
	@Transactional
	public List<Report> getPendingReportList() {
		return reportDao.searchReportByStatus("P");
	}

	@Override
	@Transactional
	public boolean insertReport(Report report) {

		return reportDao.insertReport(report);
	}

	@Override
	@Transactional
	public boolean deleteReport(Integer report_id) {

		return reportDao.deleteReport(report_id);

	}

	@Override
	@Transactional
	public boolean editReportStatus(Report report) {

		if (reportDao.searchReportByReportID(report.getReport_id()) != null) {
			reportDao.editReportStatus(report);
			return true;
		} else {
			return false;
		}

	}

	@Override
	@Transactional
	public Integer countReport() {

		Integer conut = reportDao.ReportList().size();

		return conut;
	}
}
