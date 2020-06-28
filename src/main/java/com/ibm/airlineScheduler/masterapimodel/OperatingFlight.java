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
	@JsonProperty("departureInfo")
	private DepartureInfo departureInfo;
	@JsonProperty("arrivalInfo")
	private ArrivalInfo arrivalInfo;
	@JsonProperty("flightLegs")
	private List<FlightLegOperatingFlight> flightLegs;
	
	@JsonProperty("assignedEmployees")
	@OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinProperty(name="assignedEmployees")
	private List<Employee> assignedEmployees;
	
	@JsonProperty("assignedPassengers")
	@OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinProperty(name="assignedPassengers")
	private List<Passenger> assignedPassengers;
	
	private String destination;
	
	
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
	public List<Employee> getAssignedEmployees() {
		return assignedEmployees;
	}
	public void setAssignedEmployees(List<Employee> assignedEmployees) {
		this.assignedEmployees = assignedEmployees;
	}
	public List<Passenger> getAssignedPassengers() {
		return assignedPassengers;
	}
	public void setAssignedPassengers(List<Passenger> assignedPassengers) {
		this.assignedPassengers = assignedPassengers;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	
	
}
