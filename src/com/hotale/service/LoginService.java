package com.hotale.service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hotale.entity.User;

public class LoginService {

	public static boolean login(String username, String password) {
		
		//getUserService userservice = new getUserService();
		
		String path = "http://192.168.1.200:8080/Struts2.3.16.1Hibernate4.3.4Spring4.0.2/login.action";//要请求的路径
		Map<String,String> params = new HashMap<String, String>();
		params.put("username", username);
		params.put("password", password);
		//用Get方式提交参数，需要把参数附加在路径后面
		
		try {
			//userservice.getJSONUsers();//用getuserservice()获取到用户
			sendGETRequest(path,params);//调用发送get请求的方法
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
    //向服务器发送get请求的方法
	private static boolean sendGETRequest(String path,Map<String, String> params) throws Exception{
		StringBuilder url = new StringBuilder(path);
		url.append("?");
		for (Map.Entry<String, String> entry: params.entrySet()) {
			url.append(entry.getKey()).append("=");
			url.append(entry.getValue());
			url.append("&");
		}
		url.deleteCharAt(url.length() - 1);
		HttpURLConnection conn = (HttpURLConnection)new URL(url.toString()).openConnection();
		conn.setConnectTimeout(50000);
		conn.setRequestMethod("GET");
		conn.setDoInput(true);
		if (conn.getResponseCode()==200) {
			//System.out.println("111111111111");
			return true;
		}
		return false;
	}

}
