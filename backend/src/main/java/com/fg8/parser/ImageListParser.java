package com.fg8.parser;

import java.util.ArrayList;
import java.util.List;

import com.fg8.utils.RegUtil;
import com.fg8.utils.WebUtil;

public class ImageListParser {
	List<String> mImageList;

	public List<String> getImageList(String episodeUrl) {		
		String episodePageHTML = WebUtil.getUrlContent(episodeUrl);

		int ch = Integer.parseInt(episodeUrl.split("=")[1]);
		int chs = getChs(episodePageHTML);
		int ti = getTi(episodePageHTML);
		String cs = getCs(episodePageHTML);
		
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

		mImageList = new ArrayList<String>();
		for (int i = 1; i <= ps; i++) {
			mImageList.add("http://img" + ss(c, 4, 2, true) + ".8comic.com/" + ss(c, 6, 1, true) + "/" + ti + "/" + ss(c, 0, 4, true) + "/" + nn(i) + '_' + ss(c, mm(i) + 10, 3, false) + ".jpg");
		}
		
		return mImageList;
	}
	
	private int getChs(String content) {
		String pattern = "var chs=([0-9])*;";
		
		return Integer.parseInt(RegUtil.getRegexList(content, pattern).get(0).split("[=;]")[1]);
	}
	
	private int getTi(String content) {
		String pattern = "var ti=(.)*;";
		
		return Integer.parseInt(RegUtil.getRegexList(content, pattern).get(0).split("[=;]")[1]);
	}
	
	private String getCs(String content) {
		String pattern = "var cs=(.)*;";
		
		return RegUtil.getRegexList(content, pattern).get(0).split("'")[1];
	}
	
	// parse comic name
	private String parseComicName(String content) {
		String pattern = ":\\[(.)*<font id=";
		
		return RegUtil.getRegexList(content, pattern).get(0).split(" ")[1];
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
}
