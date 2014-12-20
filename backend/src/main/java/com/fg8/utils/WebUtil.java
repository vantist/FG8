package com.fg8.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;

public class WebUtil {
	// Connection config
	private static int connectTimeout = 30000;
	private static int readTimeout = 60000;
	private static boolean doInput = true;
	private static String requrestMethod = "GET";
	private static String encoding = "big5";
	private static boolean debugMode = true;

	static public String getUrlContent(String urlString) {
		BufferedReader br = null;
		String content = "";
		Long startTime = System.currentTimeMillis();
		
		try {
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(connectTimeout);
			conn.setReadTimeout(readTimeout);
			conn.setDoInput(doInput);
			conn.setRequestMethod(requrestMethod);
			
			if ("gzip".equalsIgnoreCase(conn.getContentEncoding())) {
				GZIPInputStream gzipis = new GZIPInputStream(conn.getInputStream());
				InputStreamReader isr = new InputStreamReader(gzipis);
				br = new BufferedReader(isr);
			} else {
				br = new BufferedReader(new InputStreamReader(conn.getInputStream(), encoding));
			}
			
			if (debugMode)
				System.out.println(urlString + " 網頁讀取時間 : " + (System.currentTimeMillis() - startTime));
			startTime = System.currentTimeMillis();
			
			StringBuffer sbf = new StringBuffer();
			String tmp = "";
			while((tmp = br.readLine()) != null) {
				sbf.append(tmp);
			}
			content = sbf.toString();
			
			if (debugMode)
				System.out.println(urlString + " 字串組成時間 : " + (System.currentTimeMillis() - startTime));
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				br = null;
			}
		}
		
		return content;
	}
}
