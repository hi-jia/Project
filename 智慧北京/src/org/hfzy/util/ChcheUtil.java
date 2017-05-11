package org.hfzy.util;

import android.content.Context;

public class ChcheUtil {

	public static String getChche(String url,Context context) {

		return (String) Sf_Util.getParam(context, url, "");
	}
	
	public static void setChche(String url,String json,Context context) {

		Sf_Util.setParam(context, url, json);
	}
}
