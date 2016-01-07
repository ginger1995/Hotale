package com.hotale.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.alibaba.fastjson.JSON;
import com.example.t.R;
import com.hotale.adapter.OrderListAdapter;
import com.hotale.entity.Hotel;
import com.hotale.entity.Order;
import com.hotale.entity.User;
import com.hotale.utils.HotaleApplication;
import com.hotale.utils.URLTool;

public class OrderListActivity extends Activity {

	User nowuser;
	URLTool urlTool = new URLTool();// 实例化一个urlTool为接下来url注入做准备，以便传值给服务器端
	List<Order> listOrder = new ArrayList<Order>();
	ListView orderlv;
	Map<String, Hotel> hotelIndexByOrderID = new HashMap<String, Hotel>();
	List<Hotel> hotellist = new ArrayList<Hotel>();
	int currentOrderID;
	List<Hotel> listhotel_one=new ArrayList<Hotel>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_list);
		orderlv = (ListView) this.findViewById(R.id.list_orders);	
		orderlv.setOnItemClickListener(new ItemClickListener());
	}
	
	// listview中每一项的点击事件监听器
		private final class ItemClickListener implements OnItemClickListener {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				
				//int orderid = (listOrder.get(position).getOrderId());
				//Hotel hotel=hotelIndexByOrderID.get(listOrder.get(position).getOrderId()+"");
				//Hotel hoteltest=(hotelIndexByOrderID.get(listOrder.get(position).getOrderId()+""));
				Order order=listOrder.get(position);
				//System.out.println(hotelid + " is the current clicked hotel ID");
				Intent orderdetailintent = new Intent(getApplication(),
						OrderInfoActivity.class);
                orderdetailintent.putExtra("hotelname", (hotelIndexByOrderID.get(listOrder.get(position).getOrderId()+"")).getHotelName());
              orderdetailintent.putExtra("hoteladd", (hotelIndexByOrderID.get(listOrder.get(position).getOrderId()+"")).getHotelAddress());
               orderdetailintent.putExtra("hoteltel", (hotelIndexByOrderID.get(listOrder.get(position).getOrderId()+"")).getHotelTel());
                orderdetailintent.putExtra("indate", order.getCheckInDate());
                orderdetailintent.putExtra("outdate", order.getCheckOutDate());
                orderdetailintent.putExtra("ordertime", order.getOrderTime());
                orderdetailintent.putExtra("totalprice", order.getTotal());
                orderdetailintent.putExtra("orderstatus", order.getStatusExecuteId());
                orderdetailintent.putExtra("commentstatus", order.getCommentOrNot());
				startActivity(orderdetailintent);
			}
		}

	@Override
	protected void onResume() {
		try {
			nowuser = ((HotaleApplication) getApplication()).getUserLogined();
			sendinfotoserver();			
			super.onResume();
		} catch (Exception e) {
			startActivity(new Intent(getApplication(), LoginActivity.class)
					.putExtra("back_main_flag", 1));
			super.onResume();
		}

	}

	private void sendinfotoserver() {
		int userid = nowuser.getUser_id();
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", userid + "");
		System.out.println("finalhttp entered");
		FinalHttp finalHttp = new FinalHttp();
		finalHttp.post(urlTool.urlBuild("findorder", params),
				new AjaxCallBack<String>() {
					@Override
					public void onSuccess(String t) {
						System.out.println("finalhttp onSuccess entered");
						try {
							listOrder = JSON.parseArray(t.trim(), Order.class);
							System.out.println(t.trim());
							for (int i = 0; i < listOrder.size(); i++) {
								currentOrderID = listOrder.get(i).getOrderId();
								final int ii=i;
								System.out.println("currentOrderID： "
										+ currentOrderID);
								// 2
								int hotelid = listOrder.get(i).getHotelId();
								Map<String, String> params1 = new HashMap<String, String>();
								params1.put("hotelId", hotelid + "");
								System.out.println("finalhttp entered");
								FinalHttp finalHttp1 = new FinalHttp();
								finalHttp1.post(
										urlTool.urlBuild("findhotel2", params1),
										new AjaxCallBack<String>() {
											@Override
											public void onSuccess(String t) {
												System.out
														.println("finalhttp1 onSuccess entered");
												try {
													listhotel_one = JSON.parseArray(
															t.trim(),
															Hotel.class);
													hotellist.add(listhotel_one.get(0));
													hotelIndexByOrderID.put(listOrder.get(ii).getOrderId()+"", listhotel_one.get(0));
													System.out
															.println(
																	 t.trim()
																	);
													
													if(ii == listOrder.size()-1){
													showorderlist();		
													/*Toast.makeText(
															getApplication(),
															"showorderlist() "
																	,
															Toast.LENGTH_SHORT)
															.show();*/
													//hotellist.clear();
													//listOrder.clear();
													//hotelIndexByOrderID.clear();
													}		
													
												} catch (Exception e) {
													System.out.println("Finalhttp1 has a exception whose name is "
															+ e.toString());
													/*Toast.makeText(
															getApplication(),
															"Finalhttp1 has a exception whose name is "
																	+ e.toString(),
															Toast.LENGTH_SHORT)
															.show();*/
													e.printStackTrace();
												}
												
											}
										});

								// 2
							}
						} catch (Exception e) {
							System.out.println("fail to get json");
							Toast.makeText(getApplication(), "获取用户订单列表失败",
									Toast.LENGTH_SHORT).show();
							e.printStackTrace();
						}
						
					}
				});
		
	}

	private void showorderlist() {
		List<Map<String, Object>> orderlist = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < listOrder.size(); i++) {
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("hotel", (hotelIndexByOrderID.get(listOrder.get(i).getOrderId()+"")).getHotelName());
			//map.put("hotel", hotellist.get(i).getHotelName());
			map.put("orderid", listOrder.get(i).getOrderId());
			map.put("ordertime", listOrder.get(i).getOrderTime());
			map.put("totalprice", listOrder.get(i).getTotal() + "元");
			if(listOrder.get(i).getStatusExecuteId()==1){map.put("orderstatus", "未支付");}
			else if(listOrder.get(i).getStatusExecuteId()==2){map.put("orderstatus", "已支付");}
			else if(listOrder.get(i).getStatusExecuteId()==3){map.put("orderstatus", "已完成");}
			else if(listOrder.get(i).getStatusExecuteId()==4){map.put("orderstatus", "退款中");}
			else if(listOrder.get(i).getStatusExecuteId()==5){map.put("orderstatus", "已取消");}
			else if(listOrder.get(i).getStatusExecuteId()==6){map.put("orderstatus", "已过期");}
			else{map.put("orderstatus", "未知");}
			orderlist.add(map);
		}
		OrderListAdapter ola = new OrderListAdapter(this, orderlist,
				R.layout.order_list_item);
		orderlv.setAdapter(ola);
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		//hotellist.clear();
		//listOrder.clear();
		super.finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.order_confirm, menu);
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
