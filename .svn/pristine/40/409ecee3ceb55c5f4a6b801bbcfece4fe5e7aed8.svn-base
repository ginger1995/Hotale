package com.hotale.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.t.R;
import com.hotale.entity.User;
import com.hotale.utils.RegexTool;
import com.hotale.utils.URLTool;

public class RegisterActivity extends Activity {

	EditText edtusername;
	EditText edtpassword;
	EditText edtpasswordagain;
	EditText edttel;
	EditText edtrealname;
	EditText edtidcard;
	EditText edtemail;
	String usernameregistered;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		edtusername = (EditText) this.findViewById(R.id.regedt_username);
		edtpassword = (EditText) this.findViewById(R.id.regedt_pwd);
		edtpasswordagain = (EditText) this.findViewById(R.id.regedt_pwdagain);
		edtrealname = (EditText) this.findViewById(R.id.regedt_realname);
		edttel = (EditText) this.findViewById(R.id.regedt_telnum);
		edtidcard = (EditText) this.findViewById(R.id.regedt_idcard);
		edtemail = (EditText) this.findViewById(R.id.regedt_email);

		Button btnsubmit = (Button) this.findViewById(R.id.regbtn_submit);
		btnsubmit.setOnClickListener(new ButtonClickListener());
	}

	// 注册提交按钮的点击事件
	private final class ButtonClickListener implements View.OnClickListener {
		public void onClick(View v) {
			String warn="";//设置警告字段
			String username = edtusername.getText().toString();
			String password = edtpassword.getText().toString();
			String passwordagain = edtpasswordagain.getText().toString();
			String tel = edttel.getText().toString();
			String realname = edtrealname.getText().toString();
			String idcard = edtidcard.getText().toString();
			String email = edtemail.getText().toString();
			//逐一验证注册输入项
			//if(RegexTool.isPassword(password)==false){warn=warn+"请输入6~16位合法密码!";}
			//if(RegexTool.isPassword(password)==true && password.equals(passwordagain)==false){warn=warn+"两次密码输入不一致!";}
			//if(RegexTool.isTel(tel)==false){warn=warn+"请输入合法手机号!";}
			//if(RegexTool.isRealname(realname)==false){warn=warn+"请输入合法中文姓名!";}
			//if(RegexTool.isIdcard(idcard)==false){warn=warn+"请输入合法身份证号码!";}
			//if(RegexTool.isEmail(email)==false){warn=warn+"请输入合法邮箱帐户!";}
			if(warn!=""){Toast.makeText(getApplication(), warn, Toast.LENGTH_LONG).show();}
			else{
				URLTool urlTool = new URLTool();// 实例化一个urlTool为接下来url注入做准备，以便传值给服务器端
				Map<String, String> params = new HashMap<String, String>();
				params.put("user_name", username);
				params.put("user_password", password);
				params.put("user_tel", tel);
				params.put("user_real_name", realname);
				params.put("user_idcard", idcard);
				params.put("user_email", email);
				System.out.println("finalhttp entered");
				FinalHttp finalHttp = new FinalHttp();
				finalHttp.post(urlTool.urlBuild("register", params),
						new AjaxCallBack<String>() {
							@Override
							public void onSuccess(String t) {
								System.out
										.println("finalhttp onSuccess entered");
								List<User> listUser = new ArrayList<User>();
								// TODO Auto-generated method stub
								try {
									listUser = JSON.parseArray(t.trim(),
											User.class);
									System.out.println(t.trim());
									usernameregistered = listUser.get(0)
											.getUser_name();

									for (int i = 0; i < listUser.size(); i++) {
										// listUserName.add(listUser.get(i).getUserName());
										System.out.println("what final got： "
												+ listUser.get(i)
														.getUser_name());
										Toast.makeText(getApplication(),
												"欢迎注册，" + usernameregistered,
												Toast.LENGTH_SHORT).show();
									}
								} catch (Exception e) {
									// TODO Auto-generated catch block
									System.out.println("fail to get json");
									Toast.makeText(getApplication(), "注册失败",
											Toast.LENGTH_SHORT).show();
									e.printStackTrace();
								}
							}
						});
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
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
