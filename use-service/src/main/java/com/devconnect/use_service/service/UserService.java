package com.devconnect.use_service.service;

import java.util.List;
import java.util.Optional;

import com.devconnect.use_service.dto.CreateUserDto;
import com.devconnect.use_service.dto.LoginRequest;
import com.devconnect.use_service.dto.LoginSuccessResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.devconnect.use_service.entity.User;
import com.devconnect.use_service.repository.UserRepository;
import org.springframework.ui.ModelMap;

@Service
public class UserService {

    private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public UserService(UserRepository userRepository,PasswordEncoder passwordEncoder) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public User save(CreateUserDto userDto) {
		System.out.println(userDto.getPassword());
		User user = new User();
		user.setPassword(userDto.getPassword());
		user.setEmail(userDto.getEmail());
		user.setUsername(userDto.getUsername());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
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

	public LoginSuccessResponse loginUser(CreateUserDto createUserDto) {

		User user;

		if (createUserDto.getUsername() != null) {
			user = userRepository.findByUsername(createUserDto.getUsername())
					.orElseThrow(() -> new RuntimeException("User not found"));
		} else if (createUserDto.getEmail() != null) {
			user = userRepository.findByEmail(createUserDto.getEmail())
					.orElseThrow(() -> new RuntimeException("User not found"));
		} else {
			throw new RuntimeException("Username or email required");
		}

		if (!passwordEncoder.matches(createUserDto.getPassword(), user.getPassword())) {
			throw new RuntimeException("Invalid credentials");
		}



		return new LoginSuccessResponse(user.getId(),user.getUsername()); // TEMPORARY – will be changed to DTO
	}



	public boolean findByUsername(LoginRequest request) {
		Optional<User> userOp = userRepository.findByUsername(request.username());
		User user = userOp.get();
		return user != null &&
				user.getPassword().equals(request.password());
    }
}
