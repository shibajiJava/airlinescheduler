package com.ibm.airlineScheduler.masterapimodel.dao;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ScheduleMasterRequest {

	@JsonProperty("scheduleMasterId")
	private String scheduleMasterId;
	
	@JsonProperty("scheduleInterval")
	private String scheduleInterval;
	
	@JsonProperty("scheduleSource")
	private String scheduleSource;
	
	@JsonProperty("interval")
	private String interval;
	
	@JsonProperty("scheduleDestination")
	private List<String> scheduleDestination;
	
	@JsonProperty("scheduleForFlight")
	private String scheduleForFlight;
	
	@JsonProperty("scheduleActiveStartDate")
	private String scheduleActiveStartDate;
	
	@JsonProperty("scheduleActiveEndDate")
	private String scheduleActiveEndDate;
	
	@JsonProperty("carrierCode")
	private String carrierCode;
	
	@JsonProperty("noOfPasenger")
	private String noOfPasenger;
	
	@JsonProperty("noOfPilot")
	private String noOfPilot;
	
	@JsonProperty("noOfCrew")
	private String noOfCrew;
	
	public String getScheduleInterval() {
		return scheduleInterval;
	}
	public void setScheduleInterval(String scheduleInterval) {
		this.scheduleInterval = scheduleInterval;
	}
	public String getScheduleSource() {
		return scheduleSource;
	}
	public void setScheduleSource(String scheduleSource) {
		this.scheduleSource = scheduleSource;
	}
	public List<String> getScheduleDestination() {
		return scheduleDestination;
	}
	public void setScheduleDestination(List<String> scheduleDestination) {
		this.scheduleDestination = scheduleDestination;
	}
	public String getScheduleForFlight() {
		return scheduleForFlight;
	}
	public void setScheduleForFlight(String scheduleForFlight) {
		this.scheduleForFlight = scheduleForFlight;
	}
	public String getScheduleActiveStartDate() {
		return scheduleActiveStartDate;
	}
	public void setScheduleActiveStartDate(String scheduleActiveStartDate) {
		this.scheduleActiveStartDate = scheduleActiveStartDate;
	}
	public String getScheduleActiveEndDate() {
		return scheduleActiveEndDate;
	}
	public void setScheduleActiveEndDate(String scheduleActiveEndDate) {
		this.scheduleActiveEndDate = scheduleActiveEndDate;
	}
	public String getCarrierCode() {
		return carrierCode;
	}
	public void setCarrierCode(String carrierCode) {
		this.carrierCode = carrierCode;
	}
	public String getInterval() {
		return interval;
	}
	public void setInterval(String interval) {
		this.interval = interval;
	}
	public String getNoOfPasenger() {
		return noOfPasenger;
	}
	public void setNoOfPasenger(String noOfPasenger) {
		this.noOfPasenger = noOfPasenger;
	}
	public String getNoOfPilot() {
		return noOfPilot;
	}
	public void setNoOfPilot(String noOfPilot) {
		this.noOfPilot = noOfPilot;
	}
	public String getNoOfCrew() {
		return noOfCrew;
	}
	public void setNoOfCrew(String noOfCrew) {
		this.noOfCrew = noOfCrew;
	}
	public String getScheduleMasterId() {
		return scheduleMasterId;
	}
	public void setScheduleMasterId(String scheduleMasterId) {
		this.scheduleMasterId = scheduleMasterId;
	}
	
	
	
	
	
	
}
