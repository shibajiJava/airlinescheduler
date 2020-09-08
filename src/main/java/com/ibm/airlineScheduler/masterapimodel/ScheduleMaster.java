package com.ibm.airlineScheduler.masterapimodel;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.github.kaiso.relmongo.annotation.CascadeType;
import io.github.kaiso.relmongo.annotation.FetchType;
import io.github.kaiso.relmongo.annotation.JoinProperty;
import io.github.kaiso.relmongo.annotation.OneToMany;

@Document(collection = "scheduleMaster")
public class ScheduleMaster {
	
	
	 @Transient
	 public static final String SEQUENCE_NAME = "scheduler_sequence";
	
	 @Id
	@JsonProperty("_id")
	private String id;
	
	 @JsonProperty("datauploadstatus")
	 private String datauploadstatus;
	
	@JsonProperty("scheduleId")
	private String scheduleId;
	
	@JsonProperty("numberOfScheduler")
	private String noOfSchduler;

	@JsonProperty("scheduleSource")
	private String scheduleSource;
	
	
	@JsonProperty("scheduleDestination")
	@OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinProperty(name="scheduleDestination")
	private List<Airport> scheduleDestination;
	
	@JsonProperty("operatingFlight")
	@OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinProperty(name="operatingFlight")
	private List<OperatingFlight> operatingFlight;
	
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
	
	@JsonProperty("noOfCrew")
	private String noOfCrew;
	
	@JsonProperty("interval")
	private String interval;
	
	@JsonProperty("scheduleInterval")
	private String scheduleInterval;
	
	@JsonProperty("noOfPilot")
	private String noOfPilot;

	@JsonProperty("flightLegs")
	private List<FlightLeg> flightLegs;

	@JsonProperty("boardingStatus")
	private String boardingStatus;
	
	@JsonProperty("_id")
	public String getId() {
		return id;
	}
	
	@JsonProperty("_id")
	public void setId(String id) {
		this.id = id;
	}
	public String getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}

	public String getScheduleSource() {
		return scheduleSource;
	}
	public void setScheduleSource(String scheduleSource) {
		this.scheduleSource = scheduleSource;
	}
	public List<Airport> getScheduleDestination() {
		return scheduleDestination;
	}
	public void setScheduleDestination(List<Airport> scheduleDestination) {
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

	public List<FlightLeg> getFlightLegs() {
		return flightLegs;
	}
	public void setFlightLegs(List<FlightLeg> flightLegs) {
		this.flightLegs = flightLegs;
	}

	public String getNoOfSchduler() {
		return noOfSchduler;
	}

	public void setNoOfSchduler(String noOfSchduler) {
		this.noOfSchduler = noOfSchduler;
	}

	public List<OperatingFlight> getOperatingFlight() {
		return operatingFlight;
	}

	public void setOperatingFlight(List<OperatingFlight> operatingFlight) {
		this.operatingFlight = operatingFlight;
	}

	public String getInterval() {
		return interval;
	}

	public void setInterval(String interval) {
		this.interval = interval;
	}

	public String getNoOfPilot() {
		return noOfPilot;
	}

	public void setNoOfPilot(String noOfPilot) {
		this.noOfPilot = noOfPilot;
	}

	public String getScheduleInterval() {
		return scheduleInterval;
	}

	public void setScheduleInterval(String scheduleInterval) {
		this.scheduleInterval = scheduleInterval;
	}

	public String getNoOfPasenger() {
		return noOfPasenger;
	}

	public void setNoOfPasenger(String noOfPasenger) {
		this.noOfPasenger = noOfPasenger;
	}

	public String getNoOfCrew() {
		return noOfCrew;
	}

	public void setNoOfCrew(String noOfCrew) {
		this.noOfCrew = noOfCrew;
	}

	public String getDatauploadstatus() {
		return datauploadstatus;
	}

	public void setDatauploadstatus(String datauploadstatus) {
		this.datauploadstatus = datauploadstatus;
	}

	public void setBoardingStatus(String boardingStatus) {
		this.boardingStatus = boardingStatus;
	}

	public String getBoardingStatus() {
		return this.boardingStatus;
	}	
	
}
