package com.sosgps.wzt.orm;

import java.util.Date;

/**
 * AbstractTMutiMedium generated by MyEclipse Persistence Tools
 */

public abstract class AbstractTMutiMedium implements java.io.Serializable {

	// Fields

	private Long id;

	private String deviceId;

	private String image;

	private Float lantitude;

	private Float longitude;

	private String jmx;

	private String jmy;

	private Date uploadTime;

	private String imgType;

	// Constructors

	/** default constructor */
	public AbstractTMutiMedium() {
	}

	/** full constructor */
	public AbstractTMutiMedium(String deviceId, String image, Float lantitude,
			Float longitude, String jmx, String jmy, Date uploadTime,
			String imgType) {
		this.deviceId = deviceId;
		this.image = image;
		this.lantitude = lantitude;
		this.longitude = longitude;
		this.jmx = jmx;
		this.jmy = jmy;
		this.uploadTime = uploadTime;
		this.imgType = imgType;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDeviceId() {
		return this.deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Float getLantitude() {
		return this.lantitude;
	}

	public void setLantitude(Float lantitude) {
		this.lantitude = lantitude;
	}

	public Float getLongitude() {
		return this.longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	public String getJmx() {
		return this.jmx;
	}

	public void setJmx(String jmx) {
		this.jmx = jmx;
	}

	public String getJmy() {
		return this.jmy;
	}

	public void setJmy(String jmy) {
		this.jmy = jmy;
	}

	public Date getUploadTime() {
		return this.uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getImgType() {
		return this.imgType;
	}

	public void setImgType(String imgType) {
		this.imgType = imgType;
	}

}