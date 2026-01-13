package com.devconnect.use_service.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.devconnect.use_service.dto.LoginRequest;
import com.devconnect.use_service.dto.LoginSuccessResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devconnect.use_service.entity.User;
import com.devconnect.use_service.service.UserService;
import com.devconnect.use_service.dto.CreateUserDto;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> createUser(@RequestPart("user") CreateUserDto user, @RequestPart("image") MultipartFile image) {
        try {
            LoginSuccessResponse response = this.userService.save(user,image);
			return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

	@PostMapping("/login")
	public LoginSuccessResponse login(@RequestBody CreateUserDto createUserDto){
		System.out.println("login user");
		return userService.loginUser(createUserDto);
	}

	@PutMapping("/update/{userId}")
	public User updateUser(@PathVariable long userId, @RequestBody User user) {
		return this.userService.updateUser(userId,user);
	}
	
	@GetMapping("/{userId}")
	public User getUser(@PathVariable long userId) {
		System.out.println("user");
		return this.userService.getUser(userId);
	}

	@GetMapping("/{userId}/image")
	public ResponseEntity<?> getProfilePic(@PathVariable long userId){
		Optional<User> userOptional = userService.findById(userId);
		if(userOptional.isPresent()) {
			User user = userOptional.get();
			byte[] imageFile = user.getProfileImage();
			return ResponseEntity.ok()
					.contentType(MediaType.valueOf(user.getImageType()))
					.body(imageFile);
		}
		return new ResponseEntity<>("Error occured while sending image",HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping("/validate/{userId}")
	public boolean validateUser(@PathVariable long userId) {
		return this.userService.isValidUser(userId);
	}

//	@PostMapping("/postUser")
//	public ResponseEntity<?> submitUser(@RequestParam long userId,@RequestPart("user") CreateUserDto user,@RequestPart("image") MultipartFile image){
//		System.out.println("WORKING");
//		return new ResponseEntity<>("OK OK Working",HttpStatus.OK);
//	}
	
	@GetMapping("/allUsers")
	public List<User> getUsers(){
		return this.userService.getAllUsers();
	}

	@PostMapping("/internal/validate")
	public boolean validate(@RequestBody LoginRequest request) {
		System.out.println("Here");
		return userService.findByUsername(request);
	}

}
