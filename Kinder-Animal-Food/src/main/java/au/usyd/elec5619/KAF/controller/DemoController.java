package au.usyd.elec5619.KAF.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {
	
	@GetMapping("/")
	public String showHome() {
		return "home";
	}
	
	@GetMapping("/locate")
	public String showLocate() {
		return "client/locate";
	}
	
}










