package com.hotale.entity;

public class Reserve {
	
	private int user_id;
	private String reserve_name;
	private String reserve_tel;
	
	public Reserve() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getReserve_name() {
		return reserve_name;
	}
	public void setReserve_name(String reserve_name) {
		this.reserve_name = reserve_name;
	}
	public String getReserve_tel() {
		return reserve_tel;
	}
	public void setReserve_tel(String reserve_tel) {
		this.reserve_tel = reserve_tel;
	}


}
