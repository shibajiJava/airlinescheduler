package com.ibm.airlineScheduler.masterapimodel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Address {

	@JsonProperty("id")
	private String id;
	@JsonProperty("address1")
	private String address1;
	@JsonProperty("address2")
	private String address2;
	@JsonProperty("city")
	private String city;
	@JsonProperty("country")
	private String country;
	@JsonProperty("postCode")
	private String postCode;
	@JsonProperty("stateProv")
	private String stateProv;
	@JsonProperty("typeCode")
	private String typeCode;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getStateProv() {
		return stateProv;
	}
	public void setStateProv(String stateProv) {
		this.stateProv = stateProv;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	
	
}
