package au.usyd.elec5619.KAF.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import au.usyd.elec5619.KAF.model.Brand;
import au.usyd.elec5619.KAF.model.Store;
import au.usyd.elec5619.KAF.service.BrandService;
import au.usyd.elec5619.KAF.service.StoreService;

@Controller
@RequestMapping("/systems")
public class SystemsController {

	@Autowired
	StoreService storeService;
	
	@Autowired
	BrandService brandService;

	@GetMapping("/")
	public String showSystems() {

		return "systems";
	}

	// Store delete insert update functions
	@GetMapping(value = "/storeSearch")
	public ModelAndView storeSearch(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("storeSearch");
		return mav;
	}

	@PostMapping(value = "/storeSearchProcess")
	public ModelAndView storeSearchProcess(HttpServletRequest request, HttpServletResponse response,
			String store_name) {

		List<Store> stores = null;

		if (store_name == "") {
			stores = storeService.storeList();
		} else {
			stores = storeService.searchStore(store_name);
		}

		ModelAndView mav = null;

		if (null != stores) {
			mav = new ModelAndView("continueStore");
			String Detail = "";
			for (int i = 0; i < stores.size(); i++) {
				Detail += stores.get(i).toString();
			}
			mav.addObject("Detail", Detail);
		} else {
			mav = new ModelAndView("storeSearch");
			mav.addObject("message", "There is no such store!!");
		}

		return mav;
	}

	@GetMapping(value = "/storeInsert")
	public ModelAndView storeInsert(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("storeInsert");
		mav.addObject("store", new Store());
		return mav;
	}

	@PostMapping(value = "/storeInsertProcess")
	public ModelAndView storeProcess(HttpServletRequest request, HttpServletResponse response, Store store) {

		ModelAndView mav = new ModelAndView("storeInsert");

		if (!storeService.insertStore(store)) {
			mav.addObject("message", "Store already exists!!");
		} else {
//			mav.addObject("message", "Insert successfully!!");
			mav.addObject("message", storeService.countStore());
		}

		return mav;
	}

	// Brand delete insert update functions
	@GetMapping(value = "/brandSearch")
	public ModelAndView brandSearch(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("brandSearch");
		return mav;
	}

	@PostMapping(value = "/brandSearchProcess")
	public ModelAndView brandSearchProcess(HttpServletRequest request, HttpServletResponse response,
			String brand_name) {

		List<Brand> brands = null;

		if (brand_name == "") {
			brands = brandService.brandList();
		} else {
			brands = brandService.searchBrand(brand_name);
		}

		ModelAndView mav = null;

		if (null != brands) {
			mav = new ModelAndView("continueBrand");
			String Detail = "";
			for (int i = 0; i < brands.size(); i++) {
				Detail += brands.get(i).toString();
			}
			mav.addObject("Detail", Detail);
		} else {
			mav = new ModelAndView("brandSearch");
			mav.addObject("message", "There is no such brand!!");
		}

		return mav;
	}

	@GetMapping(value = "/brandInsert")
	public ModelAndView brandInsert(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("brandInsert");
		mav.addObject("brand", new Brand());
		return mav;
	}

	@PostMapping(value = "/brandInsertProcess")
	public ModelAndView brandProcess(HttpServletRequest request, HttpServletResponse response, Brand brand) {

		ModelAndView mav = new ModelAndView("brandInsert");

		if (!brandService.insertBrand(brand)) {
			mav.addObject("message", "Brand already exists!!");
		} else {
//				mav.addObject("message", "Insert successfully!!");
			mav.addObject("message", brandService.countBrand());
		}

		return mav;
	}
}
