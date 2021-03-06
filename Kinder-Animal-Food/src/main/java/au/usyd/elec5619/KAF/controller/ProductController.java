package au.usyd.elec5619.KAF.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import au.usyd.elec5619.KAF.model.Accreditation;
import au.usyd.elec5619.KAF.model.Product;
import au.usyd.elec5619.KAF.model.Store;
import au.usyd.elec5619.KAF.service.AccreditationService;
import au.usyd.elec5619.KAF.service.BrandAccreditationService;
import au.usyd.elec5619.KAF.service.BrandService;
import au.usyd.elec5619.KAF.service.ProductService;
import au.usyd.elec5619.KAF.service.StoreService;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private StoreService storeService;

	@Autowired
	private BrandService brandService;
	
	@Autowired
	BrandAccreditationService brandAccreditationService;
	
	@Autowired
	AccreditationService accreditationService;
	
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public String listProducts(Model model) {
		model.addAttribute("listProducts", this.brandService.brandList());
		return "products";
	}
	
	@RequestMapping(value = "/browse/{page}", method = RequestMethod.GET)
	public String listBrand(Model model,@PathVariable("page") Integer page) {
		try {
		List list = this.brandService.brandList();
		model.addAttribute("listProducts", list.subList(12*(page-1),Math.min(12*page,list.size())));
		model.addAttribute("numPages",(int) Math.ceil(list.size() / 12));
		} catch (IndexOutOfBoundsException e) { 
            System.out.println("Exception thrown : " + e); 
        } catch (IllegalArgumentException e) { 
            System.out.println("Exception thrown : " + e); 
        }
		return "products";
	}
	@RequestMapping(value = "/browse", method = RequestMethod.GET)
	public String listBrand(Model model) {

		return listBrand(model,1);
	}
	
	@RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
	public String listProducts(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("product", this.brandService.searchBrand(id));
		
		
		List<Integer> accreditationId = this.brandAccreditationService.searchAccreditationByBrand(id);
		List<Accreditation> accreditation = new ArrayList<Accreditation>();
		for (Integer x : accreditationId) {
			accreditation.add(this.accreditationService.searchAccreditation(x));
			
		}
		model.addAttribute("accreditation", accreditation);
		Integer bestRating = 0 ;
		Integer goodRating = 0 ;
		Integer	avoidRating = 0 ;
		
		for (Accreditation a:accreditation) {
			if (a.getRating().equals("good")) {
				goodRating++;
			}
			
			if (a.getRating().equals("best")) {
				 bestRating++;
			}
			
			if (a.getRating().equals("avoid")) {
				avoidRating++;
			}
			
			
		}
		model.addAttribute("bestRating", bestRating);
		model.addAttribute("goodRating", goodRating);
		model.addAttribute("avoidRating", avoidRating);
		
		return "product";
	}
	@RequestMapping(value = "/search/{page}")
	public String listProducts(@RequestParam("search-term") String term, Model model,@PathVariable("page") Integer page) {
		
		try {
			List list =  this.brandService.searchBrand(term);
			model.addAttribute("listProducts", list.subList(12*(page-1),Math.min(12*page,list.size())));
			model.addAttribute("numPages",(int) Math.ceil(list.size() / 12));
			} catch (IndexOutOfBoundsException e) { 
	            System.out.println("Exception thrown : " + e); 
	        } catch (IllegalArgumentException e) { 
	            System.out.println("Exception thrown : " + e); 
	        }
		return "products";
	}
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String listProducts(@RequestParam("search-term") String term,Model model) {

		return listProducts(term,model,1);
	}
	
}
