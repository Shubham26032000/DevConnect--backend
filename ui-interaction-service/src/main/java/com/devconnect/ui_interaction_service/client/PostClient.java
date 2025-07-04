package com.devconnect.ui_interaction_service.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.devconnect.ui_interaction_service.dto.Post;

@FeignClient(name = "post-client", url = "http://localhost:9999/posts")
public interface PostClient {
	@GetMapping("/{postId}")
	public Post getPost(@PathVariable long postId);

	@GetMapping("/all")
	public List<Post> getUsersPost();

	@GetMapping("/user-post")
	public List<Post> getUsersPost(@RequestParam("userId") long userId);
}
