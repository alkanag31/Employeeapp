package com.wipro.employeeapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.employeeapp.entity.Vehicle;


public interface VehicleDAO extends JpaRepository<Vehicle, Integer>{

	

}
