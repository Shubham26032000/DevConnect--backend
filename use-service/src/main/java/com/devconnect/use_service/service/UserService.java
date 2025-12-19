package com.devconnect.use_service.service;

import java.util.List;
import java.util.Optional;

import com.devconnect.use_service.dto.LoginDTO;
import org.springframework.stereotype.Service;

import com.devconnect.use_service.controller.UserController;
import com.devconnect.use_service.entity.User;
import com.devconnect.use_service.repository.UserRepository;

@Service
public class UserService {

    private UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	public User save(User user) {
		return userRepository.save(user);
	}

	public User updateUser(long userId, User user) {
		Optional<User> userOptional = userRepository.findById(userId);
		if(userOptional.isPresent()){
		User userToUpdate = userOptional.get();	
		if(user.getUsername()!= null)
			userToUpdate.setUsername(user.getUsername());
		if(user.getPassword()!= null)
			userToUpdate.setPassword(user.getPassword());
		return userRepository.save(userToUpdate);
		}
		throw new RuntimeException("Can't update as user not found");
	}

	public User getUser(long userId) {
		Optional<User> userOptional = userRepository.findById(userId);
		return userOptional.orElseThrow(()->  new RuntimeException("User Not found with ID : "+userId));
	}

	public boolean isValidUser(long userId) {
		Optional<User> userOptional = userRepository.findById(userId);
		return userOptional.isPresent();
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User loginUser(LoginDTO loginDTO){
		if(loginDTO.getUsername() != null){
			return userRepository.findByUsernameAndPassword(loginDTO.getUsername(),loginDTO.getPassword());
		}
		if(loginDTO.getEmail() != null){
			return userRepository.findByEmailAndPassword(loginDTO.getEmail(),loginDTO.getPassword());
		}

		throw new RuntimeException("User Not Found");
	}
	
	
	
}
