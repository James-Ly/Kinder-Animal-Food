package au.usyd.elec5619.KAF.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import au.usyd.elec5619.KAF.model.Accreditation;
import au.usyd.elec5619.KAF.model.Brand;
import au.usyd.elec5619.KAF.model.BrandWithAccreditation;
import au.usyd.elec5619.KAF.model.Report;
import au.usyd.elec5619.KAF.model.Store;
import au.usyd.elec5619.KAF.service.AccreditationService;
import au.usyd.elec5619.KAF.service.BrandService;
import au.usyd.elec5619.KAF.service.ReportService;
import au.usyd.elec5619.KAF.service.StoreService;

@Controller
@RequestMapping("/systems")
public class SystemsController {

	@Autowired
	StoreService storeService;

	@Autowired
	BrandService brandService;

	@Autowired
	AccreditationService accreditationService;
	
	@Autowired
	ReportService reportService;
	
	@GetMapping("/")
	public String showSystems() {

		return "systems/systems";
	}

	// Accreditation Update Delete Functions
	@RequestMapping(value = "/UpdateDeleteAccreditation")
	public ModelAndView UpdateDeleteAccreditation(HttpServletRequest request, HttpServletResponse response) {
		List<Accreditation> accreditations = accreditationService.accreditationList();

		ModelAndView mav = null;

//		mav = new ModelAndView("#");
//		mav.addObject("store_name", stores.get(0).getStore_name());
//		mav.addObject("store_address", stores.get(0).getStore_address());
//		mav.addObject("brands_num", stores.get(0).getBrands_num());

		return mav;
	}

	@RequestMapping(value = "/UpdateDeleteAccreditationProcess")
	public ModelAndView UpdateDeleteAccreditationProcess(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("delete") List<Integer> delete) {

		ModelAndView mav = null;

		return mav;
	}

//  Store Update Delete Functions
	@RequestMapping(value = "/UpdateDeleteStore")
	public ModelAndView showStore(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		ModelAndView mav = new ModelAndView("systems/UpdateDeleteStore");

		List<Store> stores = storeService.storeList();
		map.put("stores", stores);

		return mav;
	}

	@RequestMapping(value = "/UpdateDeleteStoreProcess")
	public String updateDeleteStoreProcess(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("delete") List<Integer> delete) {

		for (int i = 0; i < delete.size(); i++) {
			storeService.deleteStore(delete.get(i));
		}

		return "redirect:UpdateDeleteStore";
	}

//  Brand Update Delete Functions 
	@RequestMapping(value = "/UpdateDeleteBrand")
	public ModelAndView showBrand(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		ModelAndView mav = new ModelAndView("systems/UpdateDeleteBrand");

		List<Brand> brands = brandService.brandList();
		List<BrandWithAccreditation> brandWithAccreditations = new ArrayList<BrandWithAccreditation>();
		for (int i = 0; i < brands.size(); i++) {
			BrandWithAccreditation brandWithAccreditation = new BrandWithAccreditation();
			Accreditation accreditation = accreditationService
					.searchAccreditationByID(brands.get(i).getAccreditation_id());

			brandWithAccreditation.setAccreditation_name(accreditation.getAccreditation_name());
			brandWithAccreditation.setRating(accreditation.getRating());
			brandWithAccreditation.setBrand(brands.get(i));

			brandWithAccreditations.add(brandWithAccreditation);
		}

		map.put("brands", brandWithAccreditations);

		return mav;
	}

	@RequestMapping(value = "/UpdateDeleteBrandProcess")
	public String updateDeleteBrandProcess(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("delete") List<Integer> delete) {

		for (int i = 0; i < delete.size(); i++) {
			brandService.deleteBrand(delete.get(i));
		}

		return "redirect:UpdateDeleteBrand";
	}

	@RequestMapping(value = "/CheckReport")
	public ModelAndView checkReport(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		ModelAndView mav = new ModelAndView("systems/CheckReport");
		
		List<Report> reports = reportService.getReportList();
		map.put("reports", reports);
		
		return mav;
	}
	
	/**
	 * Store and Brand Insert.
	 * 
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @param map      ModelMap
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/Insert")
	public ModelAndView storeInsert(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		ModelAndView mav = new ModelAndView("systems/Insert");

		List<Accreditation> accreditations = accreditationService.accreditationList();
		map.put("accreditations", accreditations);

		mav.addObject("store", new Store());
		mav.addObject("brand", new Brand());
		mav.addObject("accreditation", new Accreditation());

		return mav;
	}

	/**
	 * Insert new store if not exists.
	 * 
	 * @param request       HttpServletRequest
	 * @param response      HttpServletResponse
	 * @param map           ModelMap
	 * @param store         Store
	 * @param brand         Brand
	 * @param accreditation Accreditation
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/storeInsertProcess")
	public ModelAndView storeInsertProcess(HttpServletRequest request, HttpServletResponse response, ModelMap map,
			Store store, Brand brand, Accreditation accreditation) {
		ModelAndView mav = new ModelAndView("systems/Insert");

		List<Accreditation> accreditations = accreditationService.accreditationList();
		map.put("accreditations", accreditations);
		// return message
		String message = "";
		// state uppercase
		store.setStore_state(store.getStore_state().toUpperCase());
		if (store.getStore_state() == null) {
			return mav.addObject("message", "Null Input.");
		} else if (store.getStore_state().length() > 3) {
			return mav.addObject("message", "State incorrect.");
		}
		if (store.getStore_postcode() == null) {
			return mav.addObject("message", "Null Input.");
		} else if (store.getStore_postcode().length() > 4) {
			return mav.addObject("message", "Postcode incorrect.");
		}
		// get longitude and latitude by address
		String[] coordinates = addressGetCoordinate(store.getStore_address());
		// if get longitude and latitude failed
		if (coordinates[0] == null || coordinates[1] == null) {
			message += "Address error (get coordinates failed).";
		} else {
			// set latitude into store
			store.setStore_latitude(coordinates[0]);
			// set longitude into store
			store.setStore_longitude(coordinates[1]);
			if (storeService.insertStore(store)) {
				message += "Store insert success.";
			}
			// store duplicated
			else {
				message += "Store already exists.";
			}
		}
		mav.addObject("message", message);
		return mav;
	}

	/**
	 * Insert new accreditation if not exists and insert new brand.
	 * 
	 * @param request       HttpServletRequest
	 * @param response      HttpServletResponse
	 * @param map           ModelMap
	 * @param brand         Brand
	 * @param store         Store
	 * @param accreditation Accreditation
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/brandInsertProcess")
	public ModelAndView brandInsertProcess(HttpServletRequest request, HttpServletResponse response, ModelMap map,
			Brand brand, Store store, Accreditation accreditation) {
		ModelAndView mav = new ModelAndView("systems/Insert");
		List<Accreditation> accreditations = accreditationService.accreditationList();
		// return message
		String message = "";

		if (brandService.searchBrand(brand)) {
			message += "Brand already exists. ";
		} else {
			accreditations = accreditationService.accreditationList();

			// search exist accreditation
			Accreditation accreditationSearch = accreditationService.searchAccreditation(accreditation);
			int accreditation_id;
			// Accreditation not exists
			if (accreditationSearch == null) {
				// add new accreditation
				accreditationService.insertAccreditation(accreditation);
				//accreditation_id = accreditations.size() + 1;
				// accreditation id equals new accreditation's id
				accreditationSearch = accreditationService.searchAccreditation(accreditation);
				accreditation_id = accreditationSearch.getAccreditation_id();
				message += String.format("Accreditation added(id: %d). ", accreditation_id);
			}
			// Accreditation exists
			else {
				// accreditation id equals exist accreditation's id
				accreditation_id = accreditationSearch.getAccreditation_id();
				message += String.format("Accreditation exists(id: %d). ", accreditation_id);
			}
			// Add accreditation id into new brand
			brand.setAccreditation_id(accreditation_id);

			// 定义文件名
			// String fileName = "";
			// 获取原始文件名
			// String uploadFileName = image.getOriginalFilename();
			/*
			 * MultipartFile multipartFile = brand.getImage(); System.out.println(image);
			 * List<String> fileNames = new ArrayList<String>(); if (null != multipartFile)
			 * { String fileName = multipartFile.getOriginalFilename();
			 * fileNames.add(fileName);
			 * 
			 * File imageFile = new File(request.getServletContext().getRealPath("/image"),
			 * fileName); try { multipartFile.transferTo(imageFile); } catch (IOException e)
			 * { e.printStackTrace(); } }
			 */

			// Insert new brand
			if (brandService.insertBrand(brand)) {
				message += "Brand insert success.";
			}
		}
		accreditations = accreditationService.accreditationList();
		map.put("accreditations", accreditations);

		mav.addObject("message", message);
		return mav;
	}

	/**
	 * Input full address and return latitude and longitude.
	 * 
	 * @param address full address
	 * @return String[latitude,longitude]
	 */
	private String[] addressGetCoordinate(String address) {
		// API KEY
		String api_key = "56e3b46c-5fcc-4384-858b-40f54bce3976";
		System.out.println("Input store address: " + address);
		// RestTemplate
		RestTemplate restTemplate = new RestTemplate();
		// URL
		String url = "https://api.addressify.com.au/addresspro/info?api_key={api_key}&term={address}";
		// transmit parameters by Map
		Map<String, Object> params = new HashMap<>();
		params.put("api_key", api_key);
		params.put("address", address);
		// get API response in String
		String res = restTemplate.getForObject(url, String.class, params);
		System.out.println("API response: " + res);

		// Convert String to Json
		JsonObject convertedObject = new Gson().fromJson(res, JsonObject.class);

		String coordinates[] = new String[2];
		// Check Longitude and Latitude null or not
		if (convertedObject.get("Longitude").isJsonNull() || convertedObject.get("Latitude").isJsonNull()) {
			coordinates[0] = null;
			coordinates[1] = null;
		} else {
			coordinates[0] = convertedObject.get("Latitude").getAsString();
			coordinates[1] = convertedObject.get("Longitude").getAsString();
		}
		System.out.println("Latitude: " + coordinates[0]);
		System.out.println("Longitude: " + coordinates[1]);

		return coordinates;
	};
}

//	// Store delete insert update functions
//	@GetMapping(value = "/storeSearch")
//	public ModelAndView storeSearch(HttpServletRequest request, HttpServletResponse response) {
//		ModelAndView mav = new ModelAndView("storeSearch");
//		return mav;
//	}
//
//	@PostMapping(value = "/storeSearchProcess")
//	public ModelAndView storeSearchProcess(HttpServletRequest request, HttpServletResponse response,
//			String store_name) {
//
//		List<Store> stores = null;
//
//		if (store_name == "") {
//			stores = storeService.storeList();
//		} else {
//			stores = storeService.searchStore(store_name);
//		}
//
//		ModelAndView mav = null;
//
//		if (null != stores) {
//			mav = new ModelAndView("continueStore");
//			String Detail = "";
//			for (int i = 0; i < stores.size(); i++) {
//				Detail += stores.get(i).toString();
//			}
//			mav.addObject("Detail", Detail);
//		} else {
//			mav = new ModelAndView("storeSearch");
//			mav.addObject("message", "There is no such store!!");
//		}
//
//		return mav;
//	}
//
//	@GetMapping(value = "/storeInsert")
//	public ModelAndView storeInsert(HttpServletRequest request, HttpServletResponse response) {
//		ModelAndView mav = new ModelAndView("storeInsert");
//		mav.addObject("store", new Store());
//		return mav;
//	}
//
//	@PostMapping(value = "/storeInsertProcess")
//	public ModelAndView storeProcess(HttpServletRequest request, HttpServletResponse response, Store store) {
//
//		ModelAndView mav = new ModelAndView("storeInsert");
//
//		if (!storeService.insertStore(store)) {
//			mav.addObject("message", "Store already exists!!");
//		} else {
////			mav.addObject("message", "Insert successfully!!");
//			mav.addObject("message", storeService.countStore());
//		}
//
//		return mav;
//	}
//
//	// Brand delete insert update functions
//	@GetMapping(value = "/brandSearch")
//	public ModelAndView brandSearch(HttpServletRequest request, HttpServletResponse response) {
//		ModelAndView mav = new ModelAndView("brandSearch");
//		return mav;
//	}
//
//	@PostMapping(value = "/brandSearchProcess")
//	public ModelAndView brandSearchProcess(HttpServletRequest request, HttpServletResponse response,
//			String brand_name) {
//
//		List<Brand> brands = null;
//
//		if (brand_name == "") {
//			brands = brandService.brandList();
//		} else {
//			brands = brandService.searchBrand(brand_name);
//		}
//
//		ModelAndView mav = null;
//
//		if (null != brands) {
//			mav = new ModelAndView("continueBrand");
//			String Detail = "";
//			for (int i = 0; i < brands.size(); i++) {
//				Detail += brands.get(i).toString();
//			}
//			mav.addObject("Detail", Detail);
//		} else {
//			mav = new ModelAndView("brandSearch");
//			mav.addObject("message", "There is no such brand!!");
//		}
//
//		return mav;
//	}
//
//	@GetMapping(value = "/brandInsert")
//	public ModelAndView brandInsert(HttpServletRequest request, HttpServletResponse response) {
//		ModelAndView mav = new ModelAndView("brandInsert");
//		mav.addObject("brand", new Brand());
//		return mav;
//	}
//
//	@PostMapping(value = "/brandInsertProcess")
//	public ModelAndView brandProcess(HttpServletRequest request, HttpServletResponse response, Brand brand) {
//
//		ModelAndView mav = new ModelAndView("brandInsert");
//
//		if (!brandService.insertBrand(brand)) {
//			mav.addObject("message", "Brand already exists!!");
//		} else {
////				mav.addObject("message", "Insert successfully!!");
//			mav.addObject("message", brandService.countBrand());
//		}
//
//		return mav;
//	}
//}
