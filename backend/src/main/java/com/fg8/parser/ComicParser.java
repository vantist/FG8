package com.fg8.parser;

import java.util.ArrayList;
import java.util.List;

import com.fg8.object.Detail;
import com.fg8.object.Episode;
import com.fg8.utils.RegUtil;
import com.fg8.utils.WebUtil;

public class ComicParser {
	String url = "http://www.8comic.com/html/8279.html";
	
	
	public String getComicPageHTML(String comicUrl) {
		String comicPageHTML = WebUtil.getUrlContent(comicUrl);
		return comicPageHTML;
	}
	
//	public String parseDetail(String comicPageHTML) {
//		//Detail detail = new Detail();
//	}
	
//	public String parseChapter(String comicPageHTML) {
//		
//	}
	
	public List<String> parseAllEpisodeUrl(String comicPageHTML) {
		List<String> replaceRegexList = new ArrayList<String>();
		replaceRegexList.add("cview\\('");
		replaceRegexList.add("\\);return false;");
		
		String pattern = "cview\\(([^<]*?)\\);return false;"; 
		
		return RegUtil.getRegexList(comicPageHTML, pattern, replaceRegexList, "");
	}
	
//	public String parseEpisode(String comicPageHTML) {
//		Episode espisode = new Episode();
//		
//		
//	}
}
