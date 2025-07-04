package com.devconnect.post_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devconnect.post_service.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long>{

	List<Post> findByUserId(long userId);

}
