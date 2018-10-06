package com.awo.app.domain.image;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import com.awo.app.domain.registration.Registration;

@Entity
public class Image implements Serializable {

	private static final long serialVersionUID = -6473532570200791535L;

	@Id
	private String imgId;

	private String imgName;

	private String type;

	
	@Lob
	private byte[] pic;

	private String createdDate;

	private String modifiedDate;
	
	private boolean isActive;
	
/*	@OneToOne
	@JoinColumn(name="regId")
	private Registration registration;

*/
	private String regId;
	
	public void setImgId(String imgId) {
		this.imgId = imgId;
	}
	
	public String getImgId() {
		return imgId;
	}

	public Image() {
		super();
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getPic() {
		return pic;
	}

	public void setPic(byte[] pic) {
		this.pic = pic;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	/*public Registration getRegistration() {
		return registration;
	}

	public void setRegistration(Registration registration) {
		this.registration = registration;
	}*/

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	
	}
