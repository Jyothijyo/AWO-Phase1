package com.awo.app.domain.registration;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.awo.app.domain.address.Address;
import com.awo.app.domain.authentication.Authentication;

@Entity
public class Registration implements Serializable {
	
	private static final long serialVersionUID = -285581429968804879L;
	@Id
	private String regId;
	
	private String firstName;
	
	private String middleName;
	
	private String lastName;
	
	private String dob;
	
	private String bloodGroup;
	
	private int age;	
	
	private String ima_iba;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Enumerated(EnumType.STRING)
	private Role role;
	
	private boolean isActive;
	
	private boolean availability;


	public Registration() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Registration(String regId, String firstName, String middleName, String lastName, String dob, String bloodGroup,
			int age, Gender gender, Role role, boolean isActive, String ima_iba) {
		super();
		this.regId = regId;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.dob = dob;
		this.bloodGroup = bloodGroup;
		this.age = age;
		this.gender = gender;
		this.role = role;
		this.isActive = isActive;

	}

	

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

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getIma_iba() {
		return ima_iba;
	}

	public void setIma_iba(String ima_iba) {
		this.ima_iba = ima_iba;
	}

	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}
	
	

	
	
	}
