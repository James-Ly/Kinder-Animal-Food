package au.usyd.elec5619.KAF.dao;

import java.util.Date;
import java.util.List;

import au.usyd.elec5619.KAF.model.Report;


public interface ReportDao {
	
	Report searchReportByReportID(Integer report_id);
	
	List<Report> searchReportByUserID(Integer user_id);
	
	List<Report> searchReportByDate(Date startDate, Date endDate);
	
	List<Report> ReportList();
	
	List<Report> searchReportByStatus(String status);
	
	boolean insertReport(Report report);
	
	boolean deleteReport(Integer report_id);
	
	boolean editReportStatus(Report report);
	 
}
