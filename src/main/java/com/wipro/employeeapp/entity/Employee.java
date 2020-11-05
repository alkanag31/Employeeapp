package com.wipro.employeeapp.entity;

import java.util.List;

import javax.persistence.*;


@Entity
@Table(name ="employee_table")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int employeeId;
	private String first_name;
	private String last_name;
	private int age;
	private String dob;
    private String address1;
    private String address2;
    private String state;
    private String country;
    
    @OneToOne(targetEntity = Department.class)
    @JoinColumn(name="department_id", referencedColumnName = "id")
	private Department department;
    
    @OneToOne(targetEntity = Designation.class)
    @JoinColumn(name="designation_id", referencedColumnName = "id")
	private Designation designation;
    
    public Employee() {
		super();
	}

	
	public Employee(int employeeId, String first_name, String last_name, int age, String dob, String address1,
			String address2, String state, String country, Department department, Designation designation) {
		super();
		this.employeeId = employeeId;
		this.first_name = first_name;
		this.last_name = last_name;
		this.age = age;
		this.dob = dob;
		this.address1 = address1;
		this.address2 = address2;
		this.state = state;
		this.country = country;
		this.department = department;
		this.designation = designation;
	}


	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Designation getDesignation() {
		return designation;
	}

	public void setDesignation(Designation designation) {
		this.designation = designation;
	}
	

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", first_name=" + first_name + ", last_name=" + last_name
				+ ", age=" + age + ", dob=" + dob + ", address1=" + address1 + ", address2=" + address2 + ", state="
				+ state + ", country=" + country + ", department=" + department + ", designation=" + designation
				+ "]";
	}


}
