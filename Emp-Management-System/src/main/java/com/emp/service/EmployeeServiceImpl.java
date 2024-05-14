package com.emp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.emp.entity.Employee;
import com.emp.repository.EmployeeRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class EmployeeServiceImpl  implements EmployeeService{

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public Employee saveEmp(Employee employee) {
		
		Employee newEmp=employeeRepository.save(employee);
		
		return newEmp;
	}

	@Override
	public List<Employee> getAllEmployees() {
		 
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(int id) {
		
		return employeeRepository.findById(id).get();
	}

	@Override
	public boolean deleteEmployeeById(int id) {
		 
		Employee emp = employeeRepository.findById(id).get();
		if(emp!=null) {
			employeeRepository.delete(emp);
			return true;
		}
		return false;
	}
	
	public void removeSessionMessage() {
		
	HttpSession session=	((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest().getSession();
	
    session.removeAttribute("msg");	
	}

	 

}
