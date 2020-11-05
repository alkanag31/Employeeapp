package com.wipro.employeeapp.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.wipro.employeeapp.entity.ApiError;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(EmployeeIdNotFoundException.class)
	public ResponseEntity<ApiError> handleIdNotFound(EmployeeIdNotFoundException ex)
	{  
		 System.out.println(ex.getMessage());
    	 String message=ex.getMessage();
    	 ArrayList<String> details=new ArrayList<String>();
    	 details.add("Employee not found ");
    	 ApiError error=new ApiError(message,details,HttpStatus.BAD_REQUEST,LocalDateTime.now());
    	 return new ResponseEntity<ApiError>(error,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiError> handleEmptyListException(Exception ex)
	{  
		 System.out.println(ex.getMessage());
    	 String message=ex.getMessage();
    	 ArrayList<String> details=new ArrayList<String>();
    	 details.add("Exception occured ");
    	 ApiError error=new ApiError(message,details,HttpStatus.BAD_REQUEST,LocalDateTime.now());
    	 return new ResponseEntity<ApiError>(error,HttpStatus.BAD_REQUEST);
	}
	
}
