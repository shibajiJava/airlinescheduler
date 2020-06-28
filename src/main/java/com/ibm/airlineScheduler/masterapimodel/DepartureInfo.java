package com.ibm.airlineScheduler.masterapimodel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DepartureInfo {

	@JsonProperty("id")
	private String id;
	@JsonProperty("airportId")
	private String airportId;
	@JsonProperty("estimatedDateTime")
	private String estimatedDateTime;
	@JsonProperty("scheduledDepartureTime")
	private String scheduledDepartureTime;
	@JsonProperty("gate")
	private String gate;
	@JsonProperty("gateStatus")
	private String gateStatus;
	@JsonProperty("terminal")
	private String terminal;
	@JsonProperty("scheduledDateTime")
	private String scheduledDateTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAirportId() {
		return airportId;
	}
	public void setAirportId(String airportId) {
		this.airportId = airportId;
	}
	public String getEstimatedDateTime() {
		return estimatedDateTime;
	}
	public void setEstimatedDateTime(String estimatedDateTime) {
		this.estimatedDateTime = estimatedDateTime;
	}
	public String getScheduledDepartureTime() {
		return scheduledDepartureTime;
	}
	public void setScheduledDepartureTime(String scheduledDepartureTime) {
		this.scheduledDepartureTime = scheduledDepartureTime;
	}
	public String getGate() {
		return gate;
	}
	public void setGate(String gate) {
		this.gate = gate;
	}
	public String getGateStatus() {
		return gateStatus;
	}
	public void setGateStatus(String gateStatus) {
		this.gateStatus = gateStatus;
	}
	public String getTerminal() {
		return terminal;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	public String getScheduledDateTime() {
		return scheduledDateTime;
	}
	public void setScheduledDateTime(String scheduledDateTime) {
		this.scheduledDateTime = scheduledDateTime;
	}
	
	
	
}
