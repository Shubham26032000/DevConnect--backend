package com.devconnect.use_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devconnect.use_service.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{

    User findByUsernameAndPassword(String username, String password);

    User findByEmailAndPassword(String email, String password);



    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);


}
