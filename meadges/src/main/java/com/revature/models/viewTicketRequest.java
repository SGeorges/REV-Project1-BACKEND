package com.revature.models;

public class viewTicketRequest {
	private String username;
	private String password;
	private int ticketID;
	
	public viewTicketRequest(String username, String password, int ticketID) {
		super();
		this.username = username;
		this.password = password;
		this.ticketID = ticketID;
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
	public int getTicketID() {
		return ticketID;
	}
	public void setTicketID(int ticketID) {
		this.ticketID = ticketID;
	}
	
}
