package au.usyd.elec5619.KAF.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import au.usyd.elec5619.KAF.model.Brand;
import au.usyd.elec5619.KAF.model.Store;
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
		
	@GetMapping("/locate")
	public String showLocate(Model theModel) {
		
		theModel.addAttribute("crmLocate",new CrmLocate());
		return "client/locate";
	}
	
	
	
//	@PostMapping("/locate-brand")
//	public String showBrandLocation(@Valid @ModelAttribute("crmLocate") CrmLocate theCrmLocate, 
//			BindingResult theBindingResult, 
//			Model theModel) {
//		List<Brand> theBrand = brandService.searchBrand(theCrmLocate.getBrandName());
//		List<Integer> storeId = null;
//		List<Store> storeList = new ArrayList<Store>();
//		String[] userCoordinates = addressGetCoordinate(theCrmLocate.getLocation());
//		if(theBrand != null) {
//			storeId = productService.searchStoreByBrand(theBrand.get(0).getBrand_id());
//			storeList = storeService.searchStoreByDistance(userCoordinates,storeId);
//		}
//		List<String> addressName = new ArrayList<String>();
//		List<String> addressList = new ArrayList<String>();
//		List<String> addressLat = new ArrayList<String>();
//		List<String> addressLng = new ArrayList<String>();
//		
//		for(int i = 0 ; i< storeList.size(); i++) {
//			addressName.add(storeList.get(i).getStore_name());
//			addressList.add(storeList.get(i).getStore_address());
//			addressLat.add(storeList.get(i).getStore_latitude());
//			addressLng.add(storeList.get(i).getStore_longitude());
//		}
//		theModel.addAttribute("addressList",addressList);
//		theModel.addAttribute("addressName",addressName);
//		theModel.addAttribute("addressLat",addressLat);
//		theModel.addAttribute("addressLng",addressLng);
//		
//		if (theBindingResult.hasErrors()){
//			 return "client/locate";
//		 }
//		
//		return "client/locate2";
//	}
	
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
