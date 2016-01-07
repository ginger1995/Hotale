package com.hotale.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.t.R;
import com.hotale.utils.HotaleApplication;

public class OrderInfoActivity extends Activity {
	
	Intent orderdetailintent;
	TextView txt_username;
	TextView txt_realname;
	TextView txt_hotelname;
	TextView txt_hoteladd;
	TextView txt_hoteltel;
	TextView txt_indate;
	TextView txt_outdate;
	TextView txt_ordertime;
	TextView txt_totalprice;
	TextView txt_orderstatus;
	TextView txt_commentstatus;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_info);
		
		txt_username = (TextView) this.findViewById(R.id.orderinfo_username);
		txt_realname = (TextView) this.findViewById(R.id.orderinfo_realname);
		txt_hotelname = (TextView) this.findViewById(R.id.orderinfo_hotelname);
		txt_hoteladd = (TextView) this.findViewById(R.id.orderinfo_hoteladd);
		txt_hoteltel = (TextView) this.findViewById(R.id.orderinfo_hoteltel);
		txt_indate = (TextView) this.findViewById(R.id.orderinfo_indate);
		txt_outdate = (TextView) this.findViewById(R.id.orderinfo_outdate);
		txt_ordertime = (TextView) this.findViewById(R.id.orderinfo_orderdate);
		txt_totalprice = (TextView) this.findViewById(R.id.orderinfo_price);
		txt_orderstatus = (TextView) this.findViewById(R.id.orderinfo_status);
		txt_commentstatus = (TextView) this.findViewById(R.id.orderinfo_comment);
		orderdetailintent = getIntent();
		
		txt_username.setText("用户名："+((HotaleApplication) getApplication()).getUserLogined().getUser_name());
		txt_realname.setText("姓    名："+((HotaleApplication) getApplication()).getUserLogined().getUser_real_name());
		txt_hotelname.setText("酒   店："+ orderdetailintent.getExtras().get("hotelname"));
		txt_hoteladd.setText("酒店地址："+orderdetailintent.getExtras().get("hoteladd"));
		txt_hoteltel.setText("酒店电话:"+orderdetailintent.getExtras().get("hoteltel"));
		txt_indate.setText("入住日期："+orderdetailintent.getExtras().get("indate"));
		txt_outdate.setText("离店日期："+orderdetailintent.getExtras().get("outdate"));
		txt_ordertime.setText("下单时间："+orderdetailintent.getExtras().get("ordertime"));
		txt_totalprice.setText("订单总价："+orderdetailintent.getExtras().get("totalprice")+"元");
		if(orderdetailintent.getExtras().getInt("commentstatus")==0){
			txt_commentstatus.setText("评价状态：未评价");
		}else{
			txt_commentstatus.setText("评价状态：已评价");
		}
		switch (orderdetailintent.getExtras().getInt("orderstatus")) {
		case 1:
			txt_orderstatus.setText("订单状态：未支付");
			break;
		case 2:
			txt_orderstatus.setText("订单状态：已支付");
			break;
		case 3:
			txt_orderstatus.setText("订单状态：已完成");
			break;
		case 4:
			txt_orderstatus.setText("订单状态：退款中");
			break;
		case 5:
			txt_orderstatus.setText("订单状态：已取消");
			break;
		case 6:
			txt_orderstatus.setText("订单状态：已过期");
			break;
		default:
			break;
			
		}
	}
	
	public void tocomment(View v) {
		if(orderdetailintent.getExtras().getInt("orderstatus")==3){
			startActivity(new Intent(getApplication(), CommentActivity.class));
		}
		else{
			Toast.makeText(getApplication(), "订单未完成，暂不能评价！", Toast.LENGTH_SHORT).show();
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.order_info, menu);
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
