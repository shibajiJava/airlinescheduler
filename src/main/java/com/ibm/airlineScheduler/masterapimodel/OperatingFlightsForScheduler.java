package com.ibm.airlineScheduler.masterapimodel;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.github.kaiso.relmongo.annotation.CascadeType;
import io.github.kaiso.relmongo.annotation.FetchType;
import io.github.kaiso.relmongo.annotation.JoinProperty;
import io.github.kaiso.relmongo.annotation.OneToMany;
@Document(collection = "operatingFlightsForScheduler")
public class OperatingFlightsForScheduler {
	
	@JsonProperty("_id")
	@Id
	private  String id;
	
	@JsonProperty("operatingFlight")
	@OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinProperty(name="operatingFlight")
	private List<OperatingFlight> operatingFlight;

	
	@JsonProperty("_id")
	public String getId() {
		return id;
	}

	@JsonProperty("_id")
	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("assignedEmployees")
	@OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinProperty(name="assignedEmployees")
	private List<Employee> assignedEmployees;
	
	public List<OperatingFlight> getOperatingFlight() {
		return operatingFlight;
	}

	public void setOperatingFlight(List<OperatingFlight> operatingFlight) {
		this.operatingFlight = operatingFlight;
	}

	public List<Employee> getAssignedEmployees() {
		return assignedEmployees;
	}

	public void setAssignedEmployees(List<Employee> assignedEmployees) {
		this.assignedEmployees = assignedEmployees;
	}

	
	
	

}
