package com.hotale.entity;

public class User {

	private int user_id;
	private String user_password;
	private String user_name;
	private String user_real_name;
	private String user_email;
	private String user_idcard;
	private String user_tel;
	private String user_wechat;
	private int user_credits;
	
	public User() {
		super();
	}

	public User(int user_id, String user_password, String user_name,
			String user_real_name, String user_email, String user_idcard,
			String user_tel, String user_wechat, int user_credits) {
		super();
		this.user_id = user_id;
		this.user_password = user_password;
		this.user_name = user_name;
		this.user_real_name = user_real_name;
		this.user_email = user_email;
		this.user_idcard = user_idcard;
		this.user_tel = user_tel;
		this.user_wechat = user_wechat;
		this.user_credits = user_credits;
	}
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_real_name() {
		return user_real_name;
	}
	public void setUser_real_name(String user_real_name) {
		this.user_real_name = user_real_name;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_idcard() {
		return user_idcard;
	}
	public void setUser_idcard(String user_idcard) {
		this.user_idcard = user_idcard;
	}
	public String getUser_tel() {
		return user_tel;
	}
	public void setUser_tel(String user_tel) {
		this.user_tel = user_tel;
	}
	public String getUser_wechat() {
		return user_wechat;
	}
	public void setUser_wechat(String user_wechat) {
		this.user_wechat = user_wechat;
	}
	public int getUser_credits() {
		return user_credits;
	}
	public void setUser_credits(int user_credits) {
		this.user_credits = user_credits;
	}
	
	
}
