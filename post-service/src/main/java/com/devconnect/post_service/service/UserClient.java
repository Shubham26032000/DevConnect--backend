package com.devconnect.post_service.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "user-client", url = "http://localhost:9999/user")
public interface UserClient {

	@PostMapping("/validate/{userId}")
	public boolean validateUser(@PathVariable long userId);
}
