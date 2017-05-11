package com.org.basepage;

import org.hfzy.domain.GetDataJsonClass.NewsTabData;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

public  class BaseMenuDataView extends BaseMenuPage {

	public Activity mActivity;
	public NewsTabData mTabData;
	private TextView tv;
	public BaseMenuDataView(Activity mActivity, NewsTabData newsTabData) {
		super(mActivity);
		mActivity=mActivity;
		newsTabData=mTabData;
		
	}
	
	public  void initDate(){
		tv.setText(mTabData.title);
	}

	@Override
	public View initView() {
		tv = new TextView(mActivity);
		tv.setText(mTabData.title);
		System.out.println("------------------->>>>");
		System.out.println("title---------¡·"+mTabData.title);
		tv.setGravity(Gravity.CENTER);
		tv.setTextSize(30);
		return tv;
	}
}
