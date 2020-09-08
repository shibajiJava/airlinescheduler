package com.ibm.airlineScheduler.masterapimodel;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;
@Document(collection = "pnrDetails")
public class PnrDetails {
	@JsonProperty("_id")
	@Id
	private String id;
	@JsonProperty("PassengerId")
	private String passId;
	@JsonProperty("AssignedPNR")
	private String pnrNumber;
	
	
	public String getPassId() {
		return passId;
	}
	public void setPassId(String passId) {
		this.passId = passId;
	}
	public String getPnrNumber() {
		return pnrNumber;
	}
	public void setPnrNumber(String pnrNumber) {
		this.pnrNumber = pnrNumber;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	

}
