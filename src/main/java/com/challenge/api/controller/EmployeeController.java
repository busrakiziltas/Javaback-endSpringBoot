package com.challenge.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.challenge.api.model.Employee;
import com.challenge.api.service.CompanyService;
import com.challenge.api.service.EmployeeService;


@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired private EmployeeService employeeService;
	@Autowired private CompanyService companyService;
	
	@GetMapping("")
	public String index(Model model) {
		model.addAttribute("employees", employeeService.employees());
		return "employee_index";
	}
	@GetMapping("/new")  
	public String newEmployee(Model model) {
		model.addAttribute("companies", companyService.companies());
		return "employee_new";
	}
	@GetMapping("/delete/{id}")
	public String deleteEmployee(@PathVariable("id") int id) {
		employeeService.deleteEmployee(id);
		return "redirect:/employee";
	}
	@GetMapping("/edit/{id}")
	public String editEmployee(@PathVariable("id") int id, Model model) {
		model.addAttribute("companies",companyService.companies());
		model.addAttribute("employee", employeeService.getEmployeeById(id));
		return "employee_edit";
	}
	@PostMapping("/update/{id}")
	public String updateEmployee(@PathVariable("id") int id, @ModelAttribute("employee") Employee employee) {
		employeeService.updatEmployee(employee, id);
		return "redirect:/employee";
	}
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		employeeService.savEmployee(employee);
		return "redirect:/employee";
	}
}
