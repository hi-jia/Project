package com.org.page;

import java.util.ArrayList;

import org.apache.http.HttpException;
import org.chzy.News.IteractMenu;
import org.chzy.News.NewsMenu;
import org.chzy.News.PhotoMenu;
import org.chzy.News.SubjectMenu;
import org.chzy.global.GlobalCon;
import org.hfzy.Beijing.MainActivity;
import org.hfzy.domain.GetDataJsonClass;
import org.hfzy.fragment.leftfragment;
import org.hfzy.util.ChcheUtil;

import android.app.Activity;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.org.basepage.BaseMenuPage;
import com.org.basepage.Basepage;

public class NewsPage extends Basepage {

	public ArrayList<BaseMenuPage>lists=new ArrayList<BaseMenuPage>();
	private GetDataJsonClass data;
	public NewsPage(Activity activity) {
		super(activity);
	}
	public void initData() {
//		
//		TextView tv_home=new TextView(mActivity);
//		tv_home.setText("首页hguybguyhvbu");
//		tv_home.setGravity(Gravity.CENTER);
//		tv_home.setTextSize(30);
//		fl_bp.addView(tv_home);
//		setNewCenterView(0);
//		
		tv_Title.setText("新闻中心");
		
		String chche = ChcheUtil.getChche(GlobalCon.RootUrlNext,mActivity);
		if (TextUtils.isEmpty(chche)) {
			getData();
			System.out.println("no!!!!!!!!!");
		}else {
			getJsonData(chche);
			System.out.println("缓存******");
		}
	
	
		
	}
	
	public void setNewCenterView(int positon) {
		fl_bp.addView(lists.get(positon).initView());
	}
	private void getData() {
		HttpUtils httpUtils=new HttpUtils();
		httpUtils.send(HttpMethod.GET, GlobalCon.RootUrlNext, new RequestCallBack<String>() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String result = responseInfo.result;
				System.out.println(result+"*******************************************");
				getJsonData(result);
				ChcheUtil.setChche(GlobalCon.RootUrlNext, result, mActivity);
			}
			public void onFailure(HttpException error, String msg) {
				System.out.println("连接失败");
			}
			@Override
			public void onFailure(
					com.lidroid.xutils.exception.HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}
		});
		}
	private void getJsonData(String result) {
		Gson gson=new Gson();
		data = gson.fromJson(result,GetDataJsonClass.class);
		System.out.println(data);
		NewsMenu newsMenu = new NewsMenu(mActivity,data.data.get(0).children);
		
		lists.add(newsMenu);
		lists.add(new SubjectMenu(mActivity));
		lists.add(new PhotoMenu(mActivity));
		lists.add(new IteractMenu(mActivity));
		
		System.out.println(data.data.get(0).children.get(0).title+"**************************************************");
		MainActivity ma=(MainActivity)mActivity;
		leftfragment leftMenu = ma.getLeftMenu(); 
		leftMenu.initLeftMenu(data.data);
		}

}
