package au.usyd.elec5619.KAF.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import au.usyd.elec5619.domain.Brand;
import au.usyd.elec5619.service.BrandService;

@Controller
public class BrandController {

	@Autowired
	BrandService brandService;

	// Search brand by name and show the brand list.
	@RequestMapping(value = "/brandSearch", method = RequestMethod.GET)
	public ModelAndView brandSearch(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("brandSearch");
		return mav;
	}

	@RequestMapping(value = "/brandSearchProcess", method = RequestMethod.POST)
	public ModelAndView brandSearchProcess(HttpServletRequest request, HttpServletResponse response,
			String brand_name) {

		ModelAndView mav = null;
		
		List<Brand> brands = brandService.validateBrand(brand_name);

		if (null != brands) {
			mav = new ModelAndView("continueBrand");
			String Detail = "";
			for(int i = 0; i < brands.size();i++) {
				Detail += brands.get(i).toString();
			}
			mav.addObject("Detail", Detail);
		} else {
			mav = new ModelAndView("brandSearch");
			mav.addObject("message", "There is no such brand!!");
		}

		return mav;
	}

	@RequestMapping(value = "/brandInsert", method = RequestMethod.GET)
	public ModelAndView brandInsert(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("brandInsert");
		mav.addObject("brand", new Brand());
		return mav;
	}

	@RequestMapping(value = "/brandInsertProcess", method = RequestMethod.POST)
	public ModelAndView brandProcess(HttpServletRequest request, HttpServletResponse response, Brand brand) {

		ModelAndView mav = new ModelAndView("brandInsert");
		
		if ( !brandService.insertBrand(brand)) {
			mav.addObject("message", "Brand already exists!!");
		} else {
//			mav.addObject("message", "Insert successfully!!");
			mav.addObject("message",  brandService.countBrand());
		}

		return mav;
	}

//	@RequestMapping(value = "/brandInsert", method = RequestMethod.GET)
//	public ModelAndView brandInsert1(HttpServletRequest request, HttpServletResponse response) {
//		ModelAndView mav = new ModelAndView("brandInsert");
//		mav.addObject("brand", new Brand());
//		return mav;
//	}
//
//	@RequestMapping(value = "/brandInsertProcess", method = RequestMethod.POST)
//	public ModelAndView brandProcess1(HttpServletRequest request, HttpServletResponse response, Brand brand) {
//
//		ModelAndView mav = new ModelAndView("brandInsert");
//		
//		if (brandService.insertBrand(brand) == false) {
//			mav.addObject("message", "Brand already exists!!");
//		} else {
////			mav.addObject("message", "Insert successfully!!");
//			mav.addObject("message", brandService);
//		}
//
//		return mav;
//	}
//	
//	@RequestMapping(value = "/brandInsert", method = RequestMethod.GET)
//	public ModelAndView brandInsert2(HttpServletRequest request, HttpServletResponse response) {
//		ModelAndView mav = new ModelAndView("brandInsert");
//		mav.addObject("brand", new Brand());
//		return mav;
//	}
//
//	@RequestMapping(value = "/brandInsertProcess", method = RequestMethod.POST)
//	public ModelAndView brandProcess2(HttpServletRequest request, HttpServletResponse response, Brand brand) {
//
//		ModelAndView mav = new ModelAndView("brandInsert");
//		
//		if (brandService.insertBrand(brand) == false) {
//			mav.addObject("message", "Brand already exists!!");
//		} else {
//			mav.addObject("message", "Insert successfully!!");
//		}
//
//		return mav;
//	}
}
