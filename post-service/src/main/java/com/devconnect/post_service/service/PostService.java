package com.devconnect.post_service.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.devconnect.post_service.dto.PostDto;
import org.springframework.stereotype.Service;

import com.devconnect.post_service.entity.Post;
import com.devconnect.post_service.exception.UserNotFoundException;
import com.devconnect.post_service.repository.PostRepository;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PostService {

	private PostRepository postRepository;

	private UserClient userClient;

	public PostService(PostRepository postRepository, UserClient userClient) {
		super();
		this.postRepository = postRepository;
		this.userClient = userClient;
	}

	public List<Post> getAllPost() {

		return postRepository.findAll();
	}

	public Post getPost(long postId) {
		return postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Invalid postId"));
	}

	public List<Post> getUsersPost(long userId) {
		return postRepository.findByUserId(userId);
	}

	public boolean deletePost(long postId, long userId) {
		Optional<Post> postOptional = postRepository.findById(postId);
		if (postOptional.isPresent() && postOptional.get().getUserId() == userId && isValidUser(userId)) {
			postRepository.deleteById(postId);
			return true;
		}
		return false;
	}

	public Post updatePost(long postId, Post post, long userId) {
		Optional<Post> postOptional = postRepository.findById(postId);
		if (postOptional.isPresent() && isValidUser(userId) && postOptional.get().getUserId() == userId) {
			Post postToUp = postOptional.get();
			if (post.getTitle() != null)
				postToUp.setTitle(post.getTitle());
			if (post.getContent() != null)
				postToUp.setContent(post.getContent());
			return postRepository.save(postToUp);
		}
		throw new RuntimeException("Invalid postId or invalid userId related to post");
	}

	public boolean createPost(PostDto post, long userId, MultipartFile image) throws IOException {
		isValidUser(userId);
		Post postToSave = new Post();
		postToSave.setTitle(post.getTitle());
		postToSave.setContent(post.getContent());
		postToSave.setUserId(userId);
		postToSave.setPostImage(image.getBytes());
		postToSave.setPostImageType(image.getContentType());
		postToSave.setPostImageName(image.getOriginalFilename());
		return postRepository.save(postToSave) != null;
	}

	private boolean isValidUser(long userId) {
		boolean isUserExist = userClient.validateUser(userId);
		if(!isUserExist)
			throw new UserNotFoundException("User not found with specified ID");
		return isUserExist;
	}

	public Post findImage(long postId) {
		Optional<Post> postOptional = postRepository.findById(postId);
		if(postOptional.isPresent()){
			return postOptional.get();
		}
		throw new RuntimeException("Post not preset.");
	}
}
