package au.usyd.elec5619.KAF.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import au.usyd.elec5619.KAF.model.Accreditation;
import au.usyd.elec5619.KAF.model.Brand;
import au.usyd.elec5619.KAF.model.BrandWithAccreditation;
import au.usyd.elec5619.KAF.model.BrandWithAllAccreditations;
import au.usyd.elec5619.KAF.model.Product;
import au.usyd.elec5619.KAF.model.Store;
import au.usyd.elec5619.KAF.service.AccreditationService;
import au.usyd.elec5619.KAF.service.BrandService;
import au.usyd.elec5619.KAF.service.ProductService;
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

//
//  Store functions
//	
//  
//	Store delete functoin
//
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

//  Store detail
	@RequestMapping("/StoreDetails/{id}")
	public ModelAndView showStoreDetail(HttpServletRequest request, HttpServletResponse response, ModelMap map,
			@PathVariable("id") Integer store_id) {
		ModelAndView mav = new ModelAndView("systems/StoreDetails");

		List<Store> stores = new ArrayList<Store>();
		stores.add(storeService.searchStoreByID(store_id));

		map.put("stores", stores);

		return mav;
	}

//  edit store details
	@RequestMapping(value = "/StoreDetails/StoreEdit/{id}")
	public ModelAndView storeEdit(HttpServletRequest request, HttpServletResponse response, ModelMap map,
			@PathVariable("id") Integer store_id) {
		ModelAndView mav = new ModelAndView("systems/StoreEdit");

		List<Store> stores = new ArrayList<Store>();
		stores.add(storeService.searchStoreByID(store_id));

		map.put("stores", stores);

		mav.addObject("store", new Store());

		return mav;
	}

	@RequestMapping(value = "/StoreDetails/StoreEdit/storeEditProcess/{id}")
	public void storeEditProcess(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") Integer store_id, Store store) {

		store.setStore_id(store_id);
		storeService.editStore(store);

		System.out.println(store.toString());
		request.setAttribute("status", "OK");
	}

//  Show store's brands
	@RequestMapping("/StoreBrand/{id}")
	public ModelAndView showStoreBrand(HttpServletRequest request, HttpServletResponse response, ModelMap map,
			@PathVariable("id") Integer store_id) {
		ModelAndView mav = new ModelAndView("systems/StoreBrand");

		List<Store> stores = new ArrayList<Store>();
		stores.add(storeService.searchStoreByID(store_id));

		List<Brand> brands = new ArrayList<Brand>();
		List<Integer> brand_ids = productService.searchBrandByStore(store_id);

		for (int i = 0; i < brand_ids.size(); i++) {
			brands.add(brandService.searchBrandByID(brand_ids.get(i)));
		}

		map.put("brands", brands);
		map.put("stores", stores);

		return mav;
	}

//  Delete store's brands
	@RequestMapping("/StoreBrand/StoreBrandDelete/{id}")
	public String StoreBrandDelete(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("delete") List<Integer> delete, @PathVariable("id") Integer store_id) {

		for (int i = 0; i < delete.size(); i++) {
			Product product = productService.searchProduct(store_id, delete.get(i));
			productService.deleteProduct(product.getProduct_id());
		}
		Integer brands_num = productService.searchBrandByStore(store_id).size();
		storeService.setBrandsNum(store_id, brands_num);

		return "redirect:../" + store_id.toString();
	}

//  Add store's brands
	@RequestMapping("/StoreBrand/StoreBrandAdd/{id}")
	public ModelAndView StoreBrandAdd(HttpServletRequest request, HttpServletResponse response, ModelMap map,
			@PathVariable("id") Integer store_id) {
		ModelAndView mav = new ModelAndView("systems/StoreBrandAdd");

		List<Store> stores = new ArrayList<Store>();
		stores.add(storeService.searchStoreByID(store_id));

		List<Integer> brand_ids = productService.searchBrandByStore(store_id);
		List<Brand> brands_ex = new ArrayList<Brand>();
		List<Brand> brands = brandService.brandList();

		for (int i = 0; i < brands.size(); i++) {
			if (!brand_ids.contains(brands.get(i).getBrand_id())) {
				brands_ex.add(brandService.searchBrandByID(brands.get(i).getBrand_id()));
			}
		}

		List<BrandWithAccreditation> brandWithAccreditations = new ArrayList<BrandWithAccreditation>();
		for (int i = 0; i < brands_ex.size(); i++) {
			brandWithAccreditations.add(brandService.setBrandWithAccreditation(brands_ex.get(i)));
		}

		map.put("stores", stores);
		map.put("brands", brandWithAccreditations);

		mav.addObject("brand", new Brand());

		return mav;
	}

	@RequestMapping(value = "/StoreBrand/StoreBrandAdd/StoreBrandAddProcess/{id}")
	public void StoreBrandAddProcess(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") Integer store_id, @RequestParam("select") List<Integer> select) {

		for (int i = 0; i < select.size(); i++) {
			Product product = new Product();
			product.setBrand_id(select.get(i));
			product.setStore_id(store_id);
			productService.insertProduct(product);
		}

		Integer brands_num = productService.searchBrandByStore(store_id).size();
		storeService.setBrandsNum(store_id, brands_num);

		request.setAttribute("status", "OK");
	}

//  Brand Update Delete Functions 
	@RequestMapping(value = "/UpdateDeleteBrand")
	public ModelAndView showBrand(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		ModelAndView mav = new ModelAndView("systems/UpdateDeleteBrand");

		List<Brand> brands = brandService.brandList();
		List<BrandWithAllAccreditations> brandWithAllAccreditations = new ArrayList<BrandWithAllAccreditations>();
		for (int i = 0; i < brands.size(); i++) {
			if (i == 0) {
				brandWithAllAccreditations.add(brandService.setBrandWithAllAccreditations(brands.get(0)));
				continue;
			}
			for (int j = 0; j < brandWithAllAccreditations.size(); j++) {
				if ((brands.get(i).getBrand_name().equals(brandWithAllAccreditations.get(j).getBrand_name())) && (brands
						.get(i).getBrand_category().equals(brandWithAllAccreditations.get(j).getBrand_category()))) {
					brandService.setBrandWithAllAccreditations(brandWithAllAccreditations.get(j), brands.get(i));
					break;
				} else {
					if (j == (brandWithAllAccreditations.size() - 1)) {
						brandWithAllAccreditations.add(brandService.setBrandWithAllAccreditations(brands.get(i)));
						break;
					}
				}
			}
		}

		map.put("brands", brandWithAllAccreditations);

		return mav;
	}

	@RequestMapping(value = "/UpdateDeleteBrandProcess")
	public String updateDeleteBrandProcess(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("delete") List<String> delete) {
		String[][] array = new String[delete.size()][2];
		for (int i = 0; i < delete.size(); i++) {
			array[i] = delete.get(i).split("_", 2);
			array[i][0].replaceAll("\\+", "\\s*");
			array[i][1].replaceAll("\\+", "\\s*");
			
			List<Brand> brands = brandService.searchBrandByNameCategory(array[i][0], array[i][1]);
			for(int j = 0; j < brands.size(); j++) {
				brandService.deleteBrand(brands.get(j).getBrand_id());
			}
			
			List<Store> stores = storeService.storeList();
			for(int j = 0; j < stores.size(); j++) {
				Integer brands_num = productService.searchBrandByStore(stores.get(j).getStore_id()).size();
				storeService.setBrandsNum(stores.get(j).getStore_id(), brands_num);
			}
		}
		return "redirect:UpdateDeleteBrand";
	}

//  Brands detail
	@RequestMapping("/BrandDetails/{name}/{category}")
	public ModelAndView showBrandDetails(HttpServletRequest request, HttpServletResponse response, ModelMap map,
			@PathVariable("name") String brand_name, @PathVariable("category") String brand_category) {
		ModelAndView mav = new ModelAndView("systems/BrandDetails");

		List<Brand> brands = brandService.searchBrandByNameCategory(brand_name, brand_category);

		List<BrandWithAllAccreditations> brandWithAllAccreditations = new ArrayList<BrandWithAllAccreditations>();
		for(int i = 0; i< brands.size(); i++) {
			brandWithAllAccreditations.add(brandService.setBrandWithAllAccreditations(brands.get(i)));
		}
		map.put("brandWithAllAccreditations", brandWithAllAccreditations);

		return mav;
	}
	
	
	
	
	
//  Store and Brand Insert
	@RequestMapping(value = "/Insert")
	public ModelAndView storeInsert(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("systems/Insert");

		mav.addObject("store", new Store());
		mav.addObject("brand", new Brand());

		return mav;
	}

	@RequestMapping(value = "/storeInsertProcess")
	public ModelAndView storeInsertProcess(HttpServletRequest request, HttpServletResponse response, Store store,
			Brand brand) {
		ModelAndView mav = new ModelAndView("systems/Insert");

		if (!storeService.insertStore(store)) {
			mav.addObject("message", "Store already exists!!");
		} else {
//			mav.addObject("message", "Insert successfully!!");
			mav.addObject("message", storeService.countStore());
		}

		return mav;
	}

	@RequestMapping(value = "/brandInsertProcess")
	public ModelAndView brandInsertProcess(HttpServletRequest request, HttpServletResponse response, Brand brand,
			Store store) {
		ModelAndView mav = new ModelAndView("systems/Insert");

		if (!brandService.insertBrand(brand)) {
			mav.addObject("message", "Brand already exists!!");
		} else {
//			mav.addObject("message", "Insert successfully!!");
			mav.addObject("message", brandService.countBrand());
		}

		return mav;
	}
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
