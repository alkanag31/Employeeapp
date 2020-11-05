package com.wipro.employeeapp.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.employeeapp.dao.VehicleDAO;
import com.wipro.employeeapp.entity.Vehicle;


@Service
public class VehicleServiceImpl implements VehicleService {
	
	@Autowired
	VehicleDAO vehicleDAO;

	@Override
	public void addEmployeeVehicle(Vehicle vehicle) {
		vehicleDAO.save(vehicle);
	}
	
	@Override
	public List<Vehicle> getAllVehicles() {
		 return vehicleDAO.findAll();
	}
	
	
}
