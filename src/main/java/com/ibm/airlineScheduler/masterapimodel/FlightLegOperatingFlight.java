package com.ibm.airlineScheduler.masterapimodel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FlightLegOperatingFlight {
	
	
	@JsonProperty("id")
	private String id;
	@JsonProperty("classification")
	private String classification;
	@JsonProperty("baggageCounts")
	private String baggageCounts;
	@JsonProperty("cabinDetails")
	private String cabinDetails;
	@JsonProperty("checkedBaggage")
	private String checkedBaggage;
	@JsonProperty("operationalStatusCode")
	private String operationalStatusCode;
	@JsonProperty("passengerCounts")
	private String passengerCounts;
	@JsonProperty("inboundConnections")
	private String inboundConnections;
	@JsonProperty("incomingCrewReport")
	private String incomingCrewReport;
	@JsonProperty("outboundConnections")
	private String outboundConnections;
	@JsonProperty("passengerCount")
	private Integer passengerCount;
	@JsonProperty("flightNumber")	
	private String flightNumber;
	@JsonProperty("aircraft")
	private Aircraft aircraft;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
	public String getBaggageCounts() {
		return baggageCounts;
	}
	public void setBaggageCounts(String baggageCounts) {
		this.baggageCounts = baggageCounts;
	}
	public String getCabinDetails() {
		return cabinDetails;
	}
	public void setCabinDetails(String cabinDetails) {
		this.cabinDetails = cabinDetails;
	}
	public String getCheckedBaggage() {
		return checkedBaggage;
	}
	public void setCheckedBaggage(String checkedBaggage) {
		this.checkedBaggage = checkedBaggage;
	}
	public String getOperationalStatusCode() {
		return operationalStatusCode;
	}
	public void setOperationalStatusCode(String operationalStatusCode) {
		this.operationalStatusCode = operationalStatusCode;
	}
	public String getPassengerCounts() {
		return passengerCounts;
	}
	public void setPassengerCounts(String passengerCounts) {
		this.passengerCounts = passengerCounts;
	}
	public String getInboundConnections() {
		return inboundConnections;
	}
	public void setInboundConnections(String inboundConnections) {
		this.inboundConnections = inboundConnections;
	}
	public String getIncomingCrewReport() {
		return incomingCrewReport;
	}
	public void setIncomingCrewReport(String incomingCrewReport) {
		this.incomingCrewReport = incomingCrewReport;
	}
	public String getOutboundConnections() {
		return outboundConnections;
	}
	public void setOutboundConnections(String outboundConnections) {
		this.outboundConnections = outboundConnections;
	}
	public Integer getPassengerCount() {
		return passengerCount;
	}
	public void setPassengerCount(Integer passengerCount) {
		this.passengerCount = passengerCount;
	}
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	public Aircraft getAircraft() {
		return aircraft;
	}
	public void setAircraft(Aircraft aircraft) {
		this.aircraft = aircraft;
	}
	
	
	
	
}
