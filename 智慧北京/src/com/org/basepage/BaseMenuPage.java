package com.org.basepage;

import android.app.Activity;
import android.view.View;

public abstract class BaseMenuPage {
	public View mRootView;
	public Activity mActivity;

	public BaseMenuPage(Activity mActivity) {
		this.mActivity = mActivity;
		mRootView = initView();
	}

	public abstract View initView();
	public void initData() {
		
	}
	
}
