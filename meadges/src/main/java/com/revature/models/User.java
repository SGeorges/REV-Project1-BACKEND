package com.revature.models;

public class User {
	private String userName;
	private String password;
	private boolean isAdmin;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + ", isAdmin=" + isAdmin + "]";
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public User(String userName, String password, boolean isAdmin) {
		super();
		this.userName = userName;
		this.password = password;
		this.isAdmin = isAdmin;
	}
	
}
