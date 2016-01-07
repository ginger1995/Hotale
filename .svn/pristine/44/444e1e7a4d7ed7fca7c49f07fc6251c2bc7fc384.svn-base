package com.hotale.utils;

import java.util.regex.Pattern;

public class RegexTool {

	// 验证邮箱
	public static boolean isEmail(String str) {
		String regex = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		boolean flag = Pattern.matches(regex, str);
		return flag;
	}

	// 验证身份证号码
	public static boolean isIdcard(String str) {
		String regex = "(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])";
		boolean flag = Pattern.matches(regex, str);
		return flag;
	}

	// 验证手机号码
	public static boolean isTel(String str) {
		String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$";
		boolean flag = Pattern.matches(regex, str);
		return flag;
	}

	// 验证登陆密码(6~16位任意字符)
	public static boolean isPassword(String str) {
		String regex = "^[^\\s\u4e00-\u9fa5]{6,16}$";
		boolean flag = Pattern.matches(regex, str);
		return flag;
	}

	// 验证2~4位的中文姓名
	public static boolean isRealname(String str) {
		String regex = "[\u4e00-\u9fa5]{2,4}";
		boolean flag = Pattern.matches(regex, str);
		return flag;
	}
}
