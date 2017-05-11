package com.org.page;

import android.app.Activity;
import android.view.Gravity;
import android.widget.TextView;

import com.org.basepage.Basepage;

public class ServerPage extends Basepage {

	public ServerPage(Activity activity) {
		super(activity);
	}
	public void initData() {
		TextView tv_home=new TextView(mActivity);
		tv_home.setText("首页");
		tv_home.setGravity(Gravity.CENTER);
		tv_home.setTextSize(30);
		fl_bp.addView(tv_home);
		
		tv_Title.setText("服务中心");
	}
}
