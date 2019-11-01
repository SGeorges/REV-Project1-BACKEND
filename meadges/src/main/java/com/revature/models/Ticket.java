package com.revature.models;

public class Ticket {
	private int id;
	private double amount;
	private String type;
	private String description;
	private String username;
	private String status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Ticket(int id, double amount, String type, String description, String username, String status) {
		super();
		this.id = id;
		this.amount = amount;
		this.type = type;
		this.description = description;
		this.username = username;
		this.status = status;
	}
	
}
