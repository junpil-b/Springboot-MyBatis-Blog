package com.cos.blog.model.user;

import java.sql.Timestamp;

public class User {
	private int id;
	private String username;
	private String password;
	private String email;
	private String profile;
	private Timestamp createData;
	
	public User() {
		System.out.println("User:User() 호출");
	}
	
	public User(int id, String username, String password, String email, String profile, Timestamp createData) {
		System.out.println("User:User(ALL) 호출");
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.profile = profile;
		this.createData = createData;
	}
	
	public int getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getEmail() {
		return email;
	}
	public String getProfile() {
		return profile;
	}
	public Timestamp getCreateData() {
		return createData;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setUsername(String username) {
		System.out.println("User:setUsername() 호출");
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public void setCreateData(Timestamp createData) {
		this.createData = createData;
	}
	
	
	
}
	
