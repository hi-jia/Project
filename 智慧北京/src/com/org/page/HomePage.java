package com.org.page;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.org.basepage.Basepage;

public class HomePage extends Basepage {

	public HomePage(Activity activity) {
		super(activity);
	}

	public void initData() {
		System.out.println("首页初始化啦...");
		TextView tv_home=new TextView(mActivity);
		tv_home.setText("首页");
		tv_home.setGravity(Gravity.CENTER);
		tv_home.setTextSize(30);
		fl_bp.addView(tv_home);
		ib_menu.setVisibility(View.INVISIBLE);
		tv_Title.setText("智慧北京");
	}
}
