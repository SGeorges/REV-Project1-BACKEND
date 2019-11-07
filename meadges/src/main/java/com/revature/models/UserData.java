package com.revature.models;

public class UserData {
	private int ers_users_id;
	private String ers_username;
	private String user_first_name;
	private String user_last_name;
	private String user_email;
	private String user_role;
	private int user_role_id;
	
	public String getUser_role() {
		return user_role;
	}
	public void setUser_role(String user_role) {
		this.user_role = user_role;
	}
	public int getErs_users_id() {
		return ers_users_id;
	}
	public void setErs_users_id(int ers_users_id) {
		this.ers_users_id = ers_users_id;
	}
	public String getErs_username() {
		return ers_username;
	}
	public void setErs_username(String ers_username) {
		this.ers_username = ers_username;
	}
	public String getUser_first_name() {
		return user_first_name;
	}
	public void setUser_first_name(String user_first_name) {
		this.user_first_name = user_first_name;
	}
	public String getUser_last_name() {
		return user_last_name;
	}
	public void setUser_last_name(String user_last_name) {
		this.user_last_name = user_last_name;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public int getUser_role_id() {
		return user_role_id;
	}
	public void setUser_role_id(int user_role_id) {
		this.user_role_id = user_role_id;
	}
	public UserData(User user) {
		this.ers_username=user.getErs_username();
		this.ers_users_id=user.getErs_users_id();
		this.user_email=user.getUser_email();
		this.user_first_name=user.getUser_first_name();
		this.user_last_name=user.getUser_last_name();
		this.user_role=user.getUser_role();
		this.user_role_id=user.getUser_role_id();
	}
	
	
}
