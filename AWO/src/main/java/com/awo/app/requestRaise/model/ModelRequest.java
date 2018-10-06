package com.awo.app.requestRaise.model;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.awo.app.requestRaise.domain.Status;

public class ModelRequest {
	
	private String reqRId;
	
	private String patientId;
	
	private String requesterName;
	
	private String patientName;
	
	private String mobile;
	
	private Status status;
	
	private String reason;
	
	private String relationship;
	
	private String bloodGroup;
	
	private String numberOfUnits;
	
	private  String bloodType;
	
	private String hospitalName;
	
	private String area;
	
	private String city;
	
	private String dist;
	
	private String state;
	
	private String pincode;
	
	private String ReqDateAndTime;

	

	public String getReqRId() {
		return reqRId;
	}

	public void setReqRId(String reqRId) {
		this.reqRId = reqRId;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getRequesterName() {
		return requesterName;
	}

	public void setRequesterName(String requesterName) {
		this.requesterName = requesterName;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getNumberOfUnits() {
		return numberOfUnits;
	}

	public void setNumberOfUnits(String numberOfUnits) {
		this.numberOfUnits = numberOfUnits;
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDist() {
		return dist;
	}

	public void setDist(String dist) {
		this.dist = dist;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getReqDateAndTime() {
		return ReqDateAndTime;
	}

	public void setReqDateAndTime(String reqDateAndTime) {
		ReqDateAndTime = reqDateAndTime;
	}


	
}
