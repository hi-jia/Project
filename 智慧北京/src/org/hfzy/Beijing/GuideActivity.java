package org.hfzy.Beijing;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class GuideActivity extends Activity {
	private Button btnStart;
	private ViewPager vp;
	private ArrayList<ImageView> imgList;
	private LinearLayout ll;
	private ImageView grayPoint;
	private ImageView redPoint;
	private int twoPointDis;
	private RelativeLayout rl;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_guide);
		initView();
		initData();
		vp.setAdapter(new myAdapter());
		vp.setOnPageChangeListener(changeListener);
		redPoint.getViewTreeObserver().addOnGlobalLayoutListener(globalLayoutListener );
	}

	private void initView() {
		rl=(RelativeLayout) findViewById(R.id.rl_point);
		btnStart = (Button) findViewById(R.id.btn_start);
		vp = (ViewPager) findViewById(R.id.vp_pager);
		ll = (LinearLayout) findViewById(R.id.ll_container);
		redPoint=(ImageView) findViewById(R.id.iv_red_point);
		btnStart.setVisibility(View.INVISIBLE);
	}

	
	private void initData() {
		int[] img = { R.drawable.guide_1, R.drawable.guide_2,
				R.drawable.guide_3 };
		imgList = new ArrayList<ImageView>();
		for (int i = 0; i < img.length; i++) {
			ImageView imageView = new ImageView(this);
			imageView.setBackgroundResource(img[i]);
			imgList.add(imageView);

			// 小圆点
			grayPoint = new ImageView(this);
			grayPoint.setBackgroundResource(R.drawable.point_gray);
			// grayPoint.setBackgroundResource(R.drawable.grayPoint_red);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.WRAP_CONTENT,
					LinearLayout.LayoutParams.WRAP_CONTENT);
			if (i > 0) {
				params.leftMargin = 20;
			}

			ll.addView(grayPoint, params);
			
			btnStart.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(GuideActivity.this, MainActivity.class);
					startActivity(intent);
					finish();
				}
			});
		}

	}

	class myAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return imgList.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {

			ImageView view = imgList.get(position);
			container.addView(view);

			return view;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			container.removeView((View) object);
		}
	}

	OnPageChangeListener changeListener = new OnPageChangeListener() {

		@Override
		public void onPageSelected(int position) {

			if (imgList.size()-1==position) {
				btnStart.setVisibility(View.VISIBLE);
			}
				
		}

		@Override
		public void onPageScrolled(int position, float positionOffset,
				int positionOffsetPixels) {
			// 当页面滑动过程中的回调
			System.out.println("当前位置:" + position + ";移动偏移百分比:"
					+ positionOffset);
//			System.out.println("positionOffset:"+positionOffset);
//			System.out.println("positionOffsetPixels:"+positionOffsetPixels);
//			System.out.println("position:"+position);
//			System.out.println("******************************************");
			int newLeftPosition=(int) (twoPointDis*positionOffset+position*twoPointDis);
			RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) redPoint
					.getLayoutParams();
			params.leftMargin = newLeftPosition;// 修改左边距

			// 重新设置布局参数
			redPoint.setLayoutParams(params);
		}

		public void onPageScrollStateChanged(int state) {

		}
	};
	OnGlobalLayoutListener globalLayoutListener=new OnGlobalLayoutListener() {
		

		public void onGlobalLayout() {
			// 移除监听,避免重复回调
			redPoint.getViewTreeObserver()
					.removeGlobalOnLayoutListener(this);
		twoPointDis = ll.getChildAt(1).getLeft()-ll.getChildAt(0).getLeft();
		}
	};
	
}
