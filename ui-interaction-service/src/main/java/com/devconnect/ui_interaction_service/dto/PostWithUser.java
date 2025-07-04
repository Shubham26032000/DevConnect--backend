package com.devconnect.ui_interaction_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class PostWithUser {
	private Post post;
	private User user;

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public PostWithUser(Post post, User user) {
		super();
		this.post = post;
		this.user = user;
	}

	public PostWithUser() {
		super();
		// TODO Auto-generated constructor stub
	}

}
