package org.hfzy.mymobilesafe;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class Homeactivity extends Activity{

	GridView gv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_home);
		super.onCreate(savedInstanceState);
		gv=(GridView) findViewById(R.id.gv_home_item);
		gv.setAdapter(new myAdapter());
		gv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				switch (position) {
				case 8:
					Toast.makeText(getApplicationContext(), "hahahhaha",0).show();
					Intent intent=new Intent(Homeactivity.this,SettingitemActivity.class);
					startActivity(intent);
					break;

				default:
					break;
				}

				
			}
		});
	}
	
	class myAdapter extends BaseAdapter{

		int[] imageId = { R.drawable.safe, R.drawable.callmsgsafe, R.drawable.app,
				R.drawable.taskmanager, R.drawable.netmanager, R.drawable.trojan,
				R.drawable.sysoptimize, R.drawable.atools, R.drawable.settings };
		String[] names = { "手机防盗", "通讯卫士", "软件管理", "进程管理", "流量统计", "手机杀毒", "缓存清理",
				"高级工具", "设置中心" };
		@Override
		public int getCount() {
			return 9;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
		
			View view = View.inflate(getApplicationContext(), R.layout.gridview_item, null);
			ImageView iv = (ImageView) view.findViewById(R.id.gv_iv);
			TextView tv=(TextView) view.findViewById(R.id.gv_tv);
			iv.setImageResource(imageId[position]);
			tv.setText(names[position]);
			return view;
		}
		
	}
}
