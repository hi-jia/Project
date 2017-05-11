package org.hfzy.fragment;

import java.util.ArrayList;

import org.hfzy.Beijing.MainActivity;
import org.hfzy.Beijing.R;
import org.hfzy.view.MyViewPage;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.org.basepage.Basepage;
import com.org.page.GovServerPage;
import com.org.page.HomePage;
import com.org.page.NewsPage;
import com.org.page.ServerPage;
import com.org.page.SettingPage;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class Mainfragment extends BaseFragment {

	private MyViewPage pager;
	private ArrayList<Basepage> pages;
	private RadioGroup group;
	private SlidingMenu menu;
	public View initView() {
		View view = View.inflate((mactivity), R.layout.main_fragment, null);
		group = (RadioGroup) view.findViewById(R.id.rb_group);
		pager = (MyViewPage) view.findViewById(R.id.mf_vp);

		RadioButton rb=(RadioButton) view.findViewById(R.id.rb_home);
		rb.setChecked(true);
		return view;
	}

	public void initData() {
		pages = new ArrayList<Basepage>();
		pages.add(new HomePage(mactivity));
		pages.add(new NewsPage(mactivity));
		pages.add(new ServerPage(mactivity));
		pages.add(new GovServerPage(mactivity));
		pages.add(new SettingPage(mactivity));
		pager.setAdapter(new myAdapter());
		// 性能优化，防止viewPage自动加载其他页面
		pager.setOnPageChangeListener(pageChageListener);
		// 第一次需要手动加载
		MainActivity ma =(MainActivity) mactivity;
		menu = ma.getSlidingMenu();
		pages.get(0).initData();
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		group.setOnCheckedChangeListener(checkedlistener);

	}

	OnPageChangeListener pageChageListener = new OnPageChangeListener() {

		public void onPageSelected(int position) {
			Basepage basepage = pages.get(position);
			basepage.initData();
			
			if (position==0||position==pages.size()-1) {
				menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
			}else {
				menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
			}
		}

		@Override
		public void onPageScrolled(int position, float positionOffset,
				int positionOffsetPixels) {

		}

		@Override
		public void onPageScrollStateChanged(int state) {

		}

	};

	private OnCheckedChangeListener checkedlistener = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			switch (checkedId) {
			case R.id.rb_home:
				// pager.setCurrentItem(0);
				pager.setCurrentItem(0, false);
				break;
			case R.id.rb_news:
				pager.setCurrentItem(1, false);
				break;
			case R.id.rb_server:
				pager.setCurrentItem(2, false);
				break;
			case R.id.rb_govserver:
				pager.setCurrentItem(3, false);
				break;
			case R.id.rb_setting:
				pager.setCurrentItem(4, false);
				break;
			default:
				break;
			}
		}
	};

	class myAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return pages.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			Basepage page = pages.get(position);
			//page.initData();
			View view = page.mlayout;

			container.addView(view);
			return view;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}
	}
	public NewsPage GetNewsGenter() {
		NewsPage page = (NewsPage) pages.get(1);
		return page;
		
	}

}
