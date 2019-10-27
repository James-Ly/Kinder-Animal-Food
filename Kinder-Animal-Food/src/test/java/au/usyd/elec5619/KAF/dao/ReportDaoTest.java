package au.usyd.elec5619.KAF.dao;

import java.util.Date;
import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import au.usyd.elec5619.KAF.config.DemoAppConfig;
import au.usyd.elec5619.KAF.model.Report;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class, classes = { DemoAppConfig.class })
@Transactional
@WebAppConfiguration
@Repository
public class ReportDaoTest extends TestCase {

	@Autowired
	private ReportDao reportDao;

	@Before
	public void setUp() throws Exception {
		Report report = new Report();
		report.setBrand_id(2);
		report.setUser_id(1);
		report.setReport_id(1);
		report.setReport_time(new Date());
		report.setStatus("P");
		report.setStore_name_by_user("testingStoreName");
		report.setStore_address_by_user("testingStoreAddress");
		report.setStore_postcode_by_user("1000");
		report.setStore_state_by_user("NSW");
		reportDao.insertReport(report);
	}

	@Test
	public void testGetReports() {
		assertNotNull(reportDao.ReportList());
	}

	@Test
	public void testEditReportsStatus() {
		Report report = new Report();
		report.setReport_id(1);
		report.setStatus("D");
		assertTrue(reportDao.editReportStatus(report));
	}

	@Test
	public void testSearchReportByStatus() {
		assertNotNull(reportDao.searchReportByStatus("P"));
	}
	
	@Test
	public void testInsertReport() {
		Report report = new Report();
		report.setBrand_id(2);
		report.setUser_id(1);
		report.setReport_id(2);
		report.setReport_time(new Date());
		report.setStatus("P");
		report.setStore_name_by_user("testingStoreName");
		report.setStore_address_by_user("testingStoreAddress");
		report.setStore_postcode_by_user("1000");
		report.setStore_state_by_user("NSW");
		reportDao.insertReport(report);
		assertTrue(reportDao.insertReport(report));
	}
	
	@Test
	public void testSearchReportByReportID() {
		assertNotNull(reportDao.searchReportByReportID(1));
	}
	
	@Test
	public void testSearchReportByUserID() {
		assertNotNull(reportDao.searchReportByUserID(1));
	}
	
	@Test
	public void testSearchReportByDate() {
		assertNotNull(reportDao.searchReportByDate(new Date(), new Date()));
	}
	
	@Test
	public void testDeleteReport() {
		assertTrue(reportDao.deleteReport(1));
	}
	
	@Test
	public void testSearchReportNotExist() {
		assertTrue(reportDao.deleteReport(1));
		assertNull(reportDao.searchReportByReportID(1));
	}
}
