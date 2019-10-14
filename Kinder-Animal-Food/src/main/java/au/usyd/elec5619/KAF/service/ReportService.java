package au.usyd.elec5619.KAF.service;

import java.util.Date;
import java.util.List;

import au.usyd.elec5619.KAF.model.Report;

public interface ReportService {

	Report searchReportByReportID(Integer report_id);
	
	List<Report> searchReportByUserID(Integer user_id);
	
	List<Report> searchDailyReportByDate(Date date);
	
	List<Report> getReportList();
	
	List<Report> getPendingReportList();
	
	boolean insertReport(Report report);
	
	boolean deleteReport(Integer report_id);
	
	boolean editReportStatus(Report report);
	
	Integer countReport();
}