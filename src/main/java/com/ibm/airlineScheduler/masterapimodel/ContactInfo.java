package com.ibm.airlineScheduler.masterapimodel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContactInfo {

	@JsonProperty("id")
	private String id;
	@JsonProperty("email")
	private String email;
	@JsonProperty("address")
	private Address address;
	@JsonProperty("telephone")
	private Telephone telephone;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Telephone getTelephone() {
		return telephone;
	}
	public void setTelephone(Telephone telephone) {
		this.telephone = telephone;
	}
	
	
}
