package com.devconnect.ui_interaction_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devconnect.ui_interaction_service.client.PostClient;
import com.devconnect.ui_interaction_service.client.UserClient;
import com.devconnect.ui_interaction_service.dto.Post;
import com.devconnect.ui_interaction_service.dto.PostWithUser;
import com.devconnect.ui_interaction_service.dto.User;

@Service
public class UiService {
	
	@Autowired
	private UserClient userClient;
	
	@Autowired
	private PostClient postClient;

	public List<PostWithUser> getPostwithUser() {
		List<Post> posts = postClient.getUsersPost();
		if(posts != null) {
		return posts.stream().map(post -> {
				PostWithUser postWithUser = new PostWithUser();
				postWithUser.setPost(post);
				User user = getUser(post.getUserId());
				postWithUser.setUser(user);
				return postWithUser;
			}).toList();
		}
		
		return null;
	}

	private User getUser(long userId) {
		User user = userClient.getUser(userId);
		return user;
	}

}
