package com.ibm.airlineScheduler.masterapimodel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Telephone {

	@JsonProperty("number")
	private String number;
	@JsonProperty("typeCode")
	private String typeCode;
	@JsonProperty("id")
	private String id;
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
}
