package com.devconnect.ui_interaction_service.dto;

public class Post {
	private long id;
	private String title;
	private String content;
	private long userId;
	public Post(long id, String title, String content, long userId) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.userId = userId;
	}

	public Post() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", content=" + content + ", userId=" + userId + "]";
	}

	
}
