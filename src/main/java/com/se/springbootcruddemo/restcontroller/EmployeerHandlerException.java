package com.se.springbootcruddemo.restcontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeerHandlerException {
	
	@ExceptionHandler
	public ResponseEntity<EmployeeErrorResponse> handlerException(EmployeeNotFoundException ex){
		EmployeeErrorResponse error= new EmployeeErrorResponse();
		
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(ex.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}


}
