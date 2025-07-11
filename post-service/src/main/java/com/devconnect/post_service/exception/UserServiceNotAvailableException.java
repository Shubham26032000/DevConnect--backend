package com.devconnect.post_service.exception;

public class UserServiceNotAvailableException extends RuntimeException {
	public UserServiceNotAvailableException(String message) {
		super(message);
	}
}
