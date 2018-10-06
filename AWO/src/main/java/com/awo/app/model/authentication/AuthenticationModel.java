package com.awo.app.model.authentication;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

public class AuthenticationModel implements Serializable{
	
	
	private static final long serialVersionUID = -6075608316008558657L;

	private String password;
	
	private String hint;
	
	private Date recentLogin;
	
	private String url;
	
	private boolean isActive;

	
	private String regId;

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


	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getRecentLogin() {
		return recentLogin;
	}

	public void setRecentLogin(Date recentLogin) {
		this.recentLogin = recentLogin;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	
	}
