package org.chzy.News;

import java.util.ArrayList;

import org.hfzy.Beijing.R;
import org.hfzy.domain.GetDataJsonClass.NewsTabData;

import android.app.Activity;
import android.provider.ContactsContract.Contacts.Data;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.org.basepage.BaseMenuDataView;
import com.org.basepage.BaseMenuPage;

public class NewsMenu extends BaseMenuPage {

	ArrayList<BaseMenuDataView> vlists;
	ArrayList<NewsTabData> mdata;
	private ViewPager vpData;

	public NewsMenu(Activity mActivity, ArrayList<NewsTabData> children) {
		super(mActivity);
		mdata = children;
	}

	@Override
	public View initView() {
		View view = View.inflate(mActivity, R.layout.newdata_viewpager, null);
		vpData = (ViewPager) view.findViewById(R.id.vp_newData);
		return view;
		
	}

	@Override
	public void initData() {
		super.initData();
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		vlists=new ArrayList<BaseMenuDataView>();
		for (int i = 0; i < mdata.size(); i++) {
			BaseMenuDataView dv=new BaseMenuDataView(mActivity,mdata.get(i));
			vlists.add(dv);
		}
		vpData.setAdapter(new myAdapter());
		
	}

	class myAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			return vlists.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view==object;
		}
		
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			BaseMenuDataView DataView = vlists.get(position);
			View v= DataView.mRootView;
			DataView.initDate();
			container.addView(v);
			return v;
			
		}
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}
	}
	private PagerAdapter adapter = new PagerAdapter() {

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

		@Override
		public int getCount() {
			return vlists.size();
		}

		public Object instantiateItem(ViewGroup container, int position) {
			BaseMenuDataView DataView = vlists.get(position);
			View v= DataView.mRootView;
			DataView.initDate();
			container.addView(v);
			return v;
		}

		public void destroyItem(ViewGroup container, int position, Object object) {

			container.removeView((View) object);
		}
	};
}
