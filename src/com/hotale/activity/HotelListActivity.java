package com.hotale.activity;

import java.util.ArrayList;
import java.util.Date;
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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.t.R;
import com.hotale.adapter.HotelListAdapter;
import com.hotale.entity.Hotel;
import com.hotale.entity.Roomtype;
import com.hotale.utils.URLTool;

public class HotelListActivity extends Activity {

	ListView hotellv;
	URLTool urlTool = new URLTool();// 实例化一个urlTool为接下来url注入做准备，以便传值给服务器端
	List<Hotel> listHotel = new ArrayList<Hotel>();
	List<List<Roomtype>> pricelist = new ArrayList<List<Roomtype>>();
	List<Roomtype> minpricelist = new ArrayList<Roomtype>();
	Map<String, String> pricelowest = new HashMap<String, String>();
	Map<String, String> itemindex = new HashMap<String, String>();
	Date indate;
	Date outdate;
	Date nowdate;
	String indatestr;
	String outdatestr;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hotel_list);
		Intent intent = getIntent();
		String regionid = intent.getExtras().getInt("regionid") + "";//获取上个界面传过来的region_id
		indate = (Date)intent.getExtras().get("indate");
		outdate = (Date)intent.getExtras().get("outdate");
		nowdate = (Date)intent.getExtras().get("nowdate");
		indatestr = intent.getExtras().getString("indatestr");
		outdatestr = intent.getExtras().getString("outdatestr");
		//System.out.println(indate+"!!!!!!!!!!!!"+outdate+"!!!!!!!!!!!"+nowdate);
		Map<String, String> params = new HashMap<String, String>();
		params.put("regionId", regionid);
		System.out.println("finalhttp entered");
		FinalHttp finalHttp = new FinalHttp();
		finalHttp.post(urlTool.urlBuild("findhotel", params),
				new AjaxCallBack<String>() {
					@Override
					public void onSuccess(String t) {
						System.out.println("finalhttp 1 onSuccess entered");
						try {
							listHotel = JSON.parseArray(t.trim(), Hotel.class);
							System.out.println(t.trim());
							for (int i = 0; i < listHotel.size(); i++) {
								System.out.println("what final got： "
										+ listHotel.get(i).getHotelName());
							}
						} catch (Exception e) {
							System.out.println("fail to get json");
							Toast.makeText(getApplication(), "获取酒店列表失败",
									Toast.LENGTH_SHORT).show();
							e.printStackTrace();
						}
						// 2
						Map<String, String> params2 = new HashMap<String, String>();
						FinalHttp finalHttp2 = new FinalHttp();
						if (listHotel == null) {
							System.out.println("listHotel==null");
						}
						System.out.println(listHotel.size() + "");
						for (int i = 0; i < listHotel.size(); i++) {
							final String currentHotelID = listHotel.get(i)
									.getHotelId().toString();
							itemindex.put(i + "", currentHotelID);
							System.out.println(" 2 for entered");

							params2.clear();
							params2.put("hotelId", listHotel.get(i)
									.getHotelId().toString());
							finalHttp2.post(
									urlTool.urlBuild("findprice", params2),
									new AjaxCallBack<String>() {
										@Override
										public void onSuccess(String t) {
											System.out
													.println("finalhttp 2 onSuccess entered");
											try {
												minpricelist = JSON.parseArray(
														t.trim(),
														Roomtype.class);
												if (minpricelist == null) {
													System.out
															.println("minpricelist==null");
												}
												System.out.println(t.trim());
												pricelowest
														.put(currentHotelID,
																minpricelist
																		.get(0)
																		.getRoomtypeOrigPrice()
																		.toString());

												for (int i = 0; i < minpricelist
														.size(); i++) {
													System.out
															.println("min price is： "
																	+ minpricelist
																			.get(0)
																			.getRoomtypeOrigPrice());

												}
												pricelist.add(minpricelist);
											} catch (Exception e) {
												System.out
														.println("fail to get json");
												Toast.makeText(
														getApplication(),
														"获取酒店列表失败",
														Toast.LENGTH_SHORT)
														.show();
												e.printStackTrace();
											}

											showhotellist();

										}
									});
						}
						// 2

					}
				});
		hotellv = (ListView) this.findViewById(R.id.list_hotels);
		hotellv.setOnItemClickListener(new ItemClickListener());
	}
	
	// listview中每一项的点击事件监听器
	private final class ItemClickListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			int hotelid = Integer.parseInt(itemindex.get(position + ""));
			System.out.println(hotelid + " is the current clicked hotel ID");
			Intent hoteldetailintent = new Intent(getApplication(),
					HotelDetailActivity.class);
			hoteldetailintent.putExtra("hotel_id", hotelid);
			hoteldetailintent.putExtra("indate", indate);
			hoteldetailintent.putExtra("outdate", outdate);
			hoteldetailintent.putExtra("nowdate", nowdate);
			hoteldetailintent.putExtra("indatestr", indatestr);
			hoteldetailintent.putExtra("outdatestr", outdatestr);
			startActivity(hoteldetailintent);
		}
	}

	private void showhotellist() {
		List<Map<String, Object>> hotellist = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < pricelist.size(); i++) {
			System.out.println(i + "boom-I");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("hotelimage", R.drawable.hotel_list_pic);
			map.put("hotelname", listHotel.get(i).getHotelName());// listHotel是接收服务器端传过来的酒店list，泛型是Hotel实体，hotellist是为适配器定制的为了显示listview的
			map.put("hotelrate", listHotel.get(i).getHotelRemark() + "/10分");
			map.put("minprice",
					pricelowest.get(listHotel.get(i).getHotelId().toString()));
			hotellist.add(map);
			System.out.println(i + "boom-III");
		}
		HotelListAdapter hta = new HotelListAdapter(this, hotellist,
				R.layout.hotel_list_item);
		hotellv.setAdapter(hta);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.hotel_list, menu);
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

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		listHotel.clear();
		pricelist.clear();
		minpricelist.clear();
		super.finish();
	}

}
