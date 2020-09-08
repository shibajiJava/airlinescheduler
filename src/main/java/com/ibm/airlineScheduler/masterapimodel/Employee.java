package com.ibm.airlineScheduler.masterapimodel;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;
@Document(collection = "emplyee")
public class Employee {
	
	@JsonProperty("_id")
	@Id
	private String id;
	@JsonProperty("employeeId")
	private String employeeId;
	@JsonProperty("typeCode")
	private String typeCode;
	@JsonProperty("firstName")
	private String firstName;
	@JsonProperty("lastName")
	private String lastName;
	@JsonProperty("photoUrl")
	private String photoUrl;
	@JsonProperty("contactInfo")
	private ContactInfo contactInfo;
	@JsonProperty("homeBaseId")
	private String homeBaseId;
	@JsonProperty("mobileNumber")
	private String mobileNumber;
	@JsonProperty("emailAddress")
	private String emailAddress;
	@JsonProperty("pairingNumber")
	private String pairingNumber;
	@JsonProperty("seniorityLevel")
	private String seniorityLevel;
	@JsonProperty("classType")
	private String classType;
	@JsonProperty("dutyTimeExpires")
	private String dutyTimeExpires;
	@JsonProperty("aircraftQualification")
	private String aircraftQualification;
	@JsonProperty("nextCertificationDate")
	private String nextCertificationDate;
	@JsonProperty("languages")
	private List<Language> languages;
	
	@JsonProperty("role")
	private String role;


	
	
	
	@JsonProperty("_id")
	public String getId() {
		return id;
	}
	
	@JsonProperty("_id")
	public void setId(String id) {
		this.id = id;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	public ContactInfo getContactInfo() {
		return contactInfo;
	}
	public void setContactInfo(ContactInfo contactInfo) {
		this.contactInfo = contactInfo;
	}
	public String getHomeBaseId() {
		return homeBaseId;
	}
	public void setHomeBaseId(String homeBaseId) {
		this.homeBaseId = homeBaseId;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getPairingNumber() {
		return pairingNumber;
	}
	public void setPairingNumber(String pairingNumber) {
		this.pairingNumber = pairingNumber;
	}
	public String getSeniorityLevel() {
		return seniorityLevel;
	}
	public void setSeniorityLevel(String seniorityLevel) {
		this.seniorityLevel = seniorityLevel;
	}
	public String getClassType() {
		return classType;
	}
	public void setClassType(String classType) {
		this.classType = classType;
	}
	public String getDutyTimeExpires() {
		return dutyTimeExpires;
	}
	public void setDutyTimeExpires(String dutyTimeExpires) {
		this.dutyTimeExpires = dutyTimeExpires;
	}
	public String getAircraftQualification() {
		return aircraftQualification;
	}
	public void setAircraftQualification(String aircraftQualification) {
		this.aircraftQualification = aircraftQualification;
	}
	public String getNextCertificationDate() {
		return nextCertificationDate;
	}
	public void setNextCertificationDate(String nextCertificationDate) {
		this.nextCertificationDate = nextCertificationDate;
	}
	public List<Language> getLanguages() {
		return languages;
	}
	public void setLanguages(List<Language> languages) {
		this.languages = languages;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	
	
	
	
}
