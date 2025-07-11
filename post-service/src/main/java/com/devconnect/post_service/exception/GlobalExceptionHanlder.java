package com.devconnect.post_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHanlder {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> handleUserNotFound(UserNotFoundException ex){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}
	
	@ExceptionHandler(UserServiceNotAvailableException.class)
	public ResponseEntity<Object> handleUserServiceDown(UserServiceNotAvailableException ex){
		return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(ex.getMessage());
	}
}
