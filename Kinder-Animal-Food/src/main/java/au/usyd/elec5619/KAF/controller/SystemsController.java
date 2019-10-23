package au.usyd.elec5619.KAF.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import au.usyd.elec5619.KAF.model.Accreditation;
import au.usyd.elec5619.KAF.model.Brand;
import au.usyd.elec5619.KAF.model.BrandAccreditation;
import au.usyd.elec5619.KAF.model.Product;
import au.usyd.elec5619.KAF.model.Report;
import au.usyd.elec5619.KAF.model.Store;
import au.usyd.elec5619.KAF.service.AccreditationService;
import au.usyd.elec5619.KAF.service.BrandAccreditationService;
import au.usyd.elec5619.KAF.service.BrandService;
import au.usyd.elec5619.KAF.service.ProductService;
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
	ProductService productService;

	@Autowired
	BrandAccreditationService brandAccreditationService;

	@Autowired
	ReportService reportService;

	@GetMapping("/")
	public String showSystems() {

		return "systems/systems";
	}

//
//	Accreditation Update Delete Functions
//	
//  
//	
//	
	@RequestMapping(value = "/UpdateDeleteAccreditation")
	public ModelAndView UpdateDeleteAccreditation(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "accreditationName", defaultValue = "") String name) {

		ModelAndView mav = new ModelAndView("systems/UpdateDeleteAccreditation");

		List<Accreditation> accreditations = accreditationService.accreditationList();

		if (!name.equals("")) {
			List<Accreditation> results = new ArrayList<Accreditation>();
			for (int i = 0; i < accreditations.size(); i++) {
				if (accreditations.get(i).getAccreditation_name().toUpperCase().contains(name.toUpperCase())) {
					results.add(accreditations.get(i));
				}
			}
			accreditations.clear();
			accreditations.addAll(results);
		}

		mav.addObject("accreditations", accreditations);

		return mav;
	}

	@RequestMapping(value = "/UpdateDeleteAccreditationProcess")
	public String UpdateDeleteAccreditationProcess(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "delete", defaultValue = "") List<Integer> delete) {
		if (!delete.equals("")) {
			for (int i = 0; i < delete.size(); i++) {
				accreditationService.deleteAccreditation(delete.get(i));
			}
		}
		return "redirect:UpdateDeleteAccreditation";
	}

	@RequestMapping(value = "/AccreditationAdd")
	public ModelAndView accreditationAdd(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mav = new ModelAndView("systems/AccreditationAdd");

		mav.addObject("nAccreditation", new Accreditation());

		return mav;
	}

	@RequestMapping(value = "/AccreditationAddProcess")
	public ModelAndView accreditationAddProcess(HttpServletRequest request, HttpServletResponse response,
			Accreditation accreditation) {

		ModelAndView mav = new ModelAndView("systems/AccreditationAdd");

		String message = "";
		if (accreditationService.insertAccreditation(accreditation)) {
			message += "Accreditation add success.";
		} else {
			message += "Add cannot be done";
		}

		mav.addObject("nAccreditation", new Accreditation());
		mav.addObject("status", message);

		return mav;
	}

	@RequestMapping(value = "/AccreditationEdit/{id}")
	public ModelAndView accreditationEdit(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") Integer accreditation_id) {

		ModelAndView mav = new ModelAndView("systems/AccreditationEdit");

		mav.addObject("accreditation", accreditationService.searchAccreditation(accreditation_id));
		mav.addObject("nAccreditation", new Accreditation());

		return mav;
	}

	@RequestMapping(value = "/AccreditationEditProcess/{id}")
	public ModelAndView accreditationEditProcess(HttpServletRequest request, HttpServletResponse response,
			Accreditation accreditation, @PathVariable("id") Integer accreditation_id) {

		ModelAndView mav = new ModelAndView("systems/AccreditationEdit");

		accreditation.setAccreditation_id(accreditation_id);

		String message = "";
		if (accreditationService.editAccreditation(accreditation)) {
			message += "Accreditation edit success.";
		} else {
			message += "Edit cannot be done";
		}

		mav.addObject("accreditation", accreditation);
		mav.addObject("nAccreditation", new Accreditation());
		mav.addObject("status", message);

		return mav;
	}

//
//  Store functions
//	
//  
//	Store delete functoin
//
	@RequestMapping(value = "/UpdateDeleteStore")
	public ModelAndView showStore(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "storeName", defaultValue = "") String name) {

		ModelAndView mav = new ModelAndView("systems/UpdateDeleteStore");

		List<Store> stores = storeService.storeList();

		if (!name.equals("")) {
			List<Store> results = new ArrayList<Store>();
			for (int i = 0; i < stores.size(); i++) {
				if (stores.get(i).getStore_name().toUpperCase().contains(name.toUpperCase())) {
					results.add(stores.get(i));
				}
			}
			stores.clear();
			stores.addAll(results);
		}

		mav.addObject("stores", stores);

		return mav;
	}

	@RequestMapping(value = "/UpdateDeleteStoreProcess")
	public String updateDeleteStoreProcess(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "delete", defaultValue = "") List<Integer> delete) {
		if (!delete.equals("")) {
			for (int i = 0; i < delete.size(); i++) {
				storeService.deleteStore(delete.get(i));
			}
		}
		return "redirect:UpdateDeleteStore";
	}

//  Store detail
	@RequestMapping("/StoreDetails/{id}")
	public ModelAndView showStoreDetail(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") Integer store_id) {
		ModelAndView mav = new ModelAndView("systems/StoreDetails");

		mav.addObject("store", storeService.searchStore(store_id));

		return mav;
	}

//  edit store details
	@RequestMapping(value = "/StoreDetails/StoreEdit/{id}")
	public ModelAndView storeEdit(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") Integer store_id) {
		ModelAndView mav = new ModelAndView("systems/StoreEdit");

		mav.addObject("store", storeService.searchStore(store_id));
		mav.addObject("nStore", new Store());

		return mav;
	}

	@RequestMapping(value = "/StoreDetails/StoreEdit/storeEditProcess/{id}")
	public ModelAndView storeEditProcess(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") Integer store_id, Store store) {

		ModelAndView mav = new ModelAndView("systems/StoreEdit");

		store.setStore_id(store_id);

		String message = "";
		// state uppercase
		store.setStore_state(store.getStore_state().toUpperCase());
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
			if (storeService.editStore(store)) {
				message += "Store edit success.";
			}
			// store duplicated
			else {
				message += "Edit cannot be done";
			}
		}

		mav.addObject("status", message);
		mav.addObject("store", store);
		mav.addObject("nStore", new Store());

		return mav;
	}

//  Show store's brands
	@RequestMapping("/StoreBrand/{id}")
	public ModelAndView showStoreBrand(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") Integer store_id, @RequestParam(value = "brandName", defaultValue = "") String name) {

		ModelAndView mav = new ModelAndView("systems/StoreBrand");

		List<Brand> brands = new ArrayList<Brand>();
		List<Integer> brand_ids = productService.searchBrandByStore(store_id);

		for (int i = 0; i < brand_ids.size(); i++) {
			brands.add(brandService.searchBrand(brand_ids.get(i)));
		}

		if (!name.equals("")) {
			List<Brand> results = new ArrayList<Brand>();
			for (int i = 0; i < brands.size(); i++) {
				if (brands.get(i).getBrand_name().toUpperCase().contains(name.toUpperCase())) {
					results.add(brands.get(i));
				}
			}
			brands.clear();
			brands.addAll(results);
		}

		mav.addObject("brands", brands);
		mav.addObject("store", storeService.searchStore(store_id));

		return mav;
	}

//  Delete store's brands
	@RequestMapping("/StoreBrand/StoreBrandDelete/{id}")
	public String StoreBrandDelete(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "delete", defaultValue = "") List<Integer> delete,
			@PathVariable("id") Integer store_id) {

		if (!delete.equals("")) {
			for (int i = 0; i < delete.size(); i++) {
				Product product = productService.searchProduct(store_id, delete.get(i));
				productService.deleteProduct(product.getProduct_id());
			}
		}

		// update store's brand number
		Integer brands_num = productService.searchBrandByStore(store_id).size();
		storeService.setBrandsNum(store_id, brands_num);

		return "redirect:../" + store_id.toString();
	}

//  Add store's brands
	@RequestMapping("/StoreBrand/StoreBrandAdd/{id}")
	public ModelAndView StoreBrandAdd(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") Integer store_id, @RequestParam(value = "brandName", defaultValue = "") String name) {
		ModelAndView mav = new ModelAndView("systems/StoreBrandAdd");

		List<Integer> brand_ids = productService.searchBrandByStore(store_id);
		List<ArrayList<Accreditation>> accreditations = new ArrayList<ArrayList<Accreditation>>();
		List<Brand> brands_ex = new ArrayList<Brand>();
		List<Brand> brands = brandService.brandList();

		for (int i = 0; i < brands.size(); i++) {
			if (!brand_ids.contains(brands.get(i).getBrand_id())) {
				// add brands which are not contained in brands to brand_ex
				brands_ex.add(brandService.searchBrand(brands.get(i).getBrand_id()));
			}
		}

		if (!name.equals("")) {
			List<Brand> results = new ArrayList<Brand>();
			for (int i = 0; i < brands_ex.size(); i++) {
				if (brands_ex.get(i).getBrand_name().toUpperCase().contains(name.toUpperCase())) {
					results.add(brands_ex.get(i));
				}
			}
			brands_ex.clear();
			brands_ex.addAll(results);
		}

		// create a list of accreditation
		for (int i = 0; i < brands_ex.size(); i++) {
			List<Integer> accreditation_ids = brandAccreditationService
					.searchAccreditationByBrand(brands.get(i).getBrand_id());

			ArrayList<Accreditation> rows = new ArrayList<Accreditation>();
			for (int j = 0; j < accreditation_ids.size(); j++) {
				rows.add(accreditationService.searchAccreditation(accreditation_ids.get(j)));
			}
			accreditations.add(rows);
		}

		mav.addObject("store", storeService.searchStore(store_id));
		mav.addObject("brands", brands_ex);
		mav.addObject("accreditations", accreditations);

		return mav;
	}

	@RequestMapping(value = "/StoreBrand/StoreBrandAdd/StoreBrandAddProcess/{id}")
	public String StoreBrandAddProcess(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") Integer store_id,
			@RequestParam(value = "select", defaultValue = "") List<Integer> select) {

		if (!select.equals("")) {
			for (int i = 0; i < select.size(); i++) {
				Product product = new Product();
				product.setBrand_id(select.get(i));
				product.setStore_id(store_id);
				productService.insertProduct(product);
			}
		}

		Integer brands_num = productService.searchBrandByStore(store_id).size();
		storeService.setBrandsNum(store_id, brands_num);

		return "redirect:../" + store_id.toString();
	}

//  Brand Update Delete Functions 
	@RequestMapping(value = "/UpdateDeleteBrand")
	public ModelAndView showBrand(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "brandName", defaultValue = "") String name) {

		ModelAndView mav = new ModelAndView("systems/UpdateDeleteBrand");

		List<Brand> brands = brandService.brandList();

		if (!name.equals("")) {
			List<Brand> results = new ArrayList<Brand>();
			for (int i = 0; i < brands.size(); i++) {
				if (brands.get(i).getBrand_name().toUpperCase().contains(name.toUpperCase())) {
					results.add(brands.get(i));
				}
			}
			brands.clear();
			brands.addAll(results);
		}

		List<ArrayList<Accreditation>> accreditations = new ArrayList<ArrayList<Accreditation>>();

		for (int i = 0; i < brands.size(); i++) {
			// create a list of accreditation
			List<Integer> accreditation_ids = brandAccreditationService
					.searchAccreditationByBrand(brands.get(i).getBrand_id());
			ArrayList<Accreditation> rows = new ArrayList<Accreditation>();
			for (int j = 0; j < accreditation_ids.size(); j++) {

				rows.add(accreditationService.searchAccreditation(accreditation_ids.get(j)));

			}
			accreditations.add(rows);
		}

		mav.addObject("brands", brands);
		mav.addObject("accreditations", accreditations);

		return mav;
	}

	@RequestMapping(value = "/UpdateDeleteBrandProcess")
	public String updateDeleteBrandProcess(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "delete", defaultValue = "") List<Integer> delete) {

		if (!delete.equals("")) {
			for (int i = 0; i < delete.size(); i++) {
				brandService.deleteBrand(delete.get(i));
			}

			List<Store> stores = storeService.storeList();
			for (int i = 0; i < stores.size(); i++) {
				Integer brands_num = productService.searchBrandByStore(stores.get(i).getStore_id()).size();
				storeService.setBrandsNum(stores.get(i).getStore_id(), brands_num);
			}
		}
		return "redirect:UpdateDeleteBrand";
	}

//  Brands detail
	@RequestMapping("/BrandDetails/{id}")
	public ModelAndView showBrandDetails(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") Integer brand_id) {
		ModelAndView mav = new ModelAndView("systems/BrandDetails");

		Brand brand = brandService.searchBrand(brand_id);
		List<Accreditation> accreditations = new ArrayList<Accreditation>();

		List<Integer> accreditation_ids = brandAccreditationService.searchAccreditationByBrand(brand.getBrand_id());

		for (int i = 0; i < accreditation_ids.size(); i++) {
			accreditations.add(accreditationService.searchAccreditation(accreditation_ids.get(i)));
		}

		mav.addObject("brand", brand);
		mav.addObject("accreditations", accreditations);

		return mav;
	}

	@RequestMapping("/BrandEdit/{id}")
	public ModelAndView editBrand(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") Integer brand_id) {

		ModelAndView mav = new ModelAndView("systems/BrandEdit");

		mav.addObject("brand", brandService.searchBrand(brand_id));
		mav.addObject("nBrand", new Brand());

		return mav;
	}

	@RequestMapping("/BrandEditProcess/{id}")
	public ModelAndView editBrandProcess(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") Integer brand_id, Brand brand) {

		ModelAndView mav = new ModelAndView("systems/BrandEdit");
		String message = "";
		brand.setBrand_id(brand_id);
		if(brand.getImage().length()<1)
		{
			System.out.println(123);
			brand.setImage(brandService.searchBrand(brand_id).getImage());
		}

		if (brandService.editBrand(brand)) {
			message += "Brand edit success.";
		} else {
			message += "Edit cannot be done";
		}

		mav.addObject("status", message);
		mav.addObject("brand", brand);

		mav.addObject("nBrand", new Brand());

//		return "redirect:../BrandEdit/" + brand_id.toString();
		return mav;
	}

	@RequestMapping("/BrandAccreditation/{id}")
	public ModelAndView editBrandAccreditation(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") Integer brand_id,
			@RequestParam(value = "accreditationNameEx", defaultValue = "") String name_ex,
			@RequestParam(value = "accreditationNameIn", defaultValue = "") String name_in) {
		ModelAndView mav = new ModelAndView("systems/BrandAccreditation");

		Brand brand = brandService.searchBrand(brand_id);

		List<Accreditation> accreditations = new ArrayList<Accreditation>();
		List<Accreditation> allAccreditations = accreditationService.accreditationList();
		List<Accreditation> accreditations_ex = new ArrayList<Accreditation>();

		List<Integer> accreditation_ids = brandAccreditationService.searchAccreditationByBrand(brand_id);
		List<Integer> allAccreditation_ids = new ArrayList<Integer>();

		for (int i = 0; i < allAccreditations.size(); i++) {
			allAccreditation_ids.add(allAccreditations.get(i).getAccreditation_id());
		}

		for (int i = 0; i < allAccreditation_ids.size(); i++) {
			if (!accreditation_ids.contains(allAccreditation_ids.get(i))) {
				accreditations_ex.add(accreditationService.searchAccreditation(allAccreditation_ids.get(i)));
			} else {
				accreditations.add(accreditationService.searchAccreditation(allAccreditation_ids.get(i)));
			}
		}

		if (!name_ex.equals("")) {
			List<Accreditation> results = new ArrayList<Accreditation>();
			for (int i = 0; i < accreditations_ex.size(); i++) {
				if (accreditations_ex.get(i).getAccreditation_name().toUpperCase().contains(name_ex.toUpperCase())) {
					results.add(accreditations_ex.get(i));
				}
			}
			accreditations_ex.clear();
			accreditations_ex.addAll(results);
		}

		if (!name_in.equals("")) {
			List<Accreditation> results = new ArrayList<Accreditation>();
			for (int i = 0; i < accreditations.size(); i++) {
				if (accreditations.get(i).getAccreditation_name().toUpperCase().contains(name_in.toUpperCase())) {
					results.add(accreditations.get(i));
				}
			}
			accreditations.clear();
			accreditations.addAll(results);
		}

		mav.addObject("brand", brand);
		mav.addObject("accreditations_in", accreditations);
		mav.addObject("accreditations_ex", accreditations_ex);

		return mav;
	}

	@RequestMapping("/BrandDeleteAccreditation/{id}")
	public String brandDeleteAccreditation(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") Integer brand_id,
			@RequestParam(value = "delete", defaultValue = "") List<Integer> delete) {
		if (!delete.equals("")) {
			for (int i = 0; i < delete.size(); i++) {
				brandAccreditationService.deleteBrandAccreditation(brand_id, delete.get(i));
			}
		}
		return "redirect:../BrandAccreditation/" + brand_id.toString();
	}

	@RequestMapping("/BrandAddAccreditation/{bID}/{aID}")
	public String brandAddAccreditation(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("bID") Integer brand_id, @PathVariable("aID") Integer accreditation_id) {

		BrandAccreditation brandAccreditation = new BrandAccreditation();
		brandAccreditation.setBrand_id(brand_id);
		brandAccreditation.setAccreditation_id(accreditation_id);

		brandAccreditationService.insertBrandAccreditation(brandAccreditation);

		return "redirect:../../BrandAccreditation/" + brand_id.toString();
	}

	// report check
	@RequestMapping(value = "/CheckReport", method = { RequestMethod.GET })
	public ModelAndView checkReport(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("systems/CheckReport");
		List<Brand> brands = new ArrayList<>();
		List<Report> reports = reportService.getPendingReportList();
		for (Report report : reports) {
			brands.add(brandService.searchBrand(report.getBrand_id()));
		}
		// System.out.print(brands);
		mav.addObject("brands", brands);
		mav.addObject("reports", reports);
		mav.addObject("report", new Report());
		mav.addObject("message", "");
		return mav;
	}

	@RequestMapping(value = "/checkReportDelete", method = RequestMethod.POST)
	public String checkReportDelete(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "reportDelete") String[] delete) {
		Report report;
		// System.out.println(Arrays.toString(delete));
		for (String reportIDString : delete) {
			report = new Report();
			try {
				report.setReport_id(Integer.parseInt(reportIDString));
				// D for delete
				report.setStatus("D");
				reportService.editReportStatus(report);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "redirect:CheckReport";
	}

	@RequestMapping(value = "/checkReportUpdate", method = RequestMethod.POST)
	public ModelAndView checkReportUpdate(HttpServletRequest request, HttpServletResponse response, Report report) {
		System.out.println(report);
		ModelAndView mav = new ModelAndView("systems/CheckReport");
		String message = "";

		String[] coordinates = addressGetCoordinate(report.getStore_address_by_user());
		if (report.getStore_state_by_user() == null) {
			return mav.addObject("message", "Error! Null Input.");
		} else if (report.getStore_state_by_user().length() > 3) {
			return mav.addObject("message", "Error! State incorrect.");
		}
		if (report.getStore_postcode_by_user() == null) {
			return mav.addObject("message", "Null Input.");
		} else if (report.getStore_postcode_by_user().length() > 4) {
			return mav.addObject("message", "Postcode incorrect.");
		}
		Store store = new Store();
		// if get longitude and latitude failed
		if (coordinates[0] == null || coordinates[1] == null) {
			return mav.addObject("message", "Error! Address gets coordinates failed).");
		} else {
			// set latitude into store
			store.setStore_latitude(coordinates[0]);
			// set longitude into store
			store.setStore_longitude(coordinates[1]);
			// set name into store
			store.setStore_name(report.getStore_name_by_user());
			// set address into store
			store.setStore_address(report.getStore_address_by_user());
			// set state into store
			store.setStore_state(report.getStore_state_by_user());
			// set postcode into store
			store.setStore_postcode(report.getStore_postcode_by_user());
			if (storeService.insertStore(store)) {
				message += " Store insert success.";
			}
			// store duplicated
			else {
				message += " Store already exists.";
			}
		}
		store = storeService.searchStore(store);
		Product product = new Product();
		product.setStore_id(store.getStore_id());
		product.setBrand_id(report.getBrand_id());
		if (productService.insertProduct(product)) {
			message += " Product insert success.";
			storeService.setBrandsNum(store.getStore_id(), store.getBrands_num() + 1);
			message += " Brands number in store is added.";
		}
		// product duplicated
		else {
			message += " Product already exists.";
		}

		// C for confirmed
		report.setStatus("C");
		reportService.editReportStatus(report);
		message += " Report confirmed.";

		mav.addObject("message", message);
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
	public ModelAndView storeInsert(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("systems/Insert");

		List<Accreditation> accreditations = accreditationService.accreditationList();
		mav.addObject("accreditations", accreditations);

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
	public ModelAndView storeInsertProcess(HttpServletRequest request, HttpServletResponse response, Store store,
			Brand brand, Accreditation accreditation) {

		ModelAndView mav = new ModelAndView("systems/Insert");

		List<Accreditation> accreditations = accreditationService.accreditationList();
		mav.addObject("accreditations", accreditations);
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

	public ModelAndView brandInsertProcess(HttpServletRequest request, HttpServletResponse response, Brand brand,
			Store store, Accreditation accreditation) {

		ModelAndView mav = new ModelAndView("systems/Insert");
		List<Accreditation> accreditations = accreditationService.accreditationList();
		// return message
		String message = "";

		if (brandService.searchBrand(brand.getBrand_name(), brand.getBrand_category()).size() != 0) {
			message += "Brand already exists. ";
		} else {
			accreditations = accreditationService.accreditationList();
			BrandAccreditation brandAccreditation = new BrandAccreditation();

			// search exist accreditation
			Accreditation accreditationSearch = accreditationService
					.searchAccreditation(accreditation.getAccreditation_name());
			int accreditation_id;
			// Accreditation not exists
			if (accreditationSearch == null) {
				// add new accreditation
				accreditationService.insertAccreditation(accreditation);
				// accreditation_id = accreditations.size() + 1;
				// accreditation id equals new accreditation's id
				accreditationSearch = accreditationService.searchAccreditation(accreditation.getAccreditation_name());
				accreditation_id = accreditationSearch.getAccreditation_id();
				message += String.format("Accreditation added(id: %d). ", accreditation_id);

				// Add accreditation id into new brand
				brandAccreditation.setAccreditation_id(accreditation_id);
			}
			// Accreditation exists
			else {
				// accreditation id equals exist accreditation's id
				accreditation_id = accreditationSearch.getAccreditation_id();
				brandAccreditation.setAccreditation_id(accreditation_id);
				message += String.format("Accreditation exists(id: %d). ", accreditation_id);
			}

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
				brandAccreditation.setBrand_id(brandService
						.searchBrand(brand.getBrand_name(), brand.getBrand_category()).get(0).getBrand_id());
			}
			brandAccreditationService.insertBrandAccreditation(brandAccreditation);
		}

		accreditations = accreditationService.accreditationList();
		mav.addObject("accreditations", accreditations);

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