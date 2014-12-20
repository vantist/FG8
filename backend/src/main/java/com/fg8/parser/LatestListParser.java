package com.fg8.parser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fg8.object.Comic;
import com.fg8.object.Detail;
import com.fg8.utils.RegUtil;
import com.fg8.utils.WebUtil;

public class LatestListParser {
	private final long mRefreshInterval = 300000;
	private final String mUpdateURL = "http://www.8comic.com/comic/u-?.html";
	private final String mUrlReplaceStr = "?";
	private List<Comic> mLatestComicList = new ArrayList<Comic>();
	private List<String> mLatestComicPageList = new ArrayList<String>();
	private long mLatestRefreshTime = 0;
	
	public LatestListParser() {
	}
	
	// unusable
	private boolean isOverRefreshInterval() {
		System.out.println(new Date().getTime() - mLatestRefreshTime);
		if (new Date().getTime() - mLatestRefreshTime > mRefreshInterval)
			return true;
		return false;
	}
	
	// 擷取 http://www.8comic.com/comic/u-(1~5).html 內的網頁資料
	private List<String> getLatestComicHTML() {
		if (!mLatestComicPageList.isEmpty() && !isOverRefreshInterval()) {
			return mLatestComicPageList;
		}
		
		mLatestComicPageList.clear();
		
		for (int i = 1; i <= 5; i++) {
			String currentUpdateURL = mUpdateURL.replace(mUrlReplaceStr, Integer.toString(i));
			String latestPage = WebUtil.getUrlContent(currentUpdateURL);
			if (!latestPage.trim().isEmpty())
				mLatestComicPageList.add(latestPage);
		}
		
		return mLatestComicPageList;
	}
	
	// 取得 comic 的 url
	private List<String> parseComicURL(List<String> latestPageList) {
		List<String> replaceRegexList = new ArrayList<String>();
		replaceRegexList.add("<td height=\"30\" nowrap> · <a href='");
		replaceRegexList.add("' >");
		
		String pattern = "<td height=\"30\" nowrap> · <a href='[^<]*?' >"; 
		
		List<String> urlList = new ArrayList<String>();
		
		for (String latestPage : latestPageList) {
			List<String> urls = RegUtil.getRegexList(latestPage, pattern, replaceRegexList, "");
			urlList.addAll(urls);
		}
		
		return urlList;
	}
	
	// 取得 comic 的名稱
	private List<String> parseComicName(List<String> latestPageList) {
		List<String> replaceRegexList = new ArrayList<String>();
		replaceRegexList.add("<td height=\"30\" nowrap> · <a href='[^<]*?' >");
		replaceRegexList.add("</a>");
		
		String pattern = "<td height=\"30\" nowrap> · <a href='[^<]*?' >[^<]*?</a>";
		
		List<String> nameList = new ArrayList<String>();
		
		for (String latestPage : latestPageList) {
			List<String> names = RegUtil.getRegexList(latestPage, pattern, replaceRegexList, "");
			nameList.addAll(names);
		}
		
		return nameList;
	}
	
	// 取得 comic 的更新日期
	private List<String> parseComicUpdateDateList(List<String> latestPageList) {
		List<String> replaceRegexList = new ArrayList<String>();
		replaceRegexList.add("<td align=\"right\" nowrap>");
		replaceRegexList.add("</td>");
		
		String pattern = "<td align=\"right\" nowrap>[^<]*?</td>";
		
		List<String> updateDateList = new ArrayList<String>();
		
		for (String latestPage : latestPageList) {
			List<String> dates = RegUtil.getRegexList(latestPage, pattern, replaceRegexList, "");
			updateDateList.addAll(dates);
		}
		
		return updateDateList;
	}
	
	private Comic createComic(String url, String name, String update, String picUrl) {
		Detail detail = new Detail(url);
		Comic comic = new Comic(name, url, update, picUrl, detail);
		
		return comic;
	}
	
	public List<Comic> addComic(Comic comic) {
		mLatestComicList.add(comic);
		return mLatestComicList;
	}
	
	public List<Comic> getLatestComicList() {
		return mLatestComicList;
	}
	
	public void clearLatestComicList() {
		mLatestComicList.clear();
	}
	
	public void refresh() {
		List<String> latestComicPage = new ArrayList<String>();
		List<String> urlList;
		List<String> nameList;
		List<String> updateList;
		
		String picUrl;
		String newUrl;
		//TODO
		clearLatestComicList();
		latestComicPage = getLatestComicHTML();
		urlList = parseComicURL(latestComicPage);
		nameList = parseComicName(latestComicPage);
		updateList = parseComicUpdateDateList(latestComicPage);
		
		for (int i = 0; i < urlList.size(); i++) {
			picUrl = "http://www.8comic.com/pics/0/" + urlList.get(i).replaceAll("/html/", "").replace(".html", "") + ".jpg";
			newUrl = "http://www.8comic.com" + urlList.get(i);
			
			Comic comic = createComic(newUrl, nameList.get(i), updateList.get(i), picUrl);
			addComic(comic);
		}
		
		mLatestRefreshTime = new Date().getTime();
	}
}
