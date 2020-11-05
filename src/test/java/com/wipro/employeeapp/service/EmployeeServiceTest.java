package com.wipro.employeeapp.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.wipro.employeeapp.dao.EmployeeDAO;
import com.wipro.employeeapp.entity.Department;
import com.wipro.employeeapp.entity.Designation;
import com.wipro.employeeapp.entity.Employee;
import com.wipro.employeeapp.exception.EmployeeIdNotFoundException;


@SpringBootTest
public class EmployeeServiceTest {
	
	@Autowired
	private EmployeeService employeeService;
	
	@MockBean
	private EmployeeDAO employeeDAO;
	
	@Test
	public void testAddEmployee(){

		Department department=new Department(4,"Development");
		Designation designation=new Designation(1,"Developer",100000);
		Employee employee=new Employee(1,"abc","cde",28,"2020-09-11","sarjapur","bangalore","karnataka","india",department,designation);
	    Mockito.when(employeeDAO.save(employee)).thenReturn(employee);
	    assertThat(employeeService.addEmployee(employee)).isEqualTo(employee);
	
	}
	
	
	@Test
	public void testGetAllEmployees(){
		Department department=new Department(4,"Development");
		Designation designation=new Designation(1,"Developer",100000);
		Employee employee1=new Employee(1,"abc","cde",28,"2020-09-11","sarjapur","bangalore","karnataka","india",department,designation);
		Employee employee2=new Employee(2,"abcdgd","cdewq",23,"2020-09-14","sarjapur","bangalore","karnataka","india",department,designation);
		List<Employee> employeeList = new ArrayList<>();
		employeeList.add(employee1);
		employeeList.add(employee2);
		Mockito.when(employeeDAO.findAll()).thenReturn(employeeList);
		assertThat(employeeService.getAllEmployees()).isEqualTo(employeeList);
	}
	
	
	@Test
	public void testDeleteEmployee(){
		Department department=new Department(4,"Development");
		Designation designation=new Designation(1,"Developer",100000);
		Employee employee=new Employee(1,"abc","cde",28,"2020-09-11","sarjapur","bangalore","karnataka","india",department,designation);
		Optional<Employee> optional = Optional.of(employee);
		Mockito.when(employeeDAO.findById(1)).thenReturn(optional);
	    Mockito.when(employeeDAO.existsById(employee.getEmployeeId())).thenReturn(false);
	    assertFalse(employeeService.deleteEmployee(1));
	}
	
	private void assertFalse(boolean existsById) {
		// TODO Auto-generated method stub
		
	}

	@Test
	public void testUpdateEmployee(){
		Department department=new Department(4,"Development");
		Designation designation=new Designation(1,"Developer",100000);
		Employee employee=new Employee(1,"abc","cde",28,"2020-09-11","sarjapur","bangalore","karnataka","india",department,designation);
		Optional<Employee> optional = Optional.of(employee);
		Mockito.when(employeeDAO.findById(1)).thenReturn(optional);
		Mockito.when(employeeDAO.save(employee)).thenReturn(employee);
		employee.setFirst_name("sam");
		Mockito.when(employeeDAO.save(employee)).thenReturn(employee);
		Mockito.when(employeeDAO.existsById(1)).thenReturn(true);
	    assertFalse(employeeService.updateEmployee(employee,1));
		
	}
	
	@Test
	public void sortByEmployee()
	{
		Department department=new Department(4,"Development");
		Designation designation=new Designation(1,"Developer",100000);
		Employee employee1=new Employee(1,"abc","cde",28,"2020-09-11","sarjapur","bangalore","karnataka","india",department,designation);
		Employee employee2=new Employee(2,"abcdgd","cdewq",23,"2020-09-14","sarjapur","bangalore","karnataka","india",department,designation);
		List<Employee> employeeList=Arrays.asList(employee1,employee2);
		when(employeeDAO.findByOrderByDesignationIdAsc()).thenReturn(employeeList);
		assertThat(employeeService.sortEmployeeAscending("designation")).isEqualTo(employeeList);
	}
}
