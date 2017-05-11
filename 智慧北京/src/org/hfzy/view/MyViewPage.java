package org.hfzy.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class MyViewPage extends ViewPager {

	public MyViewPage(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyViewPage(Context context) {
		super(context);
	}

@Override
public boolean onTouchEvent(MotionEvent ev) {
	return true;
}

}
