package com.ibm.airlineScheduler.masterapimodel;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;
@Document(collection = "airlines")
public class Airlines {

	@JsonProperty("_id")
	private String id;
	private String iATACode;
	private String iCAO;
	private String airline;
	private String region;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getiATACode() {
		return iATACode;
	}
	public void setiATACode(String iATACode) {
		this.iATACode = iATACode;
	}
	public String getiCAO() {
		return iCAO;
	}
	public void setiCAO(String iCAO) {
		this.iCAO = iCAO;
	}
	public String getAirline() {
		return airline;
	}
	public void setAirline(String airline) {
		this.airline = airline;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	
	
}
