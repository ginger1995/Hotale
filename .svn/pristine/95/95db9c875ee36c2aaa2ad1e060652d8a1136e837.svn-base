package com.hotale.activity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.t.R;
import com.hotale.entity.User;
import com.hotale.utils.HotaleApplication;
import com.hotale.utils.URLTool;

public class LoginActivity extends Activity {

	RelativeLayout layout;
	private EditText edtusername;
	private EditText edtpassword;
	String usernamelogined;
	int back_main_flag = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		// /在Android2.2以后必须添加以下代码,本应用采用的Android4.0+
		// 设置线程的策略
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
				.detectDiskReads().detectDiskWrites().detectNetwork()
				.penaltyLog().build());
		// 设置虚拟机的策略
		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
				.detectLeakedSqlLiteObjects().penaltyLog().penaltyDeath()
				.build());

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		try {
			back_main_flag = getIntent().getExtras().getInt("back_main_flag");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("back_main_flag=0");
			e.printStackTrace();
		}
		TextView tx1 = (TextView) this.findViewById(R.id.txt_register);
		tx1.setClickable(true);
		tx1.setOnClickListener(new TextViewClickListener());

		Button bt1 = (Button) this.findViewById(R.id.btn_login);
		bt1.setOnClickListener(new ButtonClickListener());

		edtusername = (EditText) this.findViewById(R.id.edt_username);
		edtpassword = (EditText) this.findViewById(R.id.edt_password);

	}

	// 设置登录按钮的点击事件
	private final class ButtonClickListener implements View.OnClickListener {
		public void onClick(View v) {
			String username = edtusername.getText().toString();
			String password = edtpassword.getText().toString();
			URLTool urlTool = new URLTool();// 实例化一个urlTool为接下来url注入做准备，以便传值给服务器端

			String strURL = "http://192.168.1.200:8080/Struts2.3.16.1Hibernate4.3.4Spring4.0.2/login.action";
			HttpPost httpPost = new HttpPost(strURL);

			List<NameValuePair> list = new ArrayList<NameValuePair>();
			BasicNameValuePair nvp1 = new BasicNameValuePair("username",
					username);
			BasicNameValuePair nvp2 = new BasicNameValuePair("password",
					password);
			list.add(nvp1);
			list.add(nvp2);

			try {
				httpPost.setEntity(new UrlEncodedFormEntity(list, HTTP.UTF_8));
				try {
					DefaultHttpClient httpclient = new DefaultHttpClient();
					HttpResponse response = httpclient.execute(httpPost);
					if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
						try {
							HttpEntity entity1 = response.getEntity();
							String userinfo = EntityUtils.toString(entity1,
									"utf-8");
							System.out.println(userinfo);
							List<User> listUser = new ArrayList<User>();
							listUser = JSON.parseArray(userinfo.trim(),
									User.class);// 把接收到的服务器端传来的json字符串转化为User实体

							JSONArray json = new JSONArray(userinfo);
							for (int i = 0; i < json.length(); i++) {
								JSONObject obj = (JSONObject) json.get(i);
								Toast.makeText(
										getApplication(),
										"欢迎你,"
												+ obj.get("user_name")
														.toString(),
										Toast.LENGTH_SHORT).show();
								((HotaleApplication) getApplication())
										.setUserLogined(listUser.get(i));
								System.out.println(listUser.get(i).getUser_id()
										+ listUser.get(i).getUser_name());
								/*
								 * if (((HotaleApplication)
								 * getApplication()).getUserLogined() != null) {
								 * Intent userinfointent = new
								 * Intent(getApplication
								 * (),UserInfoActivity.class);
								 * startActivity(userinfointent); }
								 */
								finish();
							}
						} catch (Exception e) {
							e.printStackTrace();
							Toast.makeText(getApplication(),
									"登录失败！请检查用户名密码或网络连接！", Toast.LENGTH_SHORT)
									.show();
						}
					} else {
						System.out.println("链接服务器失败，请检查网络！");
					}
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			// Map<String, String> params = new HashMap<String, String>();
			// params.put("username", username);
			// params.put("password", password);
			// System.out.println("finalhttp entered");
			// FinalHttp finalHttp = new FinalHttp();
			// finalHttp.post(urlTool.urlBuild("login", params),
			// new AjaxCallBack<String>() {
			// @Override
			// public void onSuccess(String t) {
			// System.out.println("finalhttp onSuccess entered");
			// List<User> listUser = new ArrayList<User>();
			// // TODO Auto-generated method stub
			// try {
			// listUser = JSON.parseArray(t.trim(), User.class);
			// System.out.println(t.trim());
			// usernamelogined = listUser.get(0)
			// .getUser_name();
			//
			// for (int i = 0; i < listUser.size(); i++) {
			// // listUserName.add(listUser.get(i).getUserName());
			// System.out.println("what final got： "
			// + listUser.get(i).getUser_name());
			// Toast.makeText(getApplication(),
			// "欢迎你，" + usernamelogined,
			// Toast.LENGTH_SHORT).show();
			// ((HotaleApplication)getApplication()).setUserLogined(listUser.get(i));
			// // Toast.makeText(getApplication(), "登陆成功",
			// // Toast.LENGTH_SHORT).show();
			// }
			// } catch (Exception e) {
			// // TODO Auto-generated catch block
			// System.out.println("fail to get json");
			// Toast.makeText(getApplication(), "账号或密码错误",
			// Toast.LENGTH_SHORT).show();
			// e.printStackTrace();
			// }
			// }
			// });

		}
	}

	@Override
	public void finish() {
		if (back_main_flag == 1) {
			if (((HotaleApplication) getApplication()).getUserLogined() == null) {
				startActivity(new Intent(getApplication(), MainActivity.class));
				back_main_flag = 0;
			} else {
				super.finish();
			}
		} else {
			super.finish();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	// 现在注册，跳转到注册界面
	private final class TextViewClickListener implements View.OnClickListener {
		public void onClick(View v) {
			startActivity(new Intent(getApplication(), RegisterActivity.class));
		}
	}

}
