package com.hotale.activity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.t.R;
import com.hotale.adapter.RoomTypeListAdapter;
import com.hotale.entity.Hotel;
import com.hotale.entity.Roomtype;
import com.hotale.entity.User;
import com.hotale.utils.HotaleApplication;
import com.hotale.utils.URLTool;

public class HotelDetailActivity extends Activity {

	ListView roomtypelv;
	EditText hoteltel;
	EditText hoteladd;
	URLTool urlTool = new URLTool();// ʵ����һ��urlToolΪ������urlע����׼�����Ա㴫ֵ����������
	List<Roomtype> roomtypelist = new ArrayList<Roomtype>();
	List<Hotel> hotellist = new ArrayList<Hotel>();
	Date indate;
	Date outdate;
	Date nowdate;
	String indatestr;
	String outdatestr;
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	RoomTypeListAdapter rta;//ʵ����������
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hotel_detail);
		//��ȡ�ý�����Ҫ��ʾ��ȫ���ؼ�
		roomtypelv = (ListView) this.findViewById(R.id.roomtype_list);
		hoteltel = (EditText) this.findViewById(R.id.hotelteltxt);	
		hoteladd = (EditText) this.findViewById(R.id.hoteladdtxt);
		
		final String hotelid=getIntent().getExtras().getInt("hotel_id")+"";//��ȡ�ϸ�ҳ�洫������hotelid�Ա��ڱ������в�ѯ�þƵ�ķ���
		indate=(Date)getIntent().getExtras().get("indate");
		outdate=(Date)getIntent().getExtras().get("outdate");
		nowdate=(Date)getIntent().getExtras().get("nowdate");
		indatestr = getIntent().getExtras().getString("indatestr");
		outdatestr = getIntent().getExtras().getString("outdatestr");
		//System.out.println(indate+"!!!!!!!!!!!!"+outdate+"!!!!!!!!!!!"+nowdate);
		Map<String, String> params = new HashMap<String, String>();
		params.put("hotelId", hotelid);
		System.out.println("finalhttp entered");
		FinalHttp finalHttp = new FinalHttp();
		finalHttp.post(urlTool.urlBuild("findprice", params),
				new AjaxCallBack<String>() {
					@Override
					public void onSuccess(String t) {
						System.out.println("finalhttp onSuccess entered");
						try{
							roomtypelist = JSON.parseArray(t.trim(), Roomtype.class);
							System.out.println(t.trim());
							for (int i = 0; i < roomtypelist.size(); i++) {
								System.out.println("what final got�� "
										+ roomtypelist.get(i).getRoomtypeName());
							}
						} catch (Exception e) {
							System.out.println("fail to get json");
							Toast.makeText(getApplication(), "��ȡ�����б�ʧ��",
									Toast.LENGTH_SHORT).show();
							e.printStackTrace();
						}
						//��ʾ�Ƶ�����ҳ�ĵ�ַ�͵绰
						Map<String, String> params1 = new HashMap<String, String>();
						params1.put("hotelId", hotelid);
						System.out.println("finalhttp1 entered");
						FinalHttp finalHttp1 = new FinalHttp();
						finalHttp1.post(urlTool.urlBuild("findhotel2", params1),
								new AjaxCallBack<String>() {
									@Override
									public void onSuccess(String t) {
										System.out.println("finalhttp onSuccess1 entered");
										try{
											hotellist = JSON.parseArray(t.trim(), Hotel.class);
											System.out.println(t.trim());
											for (int i = 0; i < hotellist.size(); i++) {
												System.out.println("what final got�� "
														+ hotellist.get(i).getHotelName());
												hoteltel.setText("�绰��"+hotellist.get(i).getHotelTel());
												hoteladd.setText("��ַ��"+hotellist.get(i).getHotelAddress());
											}
										} catch (Exception e) {
											System.out.println("fail to get json");
											Toast.makeText(getApplication(), "��ȡ�Ƶ��б�ʧ��",
													Toast.LENGTH_SHORT).show();
											e.printStackTrace();
										}
										showroomtypelist();
									}
						});
						//��ʾ�Ƶ�����ҳ�ĵ�ַ�͵绰						
					}
		});		
	}
	
	//��дonResume�����Ա��½��ִ��ʱ����¼�Ĵ���application���user��ӵ�
	@Override
	protected void onResume() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nowuser", ((HotaleApplication) getApplication()).getUserLogined());//���¸����洫��ǰ��¼���û�ʵ��
		for (int i = 0; i < list.size(); i++) {
			if ((User)(list.get(i).get("nowuser"))==null) {
				list.get(i).put("nowuser", ((HotaleApplication) getApplication()).getUserLogined());
			}
		}
		rta = new RoomTypeListAdapter(this, list, R.layout.roomtype_list_item);
		roomtypelv.setAdapter(rta);
		super.onResume();
	}
	
	public void showroomtypelist(){
		
		for (int i = 0; i < roomtypelist.size(); i++) {
			String roomtype_attr = "";
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("roomtype_name", roomtypelist.get(i).getRoomtypeName());
			map.put("roomtype_price", ((roomtypelist.get(i).getRoomtypeOrigPrice())*(roomtypelist.get(i).getRoomtypeDiscount()))*10/10+"Ԫ");
			map.put("roomtype_id", roomtypelist.get(i).getRoomtypeId().toString());
			map.put("roomtype",	roomtypelist.get(i));//���¸����洫��ǰitem�ķ���ʵ��
			map.put("hotel",hotellist.get(0));//���¸����洫��ǰ�ľƵ�ʵ��
			map.put("nowuser", ((HotaleApplication) getApplication()).getUserLogined());//���¸����洫��ǰ��¼���û�ʵ��
			map.put("indate", indate);
			map.put("outdate", outdate);
			map.put("nowdate", nowdate);
			map.put("indatestr", indatestr);
			map.put("outdatestr", outdatestr);
			//ƴ�ӷ������Ե��ַ���roomtype_attr
			roomtype_attr = roomtype_attr + "��ס"+roomtypelist.get(i).getRoomtypePeople()+"��.";
			if(roomtypelist.get(i).getRoomtypeInternet()==1){roomtype_attr = roomtype_attr + "�����.";}
			if(roomtypelist.get(i).getRoomtypeWifi()==1){roomtype_attr = roomtype_attr + "��wifi.";}
			if(roomtypelist.get(i).getRoomtypeBath()==1){roomtype_attr = roomtype_attr + "��ϴԡ.";}
			if(roomtypelist.get(i).getRoomtypeHotwat()==1){roomtype_attr = roomtype_attr + "����ˮ.";}
			if(roomtypelist.get(i).getRoomtypeBreakfast()==1){roomtype_attr = roomtype_attr + "�����.";}
			
			map.put("roomtype_attr", roomtype_attr);
			list.add(map);
		}
		rta = new RoomTypeListAdapter(this, list, R.layout.roomtype_list_item);
		roomtypelv.setAdapter(rta);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hotel_detail, menu);
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
