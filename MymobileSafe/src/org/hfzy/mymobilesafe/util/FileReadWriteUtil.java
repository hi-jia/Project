package org.hfzy.mymobilesafe.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class FileReadWriteUtil {

	public static String getString(InputStream in) throws IOException {
		BufferedReader reader=new BufferedReader(new InputStreamReader(in,"GBK"));
		StringBuffer buffer=new StringBuffer();
		String str=null;
		while ((str=reader.readLine())!=null) {
			buffer.append(str);
			buffer.append("/r/n");
		}
		return buffer.toString();

	}
}
