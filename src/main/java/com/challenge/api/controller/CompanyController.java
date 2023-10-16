package com.challenge.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.challenge.api.model.Company;
import com.challenge.api.service.CompanyService;

  
@Controller
@RequestMapping("/company") // company getmapping fonksiyonu
public class CompanyController {
	
	@Autowired private CompanyService companyService;
	@GetMapping("") // "/tumEmplyees"
	public String index(Model model) {
		model.addAttribute("companies", companyService.companies());
		return "company_index";
	}
	//@RequestMapping(value = "/employees/{id}", method = RequestMethod.GET)
	/*
		@RequestMapping(value = "" , method  = RequestMethod.PUT  > GUNCELLEMK ICIN
															.DELETE > SILMEK
															.POST > EKLEMEK
	 */
	@GetMapping("/employees/{id}")

	public String companyEmployees(@PathVariable("id") int id, Model model) {
		model.addAttribute("cName", companyService.getCompanyById(id).getName());
		model.addAttribute("employees", companyService.companyEmployees(id));
		return "company_employees";
	}
	@GetMapping("/new")  
	public String newCompany() {
		return "company_new";
	}
	@GetMapping("/delete/{id}")
	public String deleteCompany(@PathVariable("id") int id) {
		companyService.deleteCompany(id);
		return "redirect:/company";
	}
	@GetMapping("/edit/{id}")
	
	public String editCompany(@PathVariable("id") int id, Model model) {
		Company company = companyService.getCompanyById(id);
		model.addAttribute("company", company);
		return "company_edit";
	}
	@PostMapping("/update/{id}")
	public String updateCompany(@PathVariable("id") int id, @ModelAttribute("company") Company company) {
		companyService.updateCompany(company, id);
		return "redirect:/company";
	}
	@PostMapping("/save")
	public String saveCompany(Company company) {
		companyService.saveCompany(company);
		return "redirect:/company";
	}
}
