package com.ibm.airlineScheduler.masterapimodel;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "passenger")
public class Passenger {

	@JsonProperty("_id")
	@Id
	private String id;
	@JsonProperty("passengerId")
	private String passengerId;
	@JsonProperty("emailAddress")
	private String emailAddress;
	@JsonProperty("dateOfBirth")
	private String dateOfBirth;
	@JsonProperty("firstName")
	private String firstName;
	@JsonProperty("lastName")
	private String lastName;
	@JsonProperty("middleName")
	private String middleName;
	@JsonProperty("mobileNumber")
	private String mobileNumber;
	@JsonProperty("title")
	private String title;
	@JsonProperty("contactInfo")
	private ContactInfo contactInfo;
	
	@JsonProperty("pnr")
	private String pnr;
	
	@JsonProperty("seatNumber")
	private String seatNumber;
	
	@JsonProperty("class")
	private  String pclass; 
	

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
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public ContactInfo getContactInfo() {
		return contactInfo;
	}
	public void setContactInfo(ContactInfo contactInfo) {
		this.contactInfo = contactInfo;
	}


	public String getPnr() {
		return pnr;
	}


	public void setPnr(String pnr) {
		this.pnr = pnr;
	}


	public String getSeatNumber() {
		return seatNumber;
	}


	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}


	public String getPclass() {
		return pclass;
	}


	public void setPclass(String pclass) {
		this.pclass = pclass;
	}
	
	
	
}
