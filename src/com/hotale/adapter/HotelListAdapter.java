package com.hotale.adapter;

import java.util.List;
import java.util.Map;

import com.example.t.R;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

public class HotelListAdapter extends BaseAdapter {

	private Context context; // 运行上下文
	private int resource;
	private List<Map<String, Object>> listItems; // 酒店信息集合
	private LayoutInflater listContainer; // 视图容器

    // 自定义控件集合
	private ImageView hotelimage;
	private TextView hotelname;
	private TextView hotelrate;
	private TextView minprice;


	public HotelListAdapter(Context context,
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

	/**
	 * ListView Item设置
	 */
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Log.e("method", "getView");
		// 自定义视图
		if (convertView == null) {
			convertView = listContainer.inflate(resource, null);
		}
		// 获取每个item中的控件对象
		hotelimage = (ImageView) convertView  
				.findViewById(R.id.imageItem);
		//if(listItemView.hotelimage==null)System.out.println("meizhaodaokongjian");
		
		hotelrate = (TextView) convertView
				.findViewById(R.id.hotel_rate);
		hotelname = (TextView) convertView
				.findViewById(R.id.hotel_name);
		minprice = (TextView) convertView
				.findViewById(R.id.min_price);

		// Log.e("image", (String) listItems.get(position).get("title")); //测试
		// Log.e("image", (String) listItems.get(position).get("info"));

		// 设置文字和图片,实现数据和组件的绑定
		hotelimage.setBackgroundResource((Integer) listItems.get(   
                position).get("hotelimage"));
		hotelname.setText((String) listItems.get(position)   
                .get("hotelname"));
		hotelrate.setText((String) listItems.get(position)   
                .get("hotelrate"));
		minprice.setText((String) listItems.get(position)   
                .get("minprice"));
		

		return convertView;
	}

}
