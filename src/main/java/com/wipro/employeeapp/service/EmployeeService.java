package com.wipro.employeeapp.service;

import java.util.*;

import org.springframework.data.domain.Page;

import com.wipro.employeeapp.entity.Employee;
import com.wipro.employeeapp.exception.EmployeeIdNotFoundException;

public interface EmployeeService {

	 
	 	Employee addEmployee(Employee employee);
		Employee getEmployee(int employeeId) throws EmployeeIdNotFoundException;
		ArrayList<Employee> getAllEmployees() throws EmployeeIdNotFoundException;
		boolean deleteEmployee(int eid) throws EmployeeIdNotFoundException;
		boolean updateEmployee(Employee employee, int eid) throws EmployeeIdNotFoundException;;
		List<Employee> sortEmployeeAscending(String sort);
		List<Employee> searchEmployees(Employee employee);
		List<Employee> filterEmployeesBy(String filterName, int id);
		Page<Employee> getAllEmployeesByPages(int pageNumber, int numberOfElementsPerPage);
		
}
