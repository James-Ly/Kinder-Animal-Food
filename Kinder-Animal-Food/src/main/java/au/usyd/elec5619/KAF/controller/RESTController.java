package au.usyd.elec5619.KAF.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import au.usyd.elec5619.KAF.model.Brand;
import au.usyd.elec5619.KAF.model.Store;
import au.usyd.elec5619.KAF.service.BrandService;
import au.usyd.elec5619.KAF.service.ProductService;
import au.usyd.elec5619.KAF.service.StoreService;


@RestController
@RequestMapping("/api")
public class RESTController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	BrandService brandService;
	
	@Autowired
	StoreService storeService;
	
	@GetMapping("/locate")
	public @ResponseBody List<Store> showBrandLocation(@RequestParam("brandName") String brandName, @RequestParam("location") String location) {
		List<Brand> theBrand = brandService.searchBrand(brandName);
		List<Integer> storeId = null;
		List<Store> storeList = new ArrayList<Store>();
		String[] userCoordinates = addressGetCoordinate(location);
		if(theBrand != null) {
			storeId = productService.searchStoreByBrand(theBrand.get(0).getBrand_id());
			storeList = storeService.searchStoreByDistance(userCoordinates,storeId);
		}
		
		return storeList;
	}
	
	@GetMapping("/testing")
	public String testingController() {
		return "testing String";
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
