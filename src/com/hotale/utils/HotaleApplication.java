package com.hotale.utils;

import com.hotale.entity.User;

import android.app.Application;

public class HotaleApplication extends Application {
	User userLogined = null;
	
	public User getUserLogined() {
		return userLogined;
	}

	public void setUserLogined(User userLogined) {
		this.userLogined = userLogined;
	}
}
