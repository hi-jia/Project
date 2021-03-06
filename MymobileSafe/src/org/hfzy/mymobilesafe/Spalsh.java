package org.hfzy.mymobilesafe;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.hfzy.mymobilesafe.util.FileReadWriteUtil;
import org.json.JSONException;
import org.json.JSONObject;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.net.Uri;
import android.opengl.Visibility;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Spalsh extends Activity {

	protected static final int MES_HOME = 0;
	protected static final int MES_DIALOG = 1;
	protected static final int MES_FAIL = 2;
	protected static final int MES_JSON = 3;
	protected static final int MES_IO = 4;
	protected static final int MES_URL = 5;
	private TextView tv_version, tv_progress;
	private String localVersion;
	private String des;
	private String serviceVersion;
	private String appUrl;
	private SharedPreferences sp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spalsh);
		tv_version = (TextView) findViewById(R.id.spalsh_tv_version);
		tv_progress = (TextView) findViewById(R.id.tv_spalsh_progress);
		localVersion = getVersion();
		tv_version.setText("版本号:" + localVersion);
		sp = getSharedPreferences("config", MODE_PRIVATE);
		 boolean isCheck = sp.getBoolean("isCheck", true);
		if(isCheck){
			checkVersion();
		}else {
			new Thread(new Runnable() {
				public void run() {
					SystemClock.sleep(2000);
					goHome();
				}
			}).start();
		}
		
	}
	
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case MES_HOME:
				goHome();
				break;
			case MES_DIALOG:
				dialog();
				break;
			case MES_FAIL:
				Toast.makeText(getApplicationContext(), "亲，网络开小差了，请检查！", 0)
						.show();
				goHome();
				break;
			case MES_IO:
				Toast.makeText(getApplicationContext(), "请求失败，错误代码:"+MES_IO, 0)
						.show();
				goHome();
				break;
			case MES_URL:
				Toast.makeText(getApplicationContext(), "请求失败，错误代码:"+MES_URL, 0)
						.show();
				goHome();
				break;
			case MES_JSON:
				Toast.makeText(getApplicationContext(), "请求失败，错误代码:"+MES_JSON, 0)
						.show();
				goHome();
				break;
			default:
				break;
			}

		};
	};

	private void dialog() {
		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		dialog.setTitle("版本更新");
		dialog.setMessage(des);
		dialog.setPositiveButton("更新", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				downApp();
			}

		});

		dialog.setNegativeButton("取消", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				goHome();
			}
		});
		dialog.show();
	}

	private void downApp() {
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
		HttpUtils utils = new HttpUtils();
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		utils.download(appUrl, "/mnt/sdcard/MymobileSafe.apk",
				new RequestCallBack<File>() {

					@Override
					public void onSuccess(ResponseInfo<File> arg0) {
						installApk();
						System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!");
					}

					@Override
					public void onFailure(HttpException arg0, String arg1) {

					}

					@Override
					public void onLoading(long total, long current,
							boolean isUploading) {
						super.onLoading(total, current, isUploading);
						tv_progress.setVisibility(View.VISIBLE);
						tv_progress.setText(current + "/" + total);

					}
				});
		}else {
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			Toast.makeText(getApplicationContext(), "未检测到SD卡，请检查设备", 0).show();
		}
	}

	private void installApk() {
		/*
		 * <intent-filter> <action android:name="android.intent.action.VIEW" />
		 * <category android:name="android.intent.category.DEFAULT" /> <data
		 * android:scheme="content" /> <data android:scheme="file" /> <data
		 * android:mimeType="application/vnd.android.package-archive" />
		 * </intent-filter>
		 */
		Intent intent = new Intent();
		intent.setAction("android.intent.action.VIEW");
		intent.addCategory("android.intent.category.DEFAULT");
		// 单独设置会相互覆盖
		/*
		 * intent.setType("application/vnd.android.package-archive");
		 * intent.setData(Uri.fromFile(new
		 * File("/mnt/sdcard/mobliesafe75_2.apk")));
		 */
		intent.setDataAndType(
				Uri.fromFile(new File("/mnt/sdcard/MymobileSafe.apk")),
				"application/vnd.android.package-archive");
		// 在当前activity退出的时候,会调用之前的activity的onActivityResult方法
		// requestCode : 请求码,用来标示是从哪个activity跳转过来
		// ABC a -> c b-> c ,c区分intent是从哪个activity传递过来的,这时候就要用到请求码
		startActivityForResult(intent, 0);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		goHome();
	}

	private void goHome() {
		Intent intent = new Intent(getApplicationContext(), Homeactivity.class);
		startActivity(intent);
		finish();
	}

	private String getVersion() {
		PackageManager manager = getPackageManager();
		try {
			PackageInfo info = manager.getPackageInfo(getPackageName(), 0);
			String version = info.versionName;
			return version;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void checkVersion() {
		new Thread(new Runnable() {
			private Message mes = Message.obtain();
			private int startTime;
			@Override
			public void run() {
				try {
					startTime = (int) System.currentTimeMillis();
					URL url = new URL("http://192.168.2.100:8080/app.html");
					HttpURLConnection conn = (HttpURLConnection) url
							.openConnection();
					conn.setConnectTimeout(5000);
					conn.setRequestMethod("GET");
					int code = conn.getResponseCode();
					if (code == 200) {
						InputStream in = conn.getInputStream();
						String str = FileReadWriteUtil.getString(in);
						JSONObject json = new JSONObject(str);
						serviceVersion = json.getString("version");
						des = json.getString("des");
						appUrl = json.getString("appUrl");
						System.out.println(appUrl+"*************************");
						in.close();
						if (serviceVersion.equals(localVersion)) {
							mes.what = MES_HOME;
						} else {
							mes.what = MES_DIALOG;
						}
					} else {
						System.out.println("连接服务器失败");
						mes.what = MES_FAIL;
					}
				} catch (MalformedURLException e) {
					e.printStackTrace();
					mes.what = MES_URL;
				} catch (IOException e) {
					e.printStackTrace();
					mes.what = MES_IO;
				} catch (JSONException e) {
					e.printStackTrace();
					mes.what = MES_JSON;
				} finally {
					int endTime = (int) System.currentTimeMillis();
					if (endTime - startTime <2000) {
						SystemClock.sleep(2000);
					}
					handler.sendMessage(mes);
				}
				
			}
		}).start();
	}
}
