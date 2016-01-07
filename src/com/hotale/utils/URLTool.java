package com.hotale.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import org.apache.http.client.utils.URLEncodedUtils;

public class URLTool {
	public String urlBuild(String path, Map<String, String> params) {
		String serverurl = "http://192.168.1.200:8080/Struts2.3.16.1Hibernate4.3.4Spring4.0.2/";

		StringBuilder url = new StringBuilder(serverurl);
		url.append(path);
		url.append(".action?");
		for (Map.Entry<String, String> entry : params.entrySet()) {
			try {
				url.append(URLEncoder.encode(entry.getKey(), "utf-8")).append(
						"=");
			} catch (UnsupportedEncodingException e) {
			}
			try {
				url.append(URLEncoder.encode(entry.getValue(), "utf-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			url.append("&");
		}
		url.deleteCharAt(url.length() - 1);
		System.out.println("Final URL is: "+url);
		return url.toString();
	}

}
