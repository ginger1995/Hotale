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
import com.hotale.utils.HotaleApplication;
import com.hotale.utils.URLTool;

public class EditPasswordActivity extends Activity {
	
	EditText oldpwd;
	EditText newpwd;
	EditText newpwdagain;
	Button editpwd;
	String oldpass;
	String newpass;
	String newpassagain;
	URLTool urlTool = new URLTool();// 实例化一个urlTool为接下来url注入做准备，以便传值给服务器端
	List<User> userlist = new ArrayList<User>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_password);
		
		oldpwd = (EditText) this.findViewById(R.id.edt_oldpass);
		newpwd = (EditText) this.findViewById(R.id.edt_newpass);
		newpwdagain = (EditText) this.findViewById(R.id.edt_newpassagain);
		editpwd = (Button) this.findViewById(R.id.btn_edtpassword);
	}

	public void editpassword(View v) {
		oldpass = oldpwd.getText().toString();
		newpass = newpwd.getText().toString();
		newpassagain = newpwdagain.getText().toString();
		if(oldpass.equals("")||newpass.equals("")||newpassagain.equals("")){
			Toast.makeText(getApplication(), "请输入空缺项！", Toast.LENGTH_SHORT).show();
		}else if(!oldpass.equals(((HotaleApplication) getApplication()).getUserLogined().getUser_password())){
			Toast.makeText(getApplication(), "原密码不正确！", Toast.LENGTH_SHORT).show();
		}else if(!newpass.equals(newpassagain)){
			Toast.makeText(getApplication(), "两次输入的新密码不一致！", Toast.LENGTH_SHORT).show();
		}else{
			Map<String, String> params = new HashMap<String, String>();
			params.put("user_password", newpass);
			params.put("user_id", ((HotaleApplication) getApplication()).getUserLogined().getUser_id()+"");
			System.out.println("finalhttp entered");
			FinalHttp finalHttp = new FinalHttp();
			finalHttp.post(urlTool.urlBuild("updateuser", params),
					new AjaxCallBack<String>() {
						@Override
						public void onSuccess(String t) {
							System.out.println("finalhttp onSuccess entered");
							try{
								userlist = JSON.parseArray(t.trim(), User.class);
								System.out.println(t.trim());
								for (int i = 0; i < userlist.size(); i++) {
									System.out.println("what final got： "
											+ userlist.get(i).getUser_id());
									Toast.makeText(getApplication(), "修改成功！", Toast.LENGTH_SHORT).show();
									finish();
								}
							} catch (Exception e) {
								System.out.println("fail to get json");
								Toast.makeText(getApplication(), "修改失败",
										Toast.LENGTH_SHORT).show();
								e.printStackTrace();
							}
						}
			});
		}
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_password, menu);
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
