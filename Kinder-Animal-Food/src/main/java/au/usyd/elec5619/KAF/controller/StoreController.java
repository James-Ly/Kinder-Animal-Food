package au.usyd.elec5619.KAF.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import au.usyd.elec5619.domain.Store;
import au.usyd.elec5619.service.StoreService;

@Controller
public class StoreController {

	@Autowired
	StoreService storeService;

	// Search store by name and show the store list.
	@RequestMapping(value = "/storeSearch", method = RequestMethod.GET)
	public ModelAndView storeSearch(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("storeSearch");
		return mav;
	}

	@RequestMapping(value = "/storeSearchProcess", method = RequestMethod.POST)
	public ModelAndView storeSearchProcess(HttpServletRequest request, HttpServletResponse response,
			String store_name) {

		List<Store> stores = null;
		
		if (store_name == "") {
			stores = storeService.storeList();
		} else {
			stores = storeService.validateStore(store_name);
		}
		
		ModelAndView mav = null;
		
		if (null != stores) {
			mav = new ModelAndView("continueStore");
			String Detail = "";
			for(int i = 0; i < stores.size();i++) {
				Detail += stores.get(i).toString();
			}
			mav.addObject("Detail", Detail);
		} else {
			mav = new ModelAndView("storeSearch");
			mav.addObject("message", "There is no such store!!");
		}

		return mav;
	}

	@RequestMapping(value = "/storeInsert", method = RequestMethod.GET)
	public ModelAndView storeInsert(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("storeInsert");
		mav.addObject("store", new Store());
		return mav;
	}

	@RequestMapping(value = "/storeInsertProcess", method = RequestMethod.POST)
	public ModelAndView storeProcess(HttpServletRequest request, HttpServletResponse response, Store store) {

		ModelAndView mav = new ModelAndView("storeInsert");
		
		if ( !storeService.insertStore(store)) {
			mav.addObject("message", "Store already exists!!");
		} else {
//			mav.addObject("message", "Insert successfully!!");
			mav.addObject("message",  storeService.countStore());
		}

		return mav;
	}

//	@RequestMapping(value = "/storeInsert", method = RequestMethod.GET)
//	public ModelAndView storeInsert1(HttpServletRequest request, HttpServletResponse response) {
//		ModelAndView mav = new ModelAndView("storeInsert");
//		mav.addObject("store", new Store());
//		return mav;
//	}
//
//	@RequestMapping(value = "/storeInsertProcess", method = RequestMethod.POST)
//	public ModelAndView storeProcess1(HttpServletRequest request, HttpServletResponse response, Store store) {
//
//		ModelAndView mav = new ModelAndView("storeInsert");
//		
//		if (storeService.insertStore(store) == false) {
//			mav.addObject("message", "Store already exists!!");
//		} else {
////			mav.addObject("message", "Insert successfully!!");
//			mav.addObject("message", storeService);
//		}
//
//		return mav;
//	}
//	
//	@RequestMapping(value = "/storeInsert", method = RequestMethod.GET)
//	public ModelAndView storeInsert2(HttpServletRequest request, HttpServletResponse response) {
//		ModelAndView mav = new ModelAndView("storeInsert");
//		mav.addObject("store", new Store());
//		return mav;
//	}
//
//	@RequestMapping(value = "/storeInsertProcess", method = RequestMethod.POST)
//	public ModelAndView storeProcess2(HttpServletRequest request, HttpServletResponse response, Store store) {
//
//		ModelAndView mav = new ModelAndView("storeInsert");
//		
//		if (storeService.insertStore(store) == false) {
//			mav.addObject("message", "Store already exists!!");
//		} else {
//			mav.addObject("message", "Insert successfully!!");
//		}
//
//		return mav;
//	}
}
