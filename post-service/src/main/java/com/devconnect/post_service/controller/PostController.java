package com.devconnect.post_service.controller;

import com.devconnect.post_service.dto.PostDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devconnect.post_service.entity.Post;
import com.devconnect.post_service.service.PostService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/posts")
public class PostController {

	private final PostService postService;
	
	@Value("${shubham}")
	private String s;

	public PostController(PostService postService) {
		super();
		this.postService = postService;
	}
	
	@PostMapping("/create" )
//	@CircuitBreaker(name="userServiceCB",fallbackMethod = "fallbackCreate")
	public ResponseEntity<?> createPost(@RequestParam long userId, @RequestPart("post") PostDto post, @RequestPart("image") MultipartFile image) {
		System.out.println("Arrived here");
        try {
           return ResponseEntity.status(HttpStatus.CREATED).body(this.postService.createPost(post,userId,image));
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

	@GetMapping("/user")
	public String ok(){
//		System.out.println(postDto.getContent());

		return "ok";
	}
	
	@PutMapping("/update/{postId}")
	@RateLimiter(name="postRateLimitor",fallbackMethod = "fallbackUpdateRateLimitor")
	@Retry(name="getUserService")
	@CircuitBreaker(name="userServiceCB2",fallbackMethod = "fallbackUpdate")
	public ResponseEntity<Object> updatePost(@PathVariable long postId,@RequestBody Post post, @RequestParam("userId")long userId) {
		System.out.println(s);
		return ResponseEntity.status(HttpStatus.CREATED).body(this.postService.updatePost(postId,post,userId));
	}
	
	@GetMapping("/{postId}")
	public ResponseEntity<Object> getPost(@PathVariable long postId) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.postService.getPost(postId));
	}
	
	@DeleteMapping("/{postId}")
	@CircuitBreaker(name="userServiceCBDelete",fallbackMethod = "userServiceCBDelete")
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

	@GetMapping("/{postId}/image")
	public ResponseEntity<Object> getPostImage(@PathVariable long postId){
		Post post = postService.findImage(postId);
		try {
			return ResponseEntity.ok()
					.contentType(MediaType.valueOf(post.getPostImageType()))
					.body(post.getPostImage());
		}catch (Exception e){
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	public ResponseEntity<Object> fallbackCreate(Post post,long userId, Throwable ex) {
        // decide how to fail: 503, cached data, or just ‘false’
        return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body("UserService is Down ....! ");
    }
	
	public ResponseEntity<Object> fallbackUpdate(long postId,Post post,long userId,Throwable th){
		return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body("UserService is Down ....! ");
	}
	
	public ResponseEntity<Object> fallbackUpdateRateLimitor(long postId,Post post,long userId,Throwable th){
		return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body("You have reach the limit of making number of calls please try after some time..! ");
	}
	
	public ResponseEntity<Object> userServiceCBDelete(long postId,long userId,Throwable th){
		return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body("UserService is Down ....! ");
	}
}
