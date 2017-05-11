package org.hfzy.Beijing;

import org.hfzy.util.Sf_Util;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

public class SplashActivity extends Activity {

	private RelativeLayout layout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		layout = (RelativeLayout) findViewById(R.id.splash_bg);
		moveImg();

	}

	private void moveImg() {
		RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		rotateAnimation.setDuration(1000);
		rotateAnimation.setFillAfter(true);

		ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		scaleAnimation.setDuration(2000);
		scaleAnimation.setFillAfter(true);

		AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
		alphaAnimation.setDuration(1000);
		alphaAnimation.setFillAfter(true);

		AnimationSet set = new AnimationSet(true);
		set.addAnimation(alphaAnimation);
		set.addAnimation(scaleAnimation);
		set.addAnimation(rotateAnimation);
		layout.startAnimation(set);
		
		set.setAnimationListener(new Animation.AnimationListener() {

			private Intent intent;

			@Override
			public void onAnimationStart(Animation animation) {

			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				 Boolean isFirst = (Boolean) Sf_Util.getParam(
							SplashActivity.this, "isFirst",true );
				if (isFirst) {
					intent = new Intent(SplashActivity.this,
							GuideActivity.class);
					startActivity(intent);
					Sf_Util.setParam(getApplicationContext(), "isFirst", false);
					
				} else {
					intent = new Intent(SplashActivity.this, MainActivity.class);
					startActivity(intent);
				}
				finish();
			}
		});
	}

}
