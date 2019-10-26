package au.usyd.elec5619.KAF.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import au.usyd.elec5619.KAF.model.Accreditation;
import au.usyd.elec5619.KAF.model.Brand;
import au.usyd.elec5619.KAF.model.Report;
import au.usyd.elec5619.KAF.model.User;
import au.usyd.elec5619.KAF.service.AccreditationService;
import au.usyd.elec5619.KAF.service.BrandAccreditationService;
import au.usyd.elec5619.KAF.service.BrandService;
import au.usyd.elec5619.KAF.service.ProductService;
import au.usyd.elec5619.KAF.service.ReportService;
import au.usyd.elec5619.KAF.service.StoreService;
import au.usyd.elec5619.KAF.service.UserService;
import au.usyd.elec5619.KAF.user.CrmBrandWithRating;
import au.usyd.elec5619.KAF.user.CrmLocate;


@Controller
@RequestMapping("/client")
public class ClientController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	BrandService brandService;
	
	@Autowired
	StoreService storeService;
	
	@Autowired
	BrandAccreditationService brandAccreditationService;
	
	@Autowired
	AccreditationService accreditationService;
	
	@Autowired
	ReportService reportService;
	
	@Autowired
	UserService userService;
		
	@GetMapping("/locate")
	public String showLocate(Model theModel) {
		
		theModel.addAttribute("crmLocate",new CrmLocate());
		return "client/locate";
	}
	
	@GetMapping("reportastore")
	public String handleStoreReport(HttpServletRequest request) {
		Report newReport = new Report();
		newReport.setReport_time(new Date());
		newReport.setStore_address_by_user(request.getParameter("storeAddress"));
		newReport.setStore_name_by_user(request.getParameter("storeName"));
		newReport.setStore_postcode_by_user(request.getParameter("storePostCode"));
		newReport.setStore_state_by_user(request.getParameter("storeState"));
		Brand reportedBrand = brandService.searchExactBrand(request.getParameter("brandName"));
		User reportUser = userService.findByUserName(request.getParameter("username"));
		int userid = Math.toIntExact(reportUser.getId());
		newReport.setBrand_id(reportedBrand.getBrand_id());
		newReport.setUser_id(userid);
		newReport.setStatus("P");
		
		reportService.insertReport(newReport);
		return "client/reportconfirmation";
	}
	
	@GetMapping("/brandDetail")
	public String showBrandDetail(HttpServletRequest request,Model theModel) {
		int brandId = Integer.parseInt(request.getParameter("brandId"));
		Brand theBrand = brandService.searchBrand(brandId);
		List<Integer> accIdList = brandAccreditationService.searchAccreditationByBrand(theBrand.getBrand_id());
		List<Accreditation> accList = accreditationService.searchAccreditationByIds(accIdList);
		List<Accreditation> accBest = new ArrayList<Accreditation>();
		List<Accreditation> accGood = new ArrayList<Accreditation>();
		List<Accreditation> accAvoid = new ArrayList<Accreditation>();
		for(int i = 0 ; i < accList.size(); i ++) {
			if(accList.get(i).getRating().equalsIgnoreCase("Best")) {
				accBest.add(accList.get(i));
			} else if (accList.get(i).getRating().equalsIgnoreCase("Good")) {
				accGood.add(accList.get(i));
			} else if (accList.get(i).getRating().equalsIgnoreCase("Avoid")) {
				accAvoid.add(accList.get(i));
			}
		}
		List<Integer> number = new ArrayList<Integer>();
		number.add(accBest.size());
		number.add(accGood.size());
		number.add(accAvoid.size());
		theModel.addAttribute("brand",theBrand);
		theModel.addAttribute("accBest",accBest);
		theModel.addAttribute("accGood",accGood);
		theModel.addAttribute("accAvoid",accAvoid);
		theModel.addAttribute("number",number);
		return "client/brandDetail";
	}
	
	@GetMapping("/report")
	public String showReport(HttpServletRequest request,Model theModel) {
		String brandName = request.getParameter("brand");
		theModel.addAttribute("brandName",brandName);
		return "client/report";
	}
	
	@GetMapping("/browse")
	public String showBrowse(HttpServletRequest request,Model theModel) {
		String brandName = request.getParameter("brandName");
		System.out.println("brandName "+brandName);
		String category = request.getParameter("category");
		System.out.println("Category "+category);
		String rating = request.getParameter("rating");
		System.out.println("Rating "+rating);
		theModel.addAttribute("queryBrandName",brandName);
		theModel.addAttribute("Categoryquery",category);
		theModel.addAttribute("queryRating",rating);
		System.out.println("====================================");
		int page = 1;
		try {
			page = Integer.parseInt(request.getParameter("page"));
		} catch(Exception e) {
			page = 1;
		}
		System.out.println(page);
		int numberInPage = 12;
		if(rating!=null && rating.equalsIgnoreCase("All")) {
			rating = null;
		} else if (rating != null &&rating.equalsIgnoreCase("")) {
			rating = null;
		}
		if(category!=null && category.equalsIgnoreCase("All")) {
			category = null;
		} else if (category != null && category.equalsIgnoreCase("")) {
			category = null;
		}
		List<CrmBrandWithRating> result = brandService.searchBrandByNameCategoryRating(brandName, category,rating);
		List<CrmBrandWithRating> brandResult = new ArrayList<CrmBrandWithRating>();
		for(int i = ((page - 1)*numberInPage)  ; i < result.size() && i < (page * numberInPage)  ; i ++) {
			brandResult.add(result.get(i));
		}
		int numberOfPage = roundUp(result.size(),numberInPage);
		List<String> categoryList = brandService.searchDistinctCategory();
		theModel.addAttribute("brand",brandResult);
		theModel.addAttribute("categoryList",categoryList);
		theModel.addAttribute("numberOfPage",numberOfPage);
		theModel.addAttribute("currentPage",page);
		return "client/browse";
	}
	
	private static int roundUp(int dividend, int divisor) {
        return (dividend + divisor - 1) / divisor;
    }

}
