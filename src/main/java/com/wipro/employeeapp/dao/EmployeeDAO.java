package com.wipro.employeeapp.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.employeeapp.entity.Employee;

public interface EmployeeDAO extends JpaRepository<Employee, Integer>{
	
	List<Employee> findByOrderByDesignationIdAsc();
	List<Employee> findByOrderByDepartmentIdAsc();
	List<Employee> findByOrderByStateAsc();
	
	List<Employee> findAllByDesignationId(int id);
	List<Employee> findAllByDepartmentId(int id);
}
