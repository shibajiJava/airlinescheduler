package com.ibm.airlineScheduler.kafkaModel;

public class PnrDetailsKafka {

	private String id;
	private String passengerId;
	private String assignedPNR;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassengerId() {
		return passengerId;
	}
	public void setPassengerId(String passengerId) {
		this.passengerId = passengerId;
	}
	public String getAssignedPNR() {
		return assignedPNR;
	}
	public void setAssignedPNR(String assignedPNR) {
		this.assignedPNR = assignedPNR;
	}
	
	
}
