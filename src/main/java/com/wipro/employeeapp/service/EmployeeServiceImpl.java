package com.wipro.employeeapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.wipro.employeeapp.dao.EmployeeDAO;
import com.wipro.employeeapp.entity.Employee;
import com.wipro.employeeapp.exception.EmployeeIdNotFoundException;



@Service
public class EmployeeServiceImpl implements EmployeeService {

	private static final Logger logger=LoggerFactory.getLogger(EmployeeServiceImpl.class);
	
	@Autowired
	EmployeeDAO employeeDAO;
	
	@Autowired
	EntityManager entityManager;
	
	@Override
	public Employee addEmployee(Employee employee) {
		Employee addemployee=employeeDAO.save(employee);
		return addemployee;
	}

	public Employee getEmployee(int employeeId) {
		 Employee employee = null;
		 if(employeeDAO.existsById(employeeId)) {
			 employee=employeeDAO.findById(employeeId).orElse(new Employee());
		 	return employee;
		 }
		 else {
			  logger.error("employee not found");
		      throw new EmployeeIdNotFoundException("Employee id is not valid");
		 }
	 }

	public ArrayList<Employee> getAllEmployees() {
		ArrayList<Employee> list =(ArrayList<Employee>) employeeDAO.findAll();
		if (list==null) {
			logger.error("employees not found");
		      throw new EmployeeIdNotFoundException("Employees not found");
		}
		else return list;
	}

	public boolean deleteEmployee(int employee_id) {
		Optional<Employee> employee = employeeDAO.findById(employee_id);
		if(employee.isPresent())
		{
			employeeDAO.delete(employee.get());
			return true;
		}
		else {
				logger.error("employee not deleted");
				throw new EmployeeIdNotFoundException("Employee id is not valid");
		}
	}

	public boolean updateEmployee(Employee employee, int eid) {
		if(employeeDAO.existsById(eid))
		{
			employeeDAO.save(employee);
			return true;
		}
		else {
			logger.error("employee not updated");
			throw new EmployeeIdNotFoundException("Employee id is not valid");
	     }
	}

	@Override
	public List<Employee> sortEmployeeAscending(String sortStr) {
		if(sortStr.equals("designation")) {
			return employeeDAO.findByOrderByDesignationIdAsc();
		}
		else if(sortStr.equals("department")) {
				return employeeDAO.findByOrderByDepartmentIdAsc();
		}
		else if(sortStr.equals("state")) {
	        	return employeeDAO.findByOrderByStateAsc();
		 }
		return null;
	}
	
	@Override
	public List<Employee> searchEmployees(Employee employee) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
		Root<Employee> root = criteriaQuery.from(Employee.class);
		String firstName = employee.getFirst_name();
		System.out.println(firstName);
		String lastName = employee.getLast_name();
		System.out.println(lastName);
		
		//Adding search criteria's for query using CriteriaBuilder
		 
		List<Predicate> searchCriterias = new ArrayList<>();
		
		if( (firstName != "") && (firstName != null) ) {
			searchCriterias.add( criteriaBuilder.like( root.get("first_name"), "%"+firstName+"%") );
		}
		if( (lastName != "") && (lastName != null) ) {
			searchCriterias.add( criteriaBuilder.like( root.get("last_name"), "%"+lastName+"%") );
		}
		
		criteriaQuery.select( root ).where( criteriaBuilder.and( searchCriterias.toArray(new Predicate[searchCriterias.size()]) ));
		return entityManager.createQuery(criteriaQuery).getResultList();
	}

	@Override
	public List<Employee> filterEmployeesBy(String filterName, int id) {
		if(filterName.equals("designation")) {
			return employeeDAO.findAllByDesignationId(id);
		}
		else if(filterName.equals("department")) {
				return employeeDAO.findAllByDepartmentId(id);
		}
		return null;
	}
	
	@Override
	public Page<Employee> getAllEmployeesByPages(int pageNumber, int numberOfElementsPerPage){
		return employeeDAO.findAll(PageRequest.of(pageNumber, numberOfElementsPerPage));
	}


}

