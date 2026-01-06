package com.devconnect.ui_interaction_service.client;

import java.util.List;

import com.devconnect.ui_interaction_service.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.devconnect.ui_interaction_service.dto.User;

@FeignClient(name="user-client",url = "http://localhost:9999/user" ,configuration = FeignConfig.class)
public interface UserClient {
	@GetMapping("/{userId}")
	public User getUser(@PathVariable long userId);
	
	@GetMapping("/allUsers")
	public List<User> getUsers();
	
}
