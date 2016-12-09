package org.example.jersey.messangerRestApp.model;

import java.util.Date;

public class Comment {
	private long id;
	private String message;
	private Date date;
	private String author;
	
	public Comment() {
		super();
	}
	
	public Comment(long id, String author, String message) {
		this.id = id;
		this.author = author;
		this.message = message;
		this.date = new Date();
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
}
