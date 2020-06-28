package com.ibm.airlineScheduler.masterapimodel.dao;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FlightLegRequest {
	
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
}
