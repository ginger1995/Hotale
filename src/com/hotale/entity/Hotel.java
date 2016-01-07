package com.hotale.entity;

public class Hotel {
	private Integer hotelId;
	private String hotelName;
	private Integer regionId;
	private String hotelAddress;
	private String hotelWebsite;
	private Integer hotelTel;
	private String hotelWechat;
	private String hotelPicture;
	private String hotelRemark;
	

	
	
	public Integer getHotelId() {
		return hotelId;
	}

	public void setHotelId(Integer hotelId) {
		this.hotelId = hotelId;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public String getHotelAddress() {
		return hotelAddress;
	}

	public void setHotelAddress(String hotelAddress) {
		this.hotelAddress = hotelAddress;
	}

	public String getHotelWebsite() {
		return hotelWebsite;
	}

	public void setHotelWebsite(String hotelWebsite) {
		this.hotelWebsite = hotelWebsite;
	}

	public Integer getHotelTel() {
		return hotelTel;
	}

	public void setHotelTel(Integer hotelTel) {
		this.hotelTel = hotelTel;
	}

	public String getHotelWechat() {
		return hotelWechat;
	}

	public void setHotelWechat(String hotelWechat) {
		this.hotelWechat = hotelWechat;
	}

	public String getHotelPicture() {
		return hotelPicture;
	}

	public void setHotelPicture(String hotelPicture) {
		this.hotelPicture = hotelPicture;
	}

	public String getHotelRemark() {
		return hotelRemark;
	}

	public void setHotelRemark(String hotelRemark) {
		this.hotelRemark = hotelRemark;
	}

	public Hotel() {
	}
	
	public Hotel(String hotelName, Integer regionId, String hotelAddress,
			Integer hotelTel) {
		this.hotelName = hotelName;
		this.regionId = regionId;
		this.hotelAddress = hotelAddress;
		this.hotelTel = hotelTel;
	}
	
	public Hotel(String hotelName, Integer regionId, String hotelAddress,
			String hotelWebsite, Integer hotelTel, String hotelWechat,
			String hotelPicture, String hotelRemark) {
		this.hotelName = hotelName;
		this.regionId = regionId;
		this.hotelAddress = hotelAddress;
		this.hotelWebsite = hotelWebsite;
		this.hotelTel = hotelTel;
		this.hotelWechat = hotelWechat;
		this.hotelPicture = hotelPicture;
		this.hotelRemark = hotelRemark;
	}
}
