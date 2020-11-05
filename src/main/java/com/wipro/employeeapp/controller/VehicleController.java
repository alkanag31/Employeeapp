package com.wipro.employeeapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.employeeapp.dao.EmployeeDAO;
import com.wipro.employeeapp.dao.VehicleDAO;
import com.wipro.employeeapp.entity.Vehicle;
import com.wipro.employeeapp.service.EmployeeServiceImpl;
import com.wipro.employeeapp.service.VehicleServiceImpl;



@RestController
@RequestMapping("/employee")
public class VehicleController {

	@Autowired 
	VehicleServiceImpl vehicleService;
	
	@Autowired
	EmployeeServiceImpl employeeService;

    
	@PostMapping("/{employeeId}/vehicle")
	public void addVehicle(@RequestBody Vehicle vehicle, @PathVariable int employeeId){
		
		vehicle.setEmployee(employeeService.getEmployee(employeeId));	
    	vehicleService.addEmployeeVehicle(vehicle);
    	System.out.println(vehicle);
    	
	}
	
	@GetMapping("/vehicle")
	public ResponseEntity<List<Vehicle>> showVehicles()
	{   
		System.out.println("starting...");
		List<Vehicle> list =vehicleService.getAllVehicles();
		System.out.println("ending...");
		return new ResponseEntity<List<Vehicle>>(list, HttpStatus.OK);
	}

}
