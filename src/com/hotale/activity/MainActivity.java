package com.hotale.activity;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.t.R;
import com.hotale.entity.User;
import com.hotale.utils.HotaleApplication;

public class MainActivity extends Activity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks {

	/**
	 * Fragment managing thet behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;

	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 设置主页背景
		View v = findViewById(R.id.container);
		v.setBackgroundResource(R.drawable.main_background);

		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
	}
	
  

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager
				.beginTransaction()
				.replace(R.id.container,
						PlaceholderFragment.newInstance(position + 1)).commit();
	}

	public void onSectionAttached(int number) {
		switch (number) {
		case 1:
			mTitle = getString(R.string.title_section1);
			break;
		case 2:
			mTitle = getString(R.string.title_section2);
			break;
		case 3:
			mTitle = getString(R.string.title_section3);
			break;
		}
	}

	public void restoreActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.left, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public void finish() {
		startActivity(new Intent(getApplication(), MainActivity.class));
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";
		ViewPager viewPager;
		ImageButton mainbtn_user;
		ImageButton mainbtn_resev;
		ImageButton mainbtn_orders;
		ImageButton mainbtn_favorite;
		ImageButton mainbtn_contactus;
		ArrayList<View> list;
		static int c_id = 0;

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			list = new ArrayList<View>();
			View v1 = inflater.inflate(R.layout.a, null);
			View v2 = inflater.inflate(R.layout.a2, null);
			list.add(v1);
			list.add(v2);

			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			viewPager = (ViewPager) rootView.findViewById(R.id.viewPager);
			viewPager.setAdapter(new MyAdapter());
			viewPager.setOnPageChangeListener(new MyListener());
			viewPager.setCurrentItem(300);
			// 获取a.xml里的控件(也就是在主界面首个fragment里的控件)
			mainbtn_user = (ImageButton) v1.findViewById(R.id.mainbtn_user);
			mainbtn_resev = (ImageButton) v1.findViewById(R.id.mainbtn_resev);
			mainbtn_orders = (ImageButton) v1.findViewById(R.id.mainbtn_orders);
			mainbtn_favorite = (ImageButton) v1.findViewById(R.id.mainbtn_favorite);
			mainbtn_contactus = (ImageButton) v1.findViewById(R.id.mainbtn_contact);
			// if(mainbtn_user==null)Log.e("123","!!!!");
			mainbtn_user.setOnClickListener(new ButtonClickListener());
			mainbtn_resev.setOnClickListener(new ButtonClickListener());
			mainbtn_orders.setOnClickListener(new ButtonClickListener());
			mainbtn_favorite.setOnClickListener(new ButtonClickListener());
			mainbtn_contactus.setOnClickListener(new ButtonClickListener());
			return rootView;
		}

		private final class ButtonClickListener implements View.OnClickListener {
			public void onClick(View v) {
				//先判断是否有用户登录
				User userlogined=((HotaleApplication)(getActivity().getApplication())).getUserLogined();
				switch (v.getId()) {
				case R.id.mainbtn_user:
					if (userlogined==null) {
						startActivity(new Intent(getActivity(), LoginActivity.class));
					}else{
						startActivity(new Intent(getActivity(), UserInfoActivity.class));
					}
					break;

				case R.id.mainbtn_resev:
					startActivity(new Intent(getActivity(),
							SearchActivity.class));
					break;

				case R.id.mainbtn_orders:
					startActivity(new Intent(getActivity(),
							OrderListActivity.class));
					break;	
				case R.id.mainbtn_contact:
					new AlertDialog.Builder(getActivity())   
	                .setTitle("关于我们")    
	                .setMessage("制作人：姜博文、蔡晟航\n\n联系邮箱：jbw7@qq.com\n2015年3月软通实训作品\n欢迎批评指正！")    
	                .setPositiveButton("确定", null)      
	                .show();  
					break;
				default:
					break;
				}
			}
		}

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			((MainActivity) activity).onSectionAttached(getArguments().getInt(
					ARG_SECTION_NUMBER));
		}

		class MyAdapter extends PagerAdapter {

			@Override
			public int getCount() {
				return Integer.MAX_VALUE;
			}

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}

			@Override
			public int getItemPosition(Object object) {
				// TODO Auto-generated method stub
				return super.getItemPosition(object);
			}

			@Override
			public void destroyItem(View arg0, int arg1, Object arg2) {
				// TODO Auto-generated method stub
				// ((ViewPager) arg0).removeView(list.get(arg1));
			}

			@Override
			public Object instantiateItem(View arg0, int arg1) {
				// TODO Auto-generated method stub
				try {
					((ViewPager) arg0).addView(list.get(arg1 % list.size()), 0);
				} catch (Exception e) {
					// TODO: handle exception
				}
				return list.get(arg1 % list.size());
			}

			@Override
			public void restoreState(Parcelable arg0, ClassLoader arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public Parcelable saveState() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void startUpdate(View arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void finishUpdate(View arg0) {
				// TODO Auto-generated method stub

			}
		}

		class MyListener implements OnPageChangeListener {

			// 当滑动状态改变时调用
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				// arg0=arg0%list.size();

			}

			// 当当前页面被滑动时调用
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			// 当新的页面被选中时调用
			@Override
			public void onPageSelected(int arg0) {
				if (arg0 > 2) {
					arg0 = arg0 % list.size();
				}
				c_id = arg0;
				/*
				 * for (int i = 0; i < imageViews.length; i++) {
				 * imageViews[arg0]
				 * .setBackgroundResource(R.drawable.guide_dot_white); if (arg0
				 * != i) { imageViews[i]
				 * .setBackgroundResource(R.drawable.guide_dot_black); } }
				 */

				// Log.e("-------------", "当前是第" + c_id + "页");
				System.out.println("当前是第" + c_id + "页");
				// System.out.println("-------------", "当前是第" + c_id + "页");
			}

		}
	}
}