package com.hotale.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.t.R;
import com.hotale.utils.HotaleApplication;

public class UserInfoActivity extends Activity {

	TextView infopage_username;
	TextView infopage_usercredits;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_info);

		infopage_username = (TextView) this
				.findViewById(R.id.infopage_username);
		infopage_usercredits = (TextView) this
				.findViewById(R.id.infopage_usercredits);
		// System.out.println(((HotaleApplication) getApplication())
		// .getUserLogined().getUser_name());
		// System.out.println(((HotaleApplication) getApplication())
		// .getUserLogined().getUser_credits());
		infopage_username.setText("用户名：   "
				+ ((HotaleApplication) getApplication()).getUserLogined()
						.getUser_name());
		infopage_usercredits.setText("积分：   "
				+ ((HotaleApplication) getApplication()).getUserLogined()
						.getUser_credits());
	}
	//修改密码
	public void editpassword(View v) {
		startActivity(new Intent(getApplication(), EditPasswordActivity.class));
	}

	//修改密码
		public void tomyorders(View v) {
			startActivity(new Intent(getApplication(), OrderListActivity.class));
		}
	
	// 点击退出登录按钮后执行的方法
	public void logout(View v) {
		((HotaleApplication) getApplication()).setUserLogined(null);
		startActivity(new Intent(getApplication(), LoginActivity.class));
		finish();
	}

	@Override
	public void finish() {
		startActivity(new Intent(getApplication(), MainActivity.class));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_info, menu);
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
}
