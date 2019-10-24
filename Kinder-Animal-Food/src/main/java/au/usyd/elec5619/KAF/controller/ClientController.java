package au.usyd.elec5619.KAF.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import au.usyd.elec5619.KAF.service.AccreditationService;
import au.usyd.elec5619.KAF.service.BrandAccreditationService;
import au.usyd.elec5619.KAF.service.BrandService;
import au.usyd.elec5619.KAF.service.ProductService;
import au.usyd.elec5619.KAF.service.StoreService;
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
		
	@GetMapping("/locate")
	public String showLocate(Model theModel) {
		
		theModel.addAttribute("crmLocate",new CrmLocate());
		return "client/locate";
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
			System.out.println(i);
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
