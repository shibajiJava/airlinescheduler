package com.ibm.airlineScheduler.masterapimodel.dao;

public class AirlineResponse {
	
	private String iATACode;
	private String airline;
	private String region;
	
	
	public String getiATACode() {
		return iATACode;
	}
	public void setiATACode(String iATACode) {
		this.iATACode = iATACode;
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
