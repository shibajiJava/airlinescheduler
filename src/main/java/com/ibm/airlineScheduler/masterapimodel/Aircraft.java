package com.ibm.airlineScheduler.masterapimodel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Aircraft {

	@JsonProperty("id")
	private String id;
	@JsonProperty("fin")
	private String fin;
	@JsonProperty("equipmentId")
	private Integer equipmentId;
	@JsonProperty("aircraftType")
	private String aircraftType;
	@JsonProperty("configuration")
	private String configuration;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFin() {
		return fin;
	}
	public void setFin(String fin) {
		this.fin = fin;
	}
	public Integer getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}
	public String getAircraftType() {
		return aircraftType;
	}
	public void setAircraftType(String aircraftType) {
		this.aircraftType = aircraftType;
	}
	public String getConfiguration() {
		return configuration;
	}
	public void setConfiguration(String configuration) {
		this.configuration = configuration;
	}
	
	
}
