package com.fg8.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

public class VUtil {
	// Connection config
	private int connectTimeout = 30000;
	private int readTimeout = 60000;
	private boolean doInput = true;
	private String requrestMethod = "GET";
	private String encoding = "big5";
	private boolean debugMode = true;
	
//	public String getUrlContent(String urlString) {
//		BufferedReader br = null;
//		String content = "";
//		Long startTime = System.currentTimeMillis();
//		
//		try {
//			URL url = new URL(urlString);
//			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//			conn.setConnectTimeout(connectTimeout);
//			conn.setReadTimeout(readTimeout);
//			conn.setDoInput(doInput);
//			conn.setRequestMethod(requrestMethod);
//			
//			if ("gzip".equalsIgnoreCase(conn.getContentEncoding())) {
//				GZIPInputStream gzipis = new GZIPInputStream(conn.getInputStream());
//				InputStreamReader isr = new InputStreamReader(gzipis);
//				br = new BufferedReader(isr);
//			} else {
//				br = new BufferedReader(new InputStreamReader(conn.getInputStream(), encoding));
//			}
//			
//			if (debugMode)
//				System.out.println(urlString + " 網頁讀取時間 : " + (System.currentTimeMillis() - startTime));
//			startTime = System.currentTimeMillis();
//			
//			StringBuffer sbf = new StringBuffer();
//			String tmp = "";
//			while((tmp = br.readLine()) != null) {
//				sbf.append(tmp);
//			}
//			content = sbf.toString();
//			
//			if (debugMode)
//				System.out.println(urlString + " 字串組成時間 : " + (System.currentTimeMillis() - startTime));
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if (br != null) {
//				try {
//					br.close();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				br = null;
//			}
//		}
//		
//		return content;
//	}
	
//	public ArrayList<String> getRegexList(String source, String pattern) {
//		return getRegexList(source, pattern, null, (List<String>) null);
//	}
//	
//	public ArrayList<String> getRegexList(String source, String pattern, String replaceRegex) {
//		List<String> replaceRegexList = new ArrayList<String>();
//		List<String> replaceStringList = new ArrayList<String>();
//		
//		replaceRegexList.add(replaceRegex);
//		replaceStringList.add("");
//		
//		return getRegexList(source, pattern, replaceRegexList, replaceStringList);
//	}
//	
//	public ArrayList<String> getRegexList(String source, String pattern, List<String> replaceRegex, String replaceString) {
//		List<String> replaceStringList = new ArrayList<String>();
//		
//		for (int i = 0; i < replaceRegex.size(); i++)
//			replaceStringList.add(replaceString);
//		
//		return getRegexList(source, pattern, replaceRegex, replaceStringList);
//	}
//	
//	public ArrayList<String> getRegexList(String source, String pattern, List<String> replaceRegex) {
//		List<String> replaceStringList = new ArrayList<String>();
//		
//		for (int i = 0; i < replaceRegex.size(); i++)
//			replaceStringList.add("");
//		
//		return getRegexList(source, pattern, replaceRegex, replaceStringList);
//	}
//	
//	public ArrayList<String> getRegexList(String source, String pattern, List<String> replaceRegex, List<String> replaceString) {
//		ArrayList<String> result = new ArrayList<String>();
//		
//		try {
//	        Pattern p= Pattern.compile(pattern);
//	        Matcher m = p.matcher(source);
//	        
//	        if (replaceRegex != null && replaceString != null) {
//	        	String tmp;
//	        	while (m.find()) {
//	        		tmp = m.group();
//	        		for (int i = 0; i < replaceRegex.size(); i++) {
//	        			tmp = tmp.replaceAll(replaceRegex.get(i), replaceString.get(i));
//	        		}
//		        	result.add(tmp);
//		        }
//	        } else {
//	        	while (m.find()) {
//		        	result.add(m.group());
//		        }	
//	        }
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//		
//		return result;
//	}
	
	public int getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public int getReadTimeout() {
		return readTimeout;
	}

	public void setReadTimeout(int readTimeout) {
		this.readTimeout = readTimeout;
	}

	public boolean isDoInput() {
		return doInput;
	}

	public void setDoInput(boolean doInput) {
		this.doInput = doInput;
	}

	public String getRequrestMethod() {
		return requrestMethod;
	}

	public void setRequrestMethod(String requrestMethod) {
		this.requrestMethod = requrestMethod;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	

	public boolean isDebugMode() {
		return debugMode;
	}
	

	public void setDebugMode(boolean debugMode) {
		this.debugMode = debugMode;
	}
}
