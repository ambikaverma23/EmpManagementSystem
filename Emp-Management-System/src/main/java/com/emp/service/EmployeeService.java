package com.emp.service;

import java.util.List;

import com.emp.entity.Employee;

public interface EmployeeService {

	public Employee saveEmp(Employee employee);
	
	public List<Employee> getAllEmployees();
	
	public Employee getEmployeeById(int id);
	
	public boolean deleteEmployeeById(int id);

	 
}
