package com.wipro.employeeapp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="vehicle_table")
public class Vehicle {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vehicle_Id;
    private long regNo;
    private String model;
    private int makeYear;
    private String brand;
    
    @ManyToOne
    private Employee employee;


	public Vehicle() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Vehicle(int vehicle_Id, long regNo, String model, int makeYear, String brand, Employee employee) {
		super();
		this.vehicle_Id = vehicle_Id;
		this.regNo = regNo;
		this.model = model;
		this.makeYear = makeYear;
		this.brand = brand;
		this.employee = employee;
	}



	public int getVehicle_Id() {
		return vehicle_Id;
	}

	public void setVehicle_Id(int vehicle_Id) {
		this.vehicle_Id = vehicle_Id;
	}

	public long getRegNo() {
		return regNo;
	}

	public void setRegNo(long regNo) {
		this.regNo = regNo;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getMakeYear() {
		return makeYear;
	}

	public void setMakeYear(int makeYear) {
		this.makeYear = makeYear;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


	@Override
	public String toString() {
		return "Vehicle [vehicle_Id=" + vehicle_Id + ", regNo=" + regNo + ", model=" + model + ", makeYear=" + makeYear
				+ ", brand=" + brand + "]";
	}
	
}

