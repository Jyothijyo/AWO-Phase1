package com.awo.app.requestRaise.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
public class RequestRaise  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 874568719432410123L;

	@Id
	private String reqRId;
	
	private String patientId;
	
	private String requesterName;
	
	private String patientName;
	
	private String mobile;
	
	@Enumerated(EnumType.STRING)
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
	
	

	
	public RequestRaise() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RequestRaise(String reqRId, String patientId, String requesterName, String patientName, String mobile,
			Status status, String reason, String relationship, String bloodGroup, String numberOfUnits,
			String bloodType, String hospitalName, String area, String city, String dist, String state, String pincode,
			String reqDateAndTime) {
		super();
		this.reqRId = reqRId;
		this.patientId = patientId;
		this.requesterName = requesterName;
		this.patientName = patientName;
		this.mobile = mobile;
		this.status = status;
		this.reason = reason;
		this.relationship = relationship;
		this.bloodGroup = bloodGroup;
		this.numberOfUnits = numberOfUnits;
		this.bloodType = bloodType;
		this.hospitalName = hospitalName;
		this.area = area;
		this.city = city;
		this.dist = dist;
		this.state = state;
		this.pincode = pincode;
		ReqDateAndTime = reqDateAndTime;
	}

	public String getReqDateAndTime() {
		return ReqDateAndTime;
	}

	public void setReqDateAndTime(String reqDateAndTime) {
		ReqDateAndTime = reqDateAndTime;
	}

	
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
	
		
	
}

