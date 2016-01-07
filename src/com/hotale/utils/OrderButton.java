package com.hotale.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

public class OrderButton extends Button {
	public OrderButton(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}
	public OrderButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	public OrderButton(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	int index;
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}


}
