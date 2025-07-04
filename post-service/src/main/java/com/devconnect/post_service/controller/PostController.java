package com.devconnect.post_service.controller;

import java.util.List;

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
import com.devconnect.post_service.service.PostService;

@RestController
@RequestMapping("/posts")
public class PostController {

	private PostService postService;

	public PostController(PostService postService) {
		super();
		this.postService = postService;
	}
	
	@PostMapping("/create")
	public boolean createPost(@RequestBody Post post,@RequestParam("userId")long userId) {
		return this.postService.createPost(post,userId);
	}
	
	@PutMapping("/update/{postId}")
	public Post updatePost(@PathVariable long postId,@RequestBody Post post, @RequestParam("userId")long userId) {
		return this.postService.updatePost(postId,post,userId);
	}
	
	@GetMapping("/{postId}")
	public Post getPost(@PathVariable long postId) {
		return this.postService.getPost(postId);
	}
	
	@DeleteMapping("/{postId}")
	public boolean deletePost(@PathVariable long postId, @RequestParam("userId") long userId) {
		return this.postService.deletePost(postId,userId);
	}
	
	@GetMapping("/user-post")
	public List<Post> getUsersPost(@RequestParam("userId")long userId){
		return this.postService.getUsersPost(userId);
	}
	
	@GetMapping("/all")
	public List<Post> getUsersPost(){
		return this.postService.getAllPost();
	}
}
