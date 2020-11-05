package com.wipro.employeeapp.service;

import java.util.List;

import com.wipro.employeeapp.entity.Vehicle;


public interface VehicleService {
	void addEmployeeVehicle(Vehicle vehicle);
	List<Vehicle> getAllVehicles();
}
