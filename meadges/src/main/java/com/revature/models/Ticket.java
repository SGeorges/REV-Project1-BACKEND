package com.revature.models;

import java.sql.Timestamp;

public class Ticket {
	private int reimb_id;
	private double reimb_amount;
	private String reimb_type_id;
	private String reimb_description;
	private int reimb_author_id;
	private String reimb_author_first_name;
	private String reimb_author_last_name;
	private int reimb_status_id;
	private int reimb_resolver_id;
	private String reimb_resolver_first_name;
	private String reimb_resolver_last_name;
	private String reimb_status;
	private String reimb_type;
	private String reimb_receipt;
	private Timestamp reimb_submitted;
	private Timestamp reimb_resolved;
	
	public Timestamp getReimb_submitted() {
		return reimb_submitted;
	}
	public void setReimb_submitted(Timestamp reimb_submitted) {
		this.reimb_submitted = reimb_submitted;
	}
	public Timestamp getReimb_resolved() {
		return reimb_resolved;
	}
	public void setReimb_resolved(Timestamp reimb_resolved) {
		this.reimb_resolved = reimb_resolved;
	}
	public String getReimb_receipt() {
		return reimb_receipt;
	}
	public void setReimb_receipt(String reimb_receipt) {
		this.reimb_receipt = reimb_receipt;
	}
	public int getReimb_id() {
		return reimb_id;
	}
	public void setReimb_id(int reimb_id) {
		this.reimb_id = reimb_id;
	}
	public double getReimb_amount() {
		return reimb_amount;
	}
	public void setReimb_amount(double reimb_amount) {
		this.reimb_amount = reimb_amount;
	}
	public String getReimb_type_id() {
		return reimb_type_id;
	}
	public void setReimb_type_id(String reimb_type_id) {
		this.reimb_type_id = reimb_type_id;
	}
	public String getReimb_description() {
		return reimb_description;
	}
	public void setReimb_description(String reimb_description) {
		this.reimb_description = reimb_description;
	}
	public int getReimb_author_id() {
		return reimb_author_id;
	}
	public void setReimb_author_id(int reimb_author_id) {
		this.reimb_author_id = reimb_author_id;
	}
	public String getReimb_author_first_name() {
		return reimb_author_first_name;
	}
	public void setReimb_author_first_name(String reimb_author_first_name) {
		this.reimb_author_first_name = reimb_author_first_name;
	}
	public String getReimb_author_last_name() {
		return reimb_author_last_name;
	}
	public void setReimb_author_last_name(String reimb_author_last_name) {
		this.reimb_author_last_name = reimb_author_last_name;
	}
	public int getReimb_status_id() {
		return reimb_status_id;
	}
	public void setReimb_status_id(int reimb_status_id) {
		this.reimb_status_id = reimb_status_id;
	}
	public int getReimb_resolver_id() {
		return reimb_resolver_id;
	}
	public void setReimb_resolver_id(int reimb_resolver_id) {
		this.reimb_resolver_id = reimb_resolver_id;
	}
	public String getReimb_resolver_first_name() {
		return reimb_resolver_first_name;
	}
	public void setReimb_resolver_first_name(String reimb_resolver_first_name) {
		this.reimb_resolver_first_name = reimb_resolver_first_name;
	}
	public String getReimb_resolver_last_name() {
		return reimb_resolver_last_name;
	}
	public void setReimb_resolver_last_name(String reimb_resolver_last_name) {
		this.reimb_resolver_last_name = reimb_resolver_last_name;
	}
	public String getReimb_status() {
		return reimb_status;
	}
	public void setReimb_status(String reimb_status) {
		this.reimb_status = reimb_status;
	}
	public String getReimb_type() {
		return reimb_type;
	}
	public void setReimb_type(String reimb_type) {
		this.reimb_type = reimb_type;
	}
	
	
		
}
