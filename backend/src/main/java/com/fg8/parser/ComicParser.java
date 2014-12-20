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
		replaceRegexList.add("',13\\);return false;");
		
		String pattern = "cview\\(([^<]*?)',13\\);return false;";
		
		return RegUtil.getRegexList(comicPageHTML, pattern, replaceRegexList, "");
	}
	
	public List<String> parseAllEpisodeName(String comicPageHTML) {
		List<String> replaceRegexList = new ArrayList<String>();
		replaceRegexList.add("cview\\(([^<])*?\\);return false;\" id=\"([^\"])*?\" class=\"([^\"])*?\">");
		replaceRegexList.add("</a>");
		
		String pattern = "cview\\(([^<])*?\\);return false;\" id=\"([^\"])*?\" class=\"([^\"])*?\">.*?</a>";

		List<String> allEpisodeName = RegUtil.getRegexList(comicPageHTML, pattern, replaceRegexList, "");
		
		// the latest episode's name is diff with other
		if (allEpisodeName.size() > 0)
			allEpisodeName.set(allEpisodeName.size()-1, allEpisodeName.get(allEpisodeName.size()-1).replaceAll("<font ([^>])*?>", "").replaceAll("<img(.)*?>", "").replaceAll("</font>", "").replaceAll("<script>(.)*?</script>", ""));
		
		return allEpisodeName;
	}
	
//	public String parseEpisode(String comicPageHTML) {
//		Episode espisode = new Episode();
//		
//		
//	}
}
