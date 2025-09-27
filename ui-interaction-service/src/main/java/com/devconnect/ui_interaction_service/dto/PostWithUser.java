package com.devconnect.ui_interaction_service.dto;

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
