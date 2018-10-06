  package com.awo.app.model.registration;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.awo.app.domain.registration.Gender;
import com.awo.app.domain.registration.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
@JsonInclude(Include.NON_DEFAULT)
public class RegistrationModel implements Serializable {

	private static final long serialVersionUID = -164331729043091013L;
	
	private String regId;

	private String firstName;

	private String middleName;

	private String lastName;

	private String dob;

	private String bloodGroup;

	private int age;
	
	private boolean availability;

	private Gender gender;

	private Role role;
	
	private String country;
	
	private String street;
	
	private String area;
	
	private String city;
	
	private String state;
	
	private String pincode;
	
	private String mobile;
	
	private String emailId;
	
	private String website;
	
//	@Temporal(TemporalType.TIMESTAMP)
	private String dateAndTime;
	
	private String modifiedDateAndTime;
	
	
//	private List<AddrModel> address;
	
	private String ima_iba;
	
	private String authId;
	
	private String password;
	
	private String hint;

	private String recentLogin;
	

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

/*	public List<AddrModel> getAddress() {
		return address;
	}

	public void setAddress(List<AddrModel> address) {
		this.address = address;
	}*/
	
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
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

	


	public String getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(String dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	



	public String getModifiedDateAndTime() {
		return modifiedDateAndTime;
	}
	public void setModifiedDateAndTime(String modifiedDateAndTime) {
		this.modifiedDateAndTime = modifiedDateAndTime;
	}
	
	

	public String getIma_iba() {
		return ima_iba;
	}

	public void setIma_iba(String ima_iba) {
		this.ima_iba = ima_iba;
	}
	
	

	

	public String getAuthId() {
		return authId;
	}

	public void setAuthId(String authId) {
		this.authId = authId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getRecentLogin() {
		return recentLogin;
	}

	public void setRecentLogin(String recentLogin) {
		this.recentLogin = recentLogin;
	}

	
	
	
	

	
}
