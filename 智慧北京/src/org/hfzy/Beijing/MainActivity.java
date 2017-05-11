package org.hfzy.Beijing;

import org.hfzy.fragment.Mainfragment;
import org.hfzy.fragment.leftfragment;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;

public class MainActivity extends SlidingFragmentActivity {
	private static final String TAG_LEFTMENU = "left";
	private static final String TAG_MAIN = "main";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		setBehindContentView(R.layout.left_layout);
		SlidingMenu menu = getSlidingMenu();
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		menu.setBehindOffset(200);

		initFragment();
	}

	private void initFragment() {
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		transaction.replace(R.id.fl_left, new leftfragment(),TAG_LEFTMENU);
		transaction.replace(R.id.fl_main, new Mainfragment(),TAG_MAIN);
		transaction.commit();
	}
	public leftfragment getLeftMenu() {
		FragmentManager manager = getSupportFragmentManager();
		leftfragment fragment = (leftfragment) manager.findFragmentByTag(TAG_LEFTMENU);
		return fragment;
	}
	
	public Mainfragment getMainFragment() {
		FragmentManager manager = getSupportFragmentManager();
		Mainfragment fragment = (Mainfragment) manager.findFragmentByTag(TAG_MAIN);
		return fragment;
	}
}
