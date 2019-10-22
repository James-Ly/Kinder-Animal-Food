package au.usyd.elec5619.KAF.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import au.usyd.elec5619.KAF.model.Report;
import au.usyd.elec5619.KAF.service.ReportService;

@Controller
public class ReportController {
	
	@Autowired
	private ReportService reportService;
	
	
	@RequestMapping(value = "/report", method = RequestMethod.GET)
	public String showReport(Model model) {
		return "report";
	}
    @RequestMapping(value = "/submitReport", method = RequestMethod.POST)
    public String submit(@Valid @ModelAttribute("report")Report report, 
      BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "error";
        }

        reportService.insertReport(report);
        return "return";
    }
}
