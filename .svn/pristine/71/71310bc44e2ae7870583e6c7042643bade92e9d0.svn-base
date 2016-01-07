package com.hotale.adapter;

import java.util.List;
import java.util.Map;

import com.example.t.R;
import com.hotale.entity.Hotel;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class OrderListAdapter extends BaseAdapter {

	private Context context; // 运行上下文
	private int resource;
	private List<Map<String, Object>> listItems; // 酒店信息集合
	private LayoutInflater listContainer; // 视图容器

	// 自定义控件集合
	private TextView orderid;
	private TextView ordertime;
	private TextView totalprice;
	private TextView orderstatus;

	public OrderListAdapter(Context context,
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

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// 自定义视图
		if (convertView == null) {
			convertView = listContainer.inflate(resource, null);
		}
		// 获取每个item中的控件对象
		orderid = (TextView) convertView.findViewById(R.id.order_id);
		ordertime = (TextView) convertView.findViewById(R.id.order_time);
		orderstatus = (TextView) convertView.findViewById(R.id.order_status);
		totalprice = (TextView) convertView.findViewById(R.id.total_price);

		// 设置文字和图片,实现数据和组件的绑定
		orderid.setText("酒店：" + (String) (listItems.get(position).get("hotel")));//注意：orderid处显示酒店名称
		ordertime.setText("下单时间："
				+ (String) listItems.get(position).get("ordertime"));
		orderstatus
				.setText((String) listItems.get(position).get("orderstatus"));
		totalprice.setText((String) listItems.get(position).get("totalprice"));
		return convertView;
	}

}
