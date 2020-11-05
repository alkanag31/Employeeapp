package com.wipro.employeeapp.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wipro.employeeapp.entity.Department;
import com.wipro.employeeapp.entity.Designation;
import com.wipro.employeeapp.entity.Employee;
import com.wipro.employeeapp.service.EmployeeServiceImpl;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {
	
	@Autowired 
	private MockMvc mockMvc;
	
	@MockBean 
	private EmployeeServiceImpl employeeService;
   
    @Test
    public void testAddEmployee() throws Exception 
    {
    	Department department=new Department(4,"Development");
		Designation designation=new Designation(1,"Developer",100000);
		Employee employee=new Employee(1,"abc","cde",28,"2020-09-11","sarjapur","bangalore","karnataka","india",department,designation);
    	String input=this.mapToJson(employee);
    	String uri="/employees"; 
     	Mockito.when(employeeService.addEmployee(Mockito.any(Employee.class))).thenReturn(employee);
    	RequestBuilder requestBuilder=MockMvcRequestBuilders.post(uri).accept(MediaType.APPLICATION_JSON).content(input).contentType(MediaType.APPLICATION_JSON); 
    	MvcResult result=mockMvc.perform(requestBuilder).andReturn();
     	MockHttpServletResponse response=result.getResponse();     
    	String outputInJson=response.getContentAsString();       
    	assertThat(outputInJson).isEqualTo(input);           
    	
   }
    
    @Test
	public void testGetEmployee() throws Exception {
    	Department department=new Department(4,"Development");
		Designation designation=new Designation(1,"Developer",100000);
		Employee employee=new Employee(1,"abc","cde",28,"2020-09-11","sarjapur","bangalore","karnataka","india",department,designation);
		Mockito.when(employeeService.getEmployee(Mockito.anyInt())).thenReturn(employee);
		
		String URI = "/employees/1";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				URI).accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedJson = this.mapToJson(employee);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);
	}

	@Test
	public void testGetAllEmployees() throws Exception {

		Department department=new Department(4,"Development");
		Designation designation=new Designation(1,"Developer",100000);
		Employee employee1=new Employee(1,"abc","cde",28,"2020-09-11","sarjapur","bangalore","karnataka","india",department,designation);
		Employee employee2=new Employee(1,"abc","cde",28,"2020-09-11","sarjapur","bangalore","karnataka","india",department,designation);
		
		List<Employee> employeeList = new ArrayList<>();
		employeeList.add(employee1);
		employeeList.add(employee2);
		
		Mockito.when(employeeService.getAllEmployees()).thenReturn((ArrayList<Employee>) employeeList);
		
		String URI = "/employees";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				URI).accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expectedJson = this.mapToJson(employeeList);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);
	}
	
	@Test
	   public void deleteEmployee() throws Exception
	   {
			Department dep=new Department(4,"Development");
			Designation desig=new Designation(1,"Developer",100000);
			Employee employee=new Employee(1,"abc","cde",28,"2020-09-11","sarjapur","bangalore","karnataka","india",dep,desig);
			String input=this.mapToJson(employee);
			String uri="/employees/1";
			Mockito.when(employeeService.deleteEmployee(Mockito.anyInt())).thenReturn(true);
			RequestBuilder requestBuilder=MockMvcRequestBuilders.delete(uri); 
			MvcResult result=mockMvc.perform(requestBuilder).andReturn();
			MockHttpServletResponse response=result.getResponse();     
			String output=response.getContentAsString();         	
			assertThat(output).isEqualTo("Employee deleted");           
	   
	   }
	   
	   @Test
	   public void updateEmployee() throws Exception
	   {
		    Department dep=new Department(4,"Development");
		    Designation desig=new Designation(1,"Developer",100000);
		    Employee employee=new Employee(1,"abc","cde",28,"2020-09-11","sarjapur","bangalore","karnataka","india",dep,desig);
		    String input=this.mapToJson(employee);
	   		String uri="/employees/1";
	   		Mockito.when(employeeService.updateEmployee(Mockito.any(Employee.class), Mockito.anyInt())).thenReturn(true); 
	   		RequestBuilder requestBuilder=MockMvcRequestBuilders.put(uri).accept(MediaType.APPLICATION_JSON).content(input).contentType(MediaType.APPLICATION_JSON); 
	   		MvcResult result=mockMvc.perform(requestBuilder).andReturn();
	   		MockHttpServletResponse response=result.getResponse();    
	   		String output=response.getContentAsString();
	   		assertThat(output).isEqualTo("Employee updated");           
	   }

    /**
	 * Maps an Object into a JSON String. Uses a Jackson ObjectMapper.
	 */
	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
}