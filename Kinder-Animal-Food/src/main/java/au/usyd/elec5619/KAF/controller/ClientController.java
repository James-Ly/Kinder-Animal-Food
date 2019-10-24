package au.usyd.elec5619.KAF.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import au.usyd.elec5619.KAF.model.Brand;
import au.usyd.elec5619.KAF.model.BrandAccreditation;
import au.usyd.elec5619.KAF.service.AccreditationService;
import au.usyd.elec5619.KAF.service.BrandAccreditationService;
import au.usyd.elec5619.KAF.service.BrandService;
import au.usyd.elec5619.KAF.service.ProductService;
import au.usyd.elec5619.KAF.service.StoreService;
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
		String category = request.getParameter("category");
		String rating = request.getParameter("rating");
		List<Brand> result = brandService.searchBrandByNameCategoryRating(brandName, category,rating);
		theModel.addAttribute("brand",result);
		return "client/browse";
	}

}
