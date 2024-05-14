package com.emp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.emp.entity.Employee;
import com.emp.service.EmployeeService;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/")
	public String index(org.springframework.ui.Model m)
	{
		List<Employee> list = employeeService.getAllEmployees();
		m.addAttribute("empList",list);
		return "index";
	}
	
	@GetMapping("/loadEmpSave")
	public String loadEmpSave() {
		return "save_emp";
	}
	
	@GetMapping("/editEmpSave/{id}")
	public String editEmpSave(@PathVariable int id,org.springframework.ui.Model m) {

		Employee emp =employeeService.getEmployeeById(id);
		m.addAttribute("emp",emp);
//		System.out.println(id);
		return "edit_emp";
	}
	
	@PostMapping("/saveEmp")
	public String saveEmp(@ModelAttribute Employee employee,HttpSession session) {
		
//		System.out.println(employee);
		Employee newEmp=employeeService.saveEmp(employee);
	
		if(newEmp!=null) {
//			System.out.println("Save successfull..");
			session.setAttribute("msg", "Register successfully");
		}
		else {
//			System.out.println("Something wrong on server");
			session.setAttribute("msg", "Someting wrong on server");
		}
		return "redirect:/loadEmpSave";
	}
	
	@PostMapping("/updateEmpDtls")
	public String updateEmp(@ModelAttribute Employee employee,HttpSession session) {
		
//		System.out.println(employee);
		Employee updateEmp=employeeService.saveEmp(employee);
	
		if(updateEmp!=null) {
//			System.out.println("Save successfull..");
			session.setAttribute("msg", "Update successfully");
		}
		else {
//			System.out.println("Something wrong on server");
			session.setAttribute("msg", "Someting wrong on server");
		}
		return "redirect:/";
	}
	
	@GetMapping("/deleteEmp/{id}")
	public String deleteEmpSave(@PathVariable int id,HttpSession session) {
		
		boolean f = employeeService.deleteEmployeeById(id);
		if(f) {
			session.setAttribute("msg", "Delete successfully...");
		}
		else {
			session.setAttribute("msg", "Something wrong on server");
		}
		return "redirect:/";
	}
	
}
