package com.ibm.airlineScheduler.masterapimodel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FlightLeg {
	@JsonProperty("noOfMarketingAirlineInfo")
	private String noOfMarketingAirlineInfo;
	@JsonProperty("noOfOperationalActivities")
	private String noOfOperationalActivities;
	@JsonProperty("onOfpassenger")
	private String onOfpassenger;
	@JsonProperty("cabinDetails")
	private String cabinDetails;
	@JsonProperty("checkedBaggage")
	private String checkedBaggage;
	@JsonProperty("noOfZones")
	private String noOfZones;
	@JsonProperty("aircraftId")
	private String aircraftId;
	@JsonProperty("departureInfo-airport")
	private String departureInfoAirport;
	@JsonProperty("arrivalInfo-airport")
	private String arrivalInfoAirport;
	
	
	public String getNoOfMarketingAirlineInfo() {
		return noOfMarketingAirlineInfo;
	}
	public void setNoOfMarketingAirlineInfo(String noOfMarketingAirlineInfo) {
		this.noOfMarketingAirlineInfo = noOfMarketingAirlineInfo;
	}
	public String getNoOfOperationalActivities() {
		return noOfOperationalActivities;
	}
	public void setNoOfOperationalActivities(String noOfOperationalActivities) {
		this.noOfOperationalActivities = noOfOperationalActivities;
	}
	public String getOnOfpassenger() {
		return onOfpassenger;
	}
	public void setOnOfpassenger(String onOfpassenger) {
		this.onOfpassenger = onOfpassenger;
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
	public String getNoOfZones() {
		return noOfZones;
	}
	public void setNoOfZones(String noOfZones) {
		this.noOfZones = noOfZones;
	}
	public String getAircraftId() {
		return aircraftId;
	}
	public void setAircraftId(String aircraftId) {
		this.aircraftId = aircraftId;
	}
	public String getDepartureInfoAirport() {
		return departureInfoAirport;
	}
	public void setDepartureInfoAirport(String departureInfoAirport) {
		this.departureInfoAirport = departureInfoAirport;
	}
	public String getArrivalInfoAirport() {
		return arrivalInfoAirport;
	}
	public void setArrivalInfoAirport(String arrivalInfoAirport) {
		this.arrivalInfoAirport = arrivalInfoAirport;
	}
	
	
}
