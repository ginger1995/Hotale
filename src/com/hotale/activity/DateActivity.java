package com.hotale.activity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.Toast;

import com.example.t.R;

public class DateActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_date);
		CalendarView cv = (CalendarView) this.findViewById(R.id.calendarView);
		cv.setOnDateChangeListener(new OnDateChangeListener() {
			int clicknum = 0;
			String indatestr = "", outdatestr = "";
			Date indate, outdate, nowdate;
			String fmt = "yyyy��MM��dd��";// �������ڸ�ʽ
			SimpleDateFormat sdf = new SimpleDateFormat(fmt);
			Intent dateintent = new Intent(getApplication(),
					SearchActivity.class);
			Bundle bundle = new Bundle();
			Calendar today = Calendar.getInstance();

			@Override
			public void onSelectedDayChange(CalendarView view, int year,
					int month, int dayOfMonth) {
				switch (clicknum) {
				case 0:
					nowdate = today.getTime();
					month++;
					indatestr = year + "��" + month + "��" + dayOfMonth + "��";
					try {
						indate = (Date) sdf.parseObject(indatestr);
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					if (nowdate.before(indate)) {
						bundle.putString("indatestr", indatestr);
						Toast.makeText(getApplication(),
								"������ס����Ϊ��" + indatestr, Toast.LENGTH_SHORT)
								.show();
						clicknum++;
					} else {
						Toast.makeText(getApplication(),
								"��ס���ڲ����ڽ���֮ǰ�������µ�ѡ��ס���������", Toast.LENGTH_LONG)
								.show();
						clicknum = 0;
					}
					break;
				case 1:
					month++;
					outdatestr = year + "��" + month + "��" + dayOfMonth + "��";
					try {
						outdate = (Date) sdf.parseObject(outdatestr);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					// ������������Ƿ�����ס����֮��
					if (indate.before(outdate)) {
						bundle.putString("outdatestr", outdatestr);
						dateintent.putExtras(bundle);
						Toast.makeText(getApplication(),
								"�����������Ϊ��" + outdatestr, Toast.LENGTH_SHORT)
								.show();
						setResult(200, dateintent);// ��ȷѡ�����ں���ת��SearchActivity
						finish();
					} else {
						Toast.makeText(getApplication(),
								"������ڱ�������ס����֮�������µ�ѡ��ס���������", Toast.LENGTH_LONG)
								.show();
						clicknum = 0;
					}
					break;
				default:
					break;
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.date, menu);
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
