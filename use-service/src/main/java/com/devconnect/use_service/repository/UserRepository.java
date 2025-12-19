package com.devconnect.use_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devconnect.use_service.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

    User findByUsernameAndPassword(String username, String password);

    User findByEmailAndPassword(String email, String password);
}
