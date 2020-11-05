package com.wipro.employeeapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.wipro.employeeapp.entity.Employee;
import com.wipro.employeeapp.service.EmployeeService;
import com.wipro.employeeapp.service.EmployeeServiceImpl;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	private static final Logger logger=LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	EmployeeService employeeService;
	
	@PostMapping()
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee)
	{    
		logger.info("Adding an Employee");
		Employee addemployee=employeeService.addEmployee(employee);
		return new ResponseEntity<Employee>(addemployee,HttpStatus.OK);
	}
	
	@GetMapping("/{employeeId}")
	public ResponseEntity<Employee> showEmployee(@PathVariable("employeeId") int employeeId)
	{   
		logger.info("Get an Employee based on id");
		Employee employee =employeeService.getEmployee(employeeId);
		return new ResponseEntity<Employee>(employee,HttpStatus.OK);
	}
 
	@GetMapping()
	public ResponseEntity<ArrayList<Employee>> showAllEmployees()
	{   
		logger.info("Show all Employees");
		ArrayList<Employee> list=employeeService.getAllEmployees();
		return new ResponseEntity<ArrayList<Employee>>(list,HttpStatus.OK);	
	}
	
	@DeleteMapping("/{eid}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("eid") int eid)
	{
		logger.info("Deleting an employee");
		String msg = "";
		if(employeeService.deleteEmployee(eid))
			msg = "Employee deleted";
		else 
			msg = "Employee not deleted";
		return new ResponseEntity<String>(msg,HttpStatus.OK);	
	}
	
	@PutMapping("/{eid}")
	public ResponseEntity<String> updateEmployee(@RequestBody Employee employee, @PathVariable int eid)
	{
		logger.info("Updating an employee");
		String msg = "";
		if(employeeService.updateEmployee(employee, eid))
			msg = "Employee updated";
		else 
			msg = "Employee not updated";
		return new ResponseEntity<String>(msg,HttpStatus.OK);  
	}
	
	//Sorting
	
	@GetMapping("/sort")
    public ResponseEntity<List<Employee>> getEmployeebysorting(@RequestParam(value="sortStr") String sortStr)
    {
		logger.info("Sorting all employees");
		List<Employee> employeeList = employeeService.sortEmployeeAscending(sortStr);
        return new ResponseEntity<List<Employee>>(employeeList,HttpStatus.OK);
    }
	
	//Partial Search with criteria builder
	
	@PostMapping("/search")
	public ResponseEntity<List<Employee>> searchEmployees( @RequestBody Employee employee ) 
	{
		logger.info("Partial Search for employees");
		//System.out.println(employee);
		List<Employee> emp = employeeService.searchEmployees(employee);
		return new ResponseEntity<List<Employee>>( emp , HttpStatus.OK);
	}
	
	//Filter
	@GetMapping("/filter/{filterName}/{id}")
	public ResponseEntity<List<Employee>> filterAllEmployees(@PathVariable String filterName, @PathVariable int id)
	{  
		logger.info("Filter employee");
		List<Employee> lists=employeeService.filterEmployeesBy(filterName, id);
		return new ResponseEntity<List<Employee>>(lists,HttpStatus.OK);
	} 
	
	
	//Pagination
	@GetMapping("/pages/{pageNumber}/{numberOfElementsPerPage}")
    public Page<Employee> getAllEmployeesByPages(@PathVariable int pageNumber, @PathVariable int numberOfElementsPerPage)
    {
		logger.info(" Getting Employees in pages");
		return employeeService.getAllEmployeesByPages(pageNumber, numberOfElementsPerPage);
    }
	
}
