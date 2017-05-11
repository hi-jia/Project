package com.org.basepage;

import org.hfzy.Beijing.MainActivity;
import org.hfzy.Beijing.R;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

public class Basepage {
	public Activity mActivity;
	public ImageButton ib_menu;
	public TextView tv_Title;
	public FrameLayout fl_bp;
	public View mlayout;

	public Basepage(Activity activity) {
		mActivity = activity;
		mlayout = initView();
	}

	public View initView() {

		View view = View.inflate(mActivity, R.layout.basepage, null);
		tv_Title = (TextView) view.findViewById(R.id.tv_title);
		ib_menu = (ImageButton) view.findViewById(R.id.ib_title);
		fl_bp = (FrameLayout) view.findViewById(R.id.fl_basepage);
		ib_menu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				toggle();
			}
		});
		return view;

	}

	public void initData() {

	}

	private void toggle() {
		MainActivity ma = (MainActivity) mActivity;
		SlidingMenu sm = ma.getSlidingMenu();
		sm.toggle();
	}

}
