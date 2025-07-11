package com.devconnect.post_service.service;

import org.springframework.stereotype.Component;

import com.devconnect.post_service.exception.UserServiceNotAvailableException;

@Component
public class UserClientFallBack implements UserClient {

	@Override
	public boolean validateUser(long userId) {
		System.out.println("Error occurs Shubham...!");
		throw new UserServiceNotAvailableException("User Service is down...!Please try after sometime");
	}

}
