package com.devconnect.use_service.controller;

import java.util.List;

import com.devconnect.use_service.dto.LoginRequest;
import com.devconnect.use_service.dto.LoginSuccessResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devconnect.use_service.entity.User;
import com.devconnect.use_service.service.UserService;
import com.devconnect.use_service.dto.CreateUserDto;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/create")
	public User createUser(@RequestBody CreateUserDto user) {
		return this.userService.save(user);
	}

	@PostMapping("/login")
	public LoginSuccessResponse login(@RequestBody CreateUserDto createUserDto){
		return userService.loginUser(createUserDto);
	}

	@PutMapping("/update/{userId}")
	public User updateUser(@PathVariable long userId, @RequestBody User user) {
		return this.userService.updateUser(userId,user);
	}
	
	@GetMapping("/{userId}")
	public User getUser(@PathVariable long userId) {
		return this.userService.getUser(userId);
	}
	
	@PostMapping("/validate/{userId}")
	public boolean validateUser(@PathVariable long userId) {
		return this.userService.isValidUser(userId);
	}
	
	@GetMapping("/allUsers")
	public List<User> getUsers(){
		return this.userService.getAllUsers();
	}

	@PostMapping("/internal/validate")
	public boolean validate(@RequestBody LoginRequest request) {
		return userService.findByUsername(request);
	}

}
