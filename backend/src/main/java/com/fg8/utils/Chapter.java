package com.fg8.utils;

import java.util.ArrayList;
import java.util.List;

public class Chapter {
	private String url;
	private String name;
	private List<String> picUrlList = new ArrayList<String>();
	
	public Chapter(String url) {
		System.out.println(url);
		this.url = url.replace("\\u003d", "=");
		System.out.println(this.url);
	}
	
	private String getPicContent() {
		return WebUtil.getUrlContent(url);
	}
	
	private void addPicUrl(String picUrl) {
		picUrlList.add(picUrl);
	}
	
	private void clearPicUrlList() {
		picUrlList.clear();
	}
	
	private String ss(String a, int b, int c, Boolean d) {
		String e = a.substring(b, b + c);
		return d ? e.replaceAll("[a-z]*", "") : e;
	}
	
	private String nn(int n) {
		return n < 10 ? ("00" + n) : n < 100 ? ("0" + n) : Integer.toString(n); 
	}
	
	private int mm(int p) {
		return (((p-1) / 10) % 10) + (((p-1) % 10) *3);
	}
	
	private int getChs(String content) {
		String pattern = "var chs=([0-9])*;";
		
		return Integer.parseInt(RegUtil.getRegexList(content, pattern).get(0).split("[=;]")[1]);
	}
	
	private String parseComicName(String content) {
		String pattern = ":\\[(.)*<font id=";
		
		return RegUtil.getRegexList(content, pattern).get(0).split(" ")[1];
	}
	
	private int getTi(String content) {
		String pattern = "var ti=(.)*;";
		
		return Integer.parseInt(RegUtil.getRegexList(content, pattern).get(0).split("[=;]")[1]);
	}
	
	private String getCs(String content) {
		String pattern = "var cs=(.)*;";
		
		return RegUtil.getRegexList(content, pattern).get(0).split("'")[1];
	}
	
	public void refresh() {
		String content = getPicContent();
		
		int ch = Integer.parseInt(url.split("=")[1]);
		int chs = getChs(content);
		int ti = getTi(content);
		String cs = getCs(content);
		
		name = parseComicName(content);
		
		int cc = cs.length();
		int f = 50;
		String c = "";
//		int ci = 0;
		
		for (int i = 0; i < cc / f; i++) {
			if (Integer.parseInt(ss(cs,  i * f,  4,  true)) == ch) {
				c = ss(cs, i * f, f, false);
//				ci = i;
				break;
			}
		}
		
		if (c.equals("")) {
			c = ss(cs, cc - f, f, true);
			ch = chs;
		}
		
		int ps = Integer.parseInt(ss(c, 7, 3, true));

		clearPicUrlList();
		for (int i = 1; i <= ps; i++) {
			addPicUrl("http://img" + ss(c, 4, 2, true) + ".8comic.com/" + ss(c, 6, 1, true) + "/" + ti + "/" + ss(c, 0, 4, true) + "/" + nn(i) + '_' + ss(c, mm(i) + 10, 3, false) + ".jpg");
		}
	}

	public List<String> getPicUrlList() {
		return picUrlList;
	}

	public void setPicUrlList(List<String> picUrlList) {
		this.picUrlList = picUrlList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
