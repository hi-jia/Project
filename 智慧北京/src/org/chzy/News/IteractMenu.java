package org.chzy.News;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.org.basepage.BaseMenuPage;

public class IteractMenu extends BaseMenuPage {

	public IteractMenu(Activity mActivity) {
		super(mActivity);
	}

	@Override
	public View initView() {
		TextView tv_home=new TextView(mActivity);
		tv_home.setText("»¥¶¯");
		tv_home.setGravity(Gravity.CENTER);
		tv_home.setTextSize(30);
		
		return tv_home;
	}

}
