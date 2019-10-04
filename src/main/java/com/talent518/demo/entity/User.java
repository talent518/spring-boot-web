package com.talent518.demo.entity;

import java.util.Date;

public class User {
	private int uid;
	private String username;
	private String password;
	private String salt;
	private String email;
	private Date registerTime;
	private Date loginTime;
	private int tryLoginTimes;

	public int getId() {
		return uid;
	}

	public void setId(int id) {
		this.uid = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public int getTryLoginTimes() {
		return tryLoginTimes;
	}

	public void setTryLoginTimes(int tryLoginTimes) {
		this.tryLoginTimes = tryLoginTimes;
	}

	@Override
	public String toString() {
		return "User{id: " + uid + ", username: " + username + ", password: " + password + ", salt: " + salt + ", email: " + email + ", registerTime: " + registerTime + ", loginTime: " + loginTime + ", tryLoginTimes: " + tryLoginTimes + "}";
	}
}
