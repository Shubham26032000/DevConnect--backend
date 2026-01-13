package com.devconnect.post_service.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "posts")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String title;
	private String content;
	@Lob
	private byte[] postImage;
	private String postImageType;
	private String postImageName;
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

	public byte[] getPostImage() {
		return postImage;
	}

	public void setPostImage(byte[] postImage) {
		this.postImage = postImage;
	}

	public String getPostImageType() {
		return postImageType;
	}

	public void setPostImageType(String postImageType) {
		this.postImageType = postImageType;
	}

	public String getPostImageName() {
		return postImageName;
	}

	public void setPostImageName(String postImageName) {
		this.postImageName = postImageName;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", content=" + content + ", userId=" + userId + "]";
	}

}
