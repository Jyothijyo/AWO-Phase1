package com.awo.app.domain.authentication;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.awo.app.domain.registration.Registration;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Authentication implements Serializable{
	
	private static final long serialVersionUID = -7597310575920811369L;

	@Id
	private String authId;
	
	private String password;
	
	private String hint;
	
//	@Temporal(TemporalType.TIMESTAMP)
	private String recentLogin;
	
	private boolean isActive;
	
	private String regId;
	
	public Authentication() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Authentication(String authId, String password, String hint, String recentLogin, boolean isActive,
			String regId) {
		super();
		this.authId = authId;
		this.password = password;
		this.hint = hint;
		this.recentLogin = recentLogin;
		this.isActive = isActive;
		this.regId = regId;
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

	public String getRecentLogin() {
		return recentLogin;
	}

	public void setRecentLogin(String recentLogin) {
		this.recentLogin = recentLogin;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
