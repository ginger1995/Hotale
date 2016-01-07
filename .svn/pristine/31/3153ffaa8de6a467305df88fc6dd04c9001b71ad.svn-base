package com.hotale.service;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.hotale.entity.User;
import com.hotale.utils.StreamTool;

public class getUserService {

	public static List<User> getJSONUsers() throws Exception {
		System.out.println("enter json method");
		String path = "http://192.168.1.200:8080/Struts2.3.16.1Hibernate4.3.4Spring4.0.2/loginteacher.action";
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(50000);
		conn.setRequestMethod("GET");
		if (conn.getResponseCode() == 200) {
			InputStream inStream = conn.getInputStream();
			return parseJSON(inStream);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private static List<User> parseJSON(InputStream inStream) throws Exception {
		List<User> users = new ArrayList<User>();
		byte[] userdata = StreamTool.read(inStream);
		String userjson = new String(userdata);
		Map<String, Object> map = (Map<String, Object>) JSON.parse(userjson);
		if(map==null){
			System.out.println("null");
		}
		System.out.println(map.toString());
		// JSONArray array = new JSONArray(json);
		// for (int i = 0; i < array.length(); i++) {
		// JSONObject jsonObject = array.getJSONObject(i);
		// User user = new User(jsonObject.getInt("user_id"),
		// jsonObject.getString("user_password"),
		// jsonObject.getString("user_name"),
		// jsonObject.getString("user_real_name"),
		// jsonObject.getString("user_email"),
		// jsonObject.getString("user_idcard"),
		// jsonObject.getString("user_tel"),
		// jsonObject.getString("user_wechat"),
		// jsonObject.getInt("user_credits"));
		// users.add(user);
		// System.out.println(users.get(i).getUser_id());
		return users;
	}

}
