package com.devconnect.post_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devconnect.post_service.entity.Post;
import com.devconnect.post_service.exception.UserServiceNotAvailableException;
import com.devconnect.post_service.service.PostService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/posts")
public class PostController {

	private PostService postService;

	public PostController(PostService postService) {
		super();
		this.postService = postService;
	}
	
	@PostMapping("/create")
	@CircuitBreaker(name="userServiceCB",fallbackMethod = "fallback")
	public ResponseEntity<Object> createPost(@RequestBody Post post,@RequestParam("userId")long userId) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.postService.createPost(post,userId));
	}
	
	@PutMapping("/update/{postId}")
	public ResponseEntity<Object> updatePost(@PathVariable long postId,@RequestBody Post post, @RequestParam("userId")long userId) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.postService.updatePost(postId,post,userId));
	}
	
	@GetMapping("/{postId}")
	public ResponseEntity<Object> getPost(@PathVariable long postId) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.postService.getPost(postId));
	}
	
	@DeleteMapping("/{postId}")
	public ResponseEntity<Object> deletePost(@PathVariable long postId, @RequestParam("userId") long userId) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.postService.deletePost(postId,userId));
	}
	
	@GetMapping("/user-post")
	public ResponseEntity<Object> getUsersPost(@RequestParam("userId")long userId){
		return ResponseEntity.status(HttpStatus.CREATED).body(this.postService.getUsersPost(userId));
	}
	
	@GetMapping("/all")
	public ResponseEntity<Object> getUsersPost(){
		return ResponseEntity.status(HttpStatus.CREATED).body(this.postService.getAllPost());
	}
	
	public ResponseEntity<Object> fallback(Post post,long userId, Throwable ex) {
        // decide how to fail: 503, cached data, or just ‘false’
        return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body("UserService is Down ....! ");
    }
}
