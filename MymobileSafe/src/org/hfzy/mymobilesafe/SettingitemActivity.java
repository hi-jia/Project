package org.hfzy.mymobilesafe;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.TextView;

public class SettingitemActivity extends Activity {
private TextView tvUpdate;
private CheckBox cb;
private TextView tvCloseUpdate;
private SharedPreferences sp;
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.setting_view);
	View view = findViewById(R.id.vw_setting_update);
	tvUpdate = (TextView)view.findViewById(R.id.tv_setting_update);
	cb = (CheckBox) view.findViewById(R.id.cb_setting_update);
	tvCloseUpdate = (TextView) view.findViewById(R.id.tv_setting_closeUpdate);
	sp = getSharedPreferences("config", MODE_PRIVATE);
	
	boolean isCheck = sp.getBoolean("isCheck", false);

	if (isCheck) {
		tvCloseUpdate.setText("关闭更新");
		cb.setChecked(true);
	}else{
		tvCloseUpdate.setText("打开更新");
		cb.setChecked(false);
	}

	cb.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			if (cb.isChecked()) {
				tvCloseUpdate.setText("关闭更新");
			}else{
				tvCloseUpdate.setText("打开更新");
			}
			save();
		}
	});
	

}
private void save() {
	sp = getSharedPreferences("config", MODE_PRIVATE);
	Editor edit = sp.edit();
	edit.putBoolean("isCheck", cb.isChecked());
	edit.commit();
}
}
