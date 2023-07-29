package com.example.employeemanagementsystem.service;
import java.util.List;

import com.example.employeemanagementsystem.entity.Employee;

public interface EmployeeService {

	public List<Employee> findAll();
	
	public Employee findById(int theId);
	
	public void save(Employee theemp);
	
	public void deleteById(int theId);
	
}