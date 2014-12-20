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
	
	public List<String> convertEpisodeUrl(List<String> episodeUrlList) {
		int catId;
		String baseUrl = "";
		String newUrl = "";
		List<String> newEpisodeUrlList = new ArrayList<String>();
		
		for (String url : episodeUrlList) {
			catId = Integer.valueOf(url.split(",")[1]);
			baseUrl = "http://new.comicvip.com/show/cool-";
			if (catId == 4 || catId == 6 || catId == 12 || catId == 22)
				baseUrl = "http://new.comicvip.com/show/cool-";
			else if (catId == 1 || catId == 17 || catId == 19 || catId == 21)
				baseUrl = "http://new.comicvip.com/show/cool-";
			else if (catId == 2 || catId == 5 || catId == 7 || catId == 9)  
				baseUrl = "http://new.comicvip.com/show/cool-";
			else if (catId == 10 || catId == 11 || catId == 13 || catId == 14) 
				baseUrl = "http://new.comicvip.com/show/best-manga-";
			else if (catId == 3 || catId == 8 || catId == 15 || catId == 16 || catId == 18 || catId == 20)
				baseUrl = "http://new.comicvip.com/show/best-manga-";
			
			newUrl = baseUrl + url.split(",")[0].replace(".html'", "").replace("-", ".html?ch=");
			newEpisodeUrlList.add(newUrl);
		}
		
		return newEpisodeUrlList;
	}
	
	public List<String> parseAllEpisodeUrl(String comicPageHTML) {
		List<String> replaceRegexList = new ArrayList<String>();
		replaceRegexList.add("cview\\('");
		replaceRegexList.add("\\);return false;");
		
		String pattern = "cview\\(([^<]*?)\\);return false;";
		
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
