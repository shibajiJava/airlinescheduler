package com.ibm.airlineScheduler.masterapimodel;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.github.kaiso.relmongo.annotation.CascadeType;
import io.github.kaiso.relmongo.annotation.FetchType;
import io.github.kaiso.relmongo.annotation.JoinProperty;
import io.github.kaiso.relmongo.annotation.OneToMany;
@Document(collection = "operatingFlight")

public class OperatingFlight {


	@JsonProperty("_id")
	@Id
	private String id;
	@JsonProperty("carrierCode")
	private String carrierCode;
	
	@JsonProperty("mfClassName")
	private String mfClassName;
	@JsonProperty("flightNumber")
	private String flightNumber;
	@JsonProperty("boardingStatus")
	private String boardingStatus;
	@JsonProperty("departureInfo")
	private DepartureInfo departureInfo;
	@JsonProperty("arrivalInfo")
	private ArrivalInfo arrivalInfo;
	@JsonProperty("flightLegs")
	private List<FlightLegOperatingFlight> flightLegs;
	
	@JsonProperty("pnrAssignmentDetails")
	@OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinProperty(name="pnrNumber")
	private List<PnrDetails> pnrDetails;
	
	@JsonProperty("assignedEmployees")
	@OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinProperty(name="employeeId")
	private List<Employee> employee;
	
	@JsonProperty("assignedPassengers")
	@OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinProperty(name="passengerId")
	private List<Passenger> passenger;
	
	private String destination;
	private String scheduleSource;	
	
	
	public List<PnrDetails> getPnrDetails() {
		return pnrDetails;
	}
	public void setPnrDetails(List<PnrDetails> pnrDetails) {
		this.pnrDetails = pnrDetails;
	}
	public String getMfClassName() {
		return mfClassName;
	}
	public void setMfClassName(String mfClassName) {
		this.mfClassName = mfClassName;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCarrierCode() {
		return carrierCode;
	}
	public void setCarrierCode(String carrierCode) {
		this.carrierCode = carrierCode;
	}
	
	
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	public String getBoardingStatus() {
		return boardingStatus;
	}
	public void setBoardingStatus(String boardingStatus) {
		this.boardingStatus = boardingStatus;
	}
	public DepartureInfo getDepartureInfo() {
		return departureInfo;
	}
	public void setDepartureInfo(DepartureInfo departureInfo) {
		this.departureInfo = departureInfo;
	}
	public ArrivalInfo getArrivalInfo() {
		return arrivalInfo;
	}
	public void setArrivalInfo(ArrivalInfo arrivalInfo) {
		this.arrivalInfo = arrivalInfo;
	}
	public List<FlightLegOperatingFlight> getFlightLegs() {
		return flightLegs;
	}
	public void setFlightLegs(List<FlightLegOperatingFlight> flightLegs) {
		this.flightLegs = flightLegs;
	}
	
	public List<Employee> getEmployee() {
		return employee;
	}
	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
	}
	public List<Passenger> getPassenger() {
		return passenger;
	}
	public void setPassenger(List<Passenger> passenger) {
		this.passenger = passenger;
	}
	public String getScheduleSource() {
		return scheduleSource;
	}
	public void setScheduleSource(String scheduleSource) {
		this.scheduleSource = scheduleSource;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public void setSource(String scheduleSource) {
		this.scheduleSource = scheduleSource;
	}
	public String getSource() {
		return this.scheduleSource;
	}
}
