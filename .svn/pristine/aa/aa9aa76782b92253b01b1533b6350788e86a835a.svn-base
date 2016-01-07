package com.hotale.adapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.sax.StartElementListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.t.R;
import com.hotale.activity.HotelDetailActivity;
import com.hotale.activity.LoginActivity;
import com.hotale.activity.OrderListActivity;
import com.hotale.entity.Hotel;
import com.hotale.entity.Order;
import com.hotale.entity.Roomtype;
import com.hotale.entity.User;
import com.hotale.utils.URLTool;

public class RoomTypeListAdapter extends BaseAdapter {

	private Context context; // 运行上下文
	private int resource;
	private List<Map<String, Object>> listItems; // 房型信息集合
	private LayoutInflater listContainer; // 视图容器
	URLTool urlTool = new URLTool();// 实例化一个urlTool为接下来url注入做准备，以便传值给服务器端
	List<Order> listOrder = new ArrayList<Order>();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddhhmmss");
	String indateforserver;
	String outdateforserver;
	String nowtimeforserver;

	// 自定义控件集合
	private TextView roomtype_name;
	private TextView roomtype_attr;
	private TextView roomtype_price;
	private Button oktoorder;

	public RoomTypeListAdapter(Context context,
			List<Map<String, Object>> listItems, int resource) {
		this.context = context;
		this.resource = resource;
		// listContainer = LayoutInflater.from(context); // 创建视图容器并设置上下文
		this.listItems = listItems;
		listContainer = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public int getCount() {
		return listItems.size();
	}

	public Object getItem(int position) {
		return listItems.get(position);
	}

	public long getItemId(int listContainer) {
		return listContainer;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = listContainer.inflate(resource, null);
		}
		// 获取每个item中的控件对象
		roomtype_name = (TextView) convertView.findViewById(R.id.roomtype_name);
		roomtype_price = (TextView) convertView
				.findViewById(R.id.roomtype_price);
		roomtype_attr = (TextView) convertView.findViewById(R.id.roomtype_attr);
		oktoorder = (Button) convertView.findViewById(R.id.oktoorder);

		// 设置文字,实现数据和组件的绑定
		roomtype_name.setText((String) listItems.get(position).get(
				"roomtype_name"));
		roomtype_price.setText((String) listItems.get(position).get(
				"roomtype_price"));
		roomtype_attr.setText((String) listItems.get(position).get(
				"roomtype_attr"));
		oktoorder.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {// 点击每个“订”按钮后先判断是否已登录
				if (((User) listItems.get(position).get("nowuser")) == null) {
					Toast.makeText(context, "请您登录", Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(context, LoginActivity.class);
					((HotelDetailActivity) context).startActivity(intent);// 若未登录则跳转到登录页
				} else {
					// 点击每个item的按钮后显示对话框弹窗是否确认下单
					System.out
							.println(listItems.get(position).get("indatestr"));
					System.out.println(listItems.get(position)
							.get("outdatestr"));
					System.out.println(listItems.get(position).get("nowdate"));
					indateforserver = sdf.format(listItems.get(position).get(
							"indate"));
					outdateforserver = sdf.format(listItems.get(position).get(
							"outdate"));
					nowtimeforserver = sdf1.format(listItems.get(position).get(
							"nowdate"));
					System.out.println(indateforserver);
					System.out.println(outdateforserver);
					System.out.println(nowtimeforserver);
					AlertDialog.Builder builder = new AlertDialog.Builder(
							context);
					// 创建完后设置对话框的属性
					// 标题
					builder.setMessage(
							"请确认您的全部订单信息\n\n" + "预定人姓名："
									+ ((User) listItems.get(position).get(
											"nowuser")).getUser_real_name()
									+ "\n"
									+ "预订酒店："
									+ ((Hotel) listItems.get(position).get(
											"hotel")).getHotelName()
									+ "\n"
									+ "预订房型："
									+ ((Roomtype) listItems.get(position).get(
											"roomtype")).getRoomtypeName()
									+ "\n"
									+ "折扣："
									+ ((Roomtype) listItems.get(position).get(
											"roomtype")).getRoomtypeDiscount()
									+ "\n"
									+ "折后每日价："
									+ (((Roomtype) listItems.get(position).get(
											"roomtype")).getRoomtypeDiscount())
											* (((Roomtype) listItems.get(
													position).get("roomtype"))
													.getRoomtypeOrigPrice())
											* 10 / 10
									+ "元"
									+ "\n"
									+ "入住时间："
									+ listItems.get(position).get("indatestr")
									+ "\n"
									+ "离店时间："
									+ listItems.get(position).get("outdatestr")
									+ "\n"
									+ "联系电话："
									+ ((User) listItems.get(position).get(
											"nowuser")).getUser_tel())
							.setCancelable(true)
							// 设置第一个（取消）按钮的标签及其事件监听器
							.setPositiveButton("取消",
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog, int id) {
											dialog.dismiss();
										}
									})
							// 设置第二个（确认下单）按钮的标签及其事件监听器
							.setNegativeButton("确认下单",
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog, int id) {
											Toast.makeText(context, "正在下单…请稍等…",
													Toast.LENGTH_LONG).show();
											// 向服务器端发送确认下单的数据
											Map<String, String> params = new HashMap<String, String>();
											params.put(
													"userId",
													((User) listItems.get(
															position).get(
															"nowuser"))
															.getUser_id() + "");// 发送userId
											params.put(
													"hotelId",
													((Hotel) listItems.get(
															position).get(
															"hotel"))
															.getHotelId() + "");// 发送hotelId
											params.put(
													"roomtypeId",
													((Roomtype) listItems.get(
															position).get(
															"roomtype"))
															.getRoomtypeId()
															+ "");// 发送roomtypeId
											params.put(
													"total",
													(((Roomtype) listItems.get(
															position).get(
															"roomtype"))
															.getRoomtypeDiscount())
															* (((Roomtype) listItems
																	.get(position)
																	.get("roomtype"))
																	.getRoomtypeOrigPrice())
															* 10 / 10 + "");// 发送折后总价
											params.put("checkInDate",
													indateforserver);
											params.put("checkOutDate",
													outdateforserver);
											params.put("orderTime",
													nowtimeforserver);
											System.out
													.println("finalhttp entered");
											FinalHttp finalHttp = new FinalHttp();
											finalHttp.post(urlTool.urlBuild(
													"neworder", params),
													new AjaxCallBack<String>() {
														@Override
														public void onSuccess(
																String t) {
															System.out
																	.println("finalhttp onSuccess entered");
															try {
																listOrder = JSON
																		.parseArray(
																				t.trim(),
																				Order.class);
																System.out
																		.println(t
																				.trim());
																Toast.makeText(
																		context,
																		"下单成功！",
																		Toast.LENGTH_SHORT)
																		.show();
																((HotelDetailActivity) context)
																		.startActivity(new Intent(
																				context,
																				OrderListActivity.class));// 登陆成功后跳转到我的订单页
															} catch (Exception e) {
																System.out
																		.println("fail to get json");
																Toast.makeText(
																		context,
																		"获取订单列表失败",
																		Toast.LENGTH_SHORT)
																		.show();
																e.printStackTrace();
															}
														}
													});
											// 向服务器端发送确认下单的数据(结束)
										}
									});
					// 用对话框构造器创建对话框
					AlertDialog alert = builder.create();
					// 显示对话框
					alert.show();
				}
			}
		});
		return convertView;
	}
}
