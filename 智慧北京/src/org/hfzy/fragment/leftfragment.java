package org.hfzy.fragment;

import java.util.ArrayList;

import org.hfzy.Beijing.MainActivity;
import org.hfzy.Beijing.R;
import org.hfzy.domain.GetDataJsonClass.NewsMenuData;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.org.basepage.BaseMenuPage;
import com.org.page.NewsPage;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class leftfragment extends BaseFragment {

	private ListView lv;
	private int currentPosition;

	@Override
	public void initData() {
	}



	@Override
	public View initView() {
		View view = View.inflate(mactivity, R.layout.left_fragment, null);
		lv = (ListView) view.findViewById(R.id.lv);
		return view;
	}
	private ArrayList<NewsMenuData> myData;
	
	public void initLeftMenu(ArrayList<NewsMenuData> data) {
		currentPosition=0;
		myData=data;
		lv.setAdapter(adapter);
		getNewsView(0);//初始化侧边栏的位置
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				currentPosition=position;
				adapter.notifyDataSetChanged();
				toggle();//侧边栏开关
				getNewsView( position);
			}
			
		});
	}
	
	//侧边栏的开关闭合
	private void toggle() {
		MainActivity ma=(MainActivity) mactivity;
		SlidingMenu sm = ma.getSlidingMenu();
		sm.toggle();
	}
	
	public void getNewsView(int position) {
		MainActivity ma=(MainActivity) mactivity;
		Mainfragment fragment = ma.getMainFragment();
		NewsPage newsGenter = fragment.GetNewsGenter();
		 BaseMenuPage page = newsGenter.lists.get(position);
		newsGenter.fl_bp.removeAllViews();
		newsGenter.fl_bp.addView(page.initView());
		
	}
	private BaseAdapter adapter=new BaseAdapter() {
		
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = View.inflate(mactivity, R.layout.item_left_menu, null);
			TextView tv= (TextView) view.findViewById(R.id.tv_menu);
			
			tv.setText(myData.get(position).title);
//			TextView tv=new TextView(mactivity);
//			tv.setText("hah");
			if (position==currentPosition) {
				tv.setEnabled(true);
			}else
				tv.setEnabled(false);
			
			return view;
		}
		
		@Override
		public long getItemId(int position) {
			return 0;
		}
		
		@Override
		public NewsMenuData getItem(int position) {
			NewsMenuData menuData = myData.get(position);
			return menuData;
		}
		
		@Override
		public int getCount() {
			return myData.size();
		}
	};

}
