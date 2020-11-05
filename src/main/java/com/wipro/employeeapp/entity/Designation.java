package com.wipro.employeeapp.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name ="designation_table")
public class Designation {
	@Id
	private int id;
	private String name;
	private long salary;
	 
	
	public Designation() {
		super();
	}


	public Designation(int id, String name, long salary) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public long getSalary() {
		return salary;
	}


	public void setSalary(long salary) {
		this.salary = salary;
	}


	@Override
	public String toString() {
		return "Designation [id=" + id + ", name=" + name + ", salary=" + salary + "]";
	}

	
	
}
