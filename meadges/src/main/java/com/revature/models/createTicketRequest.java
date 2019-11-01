package com.revature.models;

public class createTicketRequest {
	private String username;
	private String password;
	private double amount;
	private String type;
	private String description;
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
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public createTicketRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public createTicketRequest(String username, String password, double amount, String type, String description) {
		super();
		this.username = username;
		this.password = password;
		this.amount = amount;
		this.type = type;
		this.description = description;
	}
	
}
