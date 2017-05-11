package org.hfzy.ui;
import org.hfzy.mymobilesafe.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

public class setting_item extends RelativeLayout{

	public setting_item(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
		// TODO Auto-generated constructor stub
	}

	public setting_item(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
		// TODO Auto-generated constructor stub
	}

	public setting_item(Context context) {
		super(context);
		init();
		// TODO Auto-generated constructor stub
	}

	private void init() {
		//添加布局文件
//		TextView textView = new TextView(getContext());
//		textView.setText("我是自定义组合控件的textview");
		//第一种方式
		//将布局文件转化成view对象
//		View view = View.inflate(getContext(), R.layout.settingview, null);//爹有了,去找孩子,亲生
//		//添加操作
//		this.addView(view);//在自定义组合控件中添加一个textview
		//第二种方式
		//获取view对象,同时给veiw对象设置父控件,相当于先创建一个view对象,在把控件放到自定义控件中
	//	View.inflate(getContext(), R.layout.settingview, this);//孩子有了,去找爹,喜当爹
		View view = View.inflate(getContext(), R.layout.setting_item,this);

	}
	
	

	
	
	
}

