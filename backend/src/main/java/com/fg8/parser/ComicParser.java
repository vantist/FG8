package com.fg8.parser;

import java.util.ArrayList;
import java.util.List;

import com.fg8.object.Comic;
import com.fg8.object.Detail;
import com.fg8.object.Episode;
import com.fg8.utils.RegUtil;
import com.fg8.utils.TimerUtil;
import com.fg8.utils.WebUtil;

public class ComicParser {
	private static final String COMIC_URL_PREFIX = "http://www.8comic.com/html/";
	private static final String COMIC_URL_POSTFIX = ".html";
	private static final String COMIC_PIC_URL_PREFIX = "http://www.8comic.com/pics/0/";
	private static final String COMIC_PIC_URL_POSTFIX = ".jpg";
	List<Episode> mEpisodeList;

	public String getComicUrl(int id) {
		String comicUrl = COMIC_URL_PREFIX.concat(Integer.toString(id)).concat(COMIC_URL_POSTFIX);
		return comicUrl;
	}
	
	public String getComicPicUrl(int id) {
		String comicPicUrl = COMIC_PIC_URL_PREFIX.concat(Integer.toString(id)).concat(COMIC_PIC_URL_POSTFIX);
		return comicPicUrl;
	}
	
	public Comic getComic(int comicID) {
		String comicUrl = getComicUrl(comicID);
		String comicPageHTML = WebUtil.getUrlContent(comicUrl);
		String name = parserName(comicPageHTML);
		String updateDate = parseUpdate(comicPageHTML);
		String picUrl = getComicPicUrl(comicID);
		String author = parseAuthor(comicPageHTML);
		Detail detail = parseDetail(comicPageHTML);
		List<Episode> episodeList = getEpisodeLiteList(comicUrl);
		
		Comic comic = new Comic(comicID, name, comicUrl, updateDate, picUrl, author, detail, episodeList);
		return comic;
	}
	
	private String parserName(String comicPageHTML) {
		List<String> replaceRegexList = new ArrayList<String>();
		replaceRegexList.add("<font color=\"#FF6600\" style=\"font:12pt;font-weight:bold;\">");
		replaceRegexList.add("</font>");
		
		String pattern = "<font color=\"#FF6600\" style=\"font:12pt;font-weight:bold;\">[^<]*?</font>";
		List<String> names = RegUtil.getRegexList(comicPageHTML, pattern, replaceRegexList, "");
		if (names.size() > 0)
			return names.get(0);
		else
			return "comic name not found";
	}
	
	private String parseAuthor(String comicPageHTML) {
		List<String> replaceRegexList = new ArrayList<String>();
		replaceRegexList.add("<font color=\"#0099CC\" style=\"font-weight:bold;\">");
		replaceRegexList.add("</font>");
		
		String pattern = "<font color=\"#0099CC\" style=\"font-weight:bold;\">[^<]*?</font>";
		List<String> authors = RegUtil.getRegexList(comicPageHTML, pattern, replaceRegexList, "");
		if (authors.size() > 0)
			return authors.get(0);
		else
			return "comic author not found";
	}
	
	public String parseUpdate(String comicPageHTML) {
		List<String> replaceRegexList = new ArrayList<String>();
		replaceRegexList.add("<td nowrap=\"nowrap\">");
		replaceRegexList.add("</td>");
		
		String pattern = "<td nowrap=\"nowrap\">[^<]*?</td>";
		List<String> update = RegUtil.getRegexList(comicPageHTML, pattern, replaceRegexList, "");
		
		if (update.size() > 0)
			return update.get(0);
		else
			return "comic update not found";
	}
	
	public Detail parseDetail(String comicPageHTML) {
		List<String> replaceRegexList = new ArrayList<String>();
		replaceRegexList.add("<td colspan=\"3\" valign=\"top\" bgcolor=\"f0f8ff\" style=\"padding:10px;line-height:25px\">");
		replaceRegexList.add("</td>");
		
		String pattern = "<td colspan=\"3\" valign=\"top\" bgcolor=\"f0f8ff\" style=\"padding:10px;line-height:25px\">[^*]*?</td>";
		List<String> detailContents = RegUtil.getRegexList(comicPageHTML, pattern, replaceRegexList, "");
		String detailContent;
		if (detailContents.size() > 0)
			detailContent = detailContents.get(0).replace("<br>", "").replace("&nbsp;", "");
		else
			detailContent = "comic detail not found";
		
		Detail detail = new Detail(detailContent);
		return detail;
	}
	
	public List<Episode> getEpisodeLiteList(String comicUrl) {
		if (mEpisodeList != null)
			mEpisodeList.clear();
		else
			mEpisodeList = new ArrayList<Episode>();
		
		String comicPageHTML = getComicPageHTML(comicUrl);
		List<String> allEpisodeUrlList = getFullAllEpisodeUrl(comicPageHTML);
		List<String> allEpisodeNameList = parseAllEpisodeName(comicPageHTML);
		
		if (allEpisodeUrlList.size() != allEpisodeNameList.size())
			System.out.println("allEpisodeUrlList.size():" + allEpisodeUrlList.size() + " != allEpisodeNameList.size():" + allEpisodeNameList.size());
		
		for (int i = 0; i < allEpisodeUrlList.size(); i++) {
			String episodeUrl = allEpisodeUrlList.get(i);
			String episodeName = allEpisodeNameList.get(i);
			int index = Integer.parseInt(episodeUrl.split("ch=")[1]);
			Episode episode = new Episode(index, 0, 0, episodeName, episodeUrl, null);
			mEpisodeList.add(episode);
		}
		
		return mEpisodeList;
	}
	
	public String getComicPageHTML(String comicUrl) {
		String comicPageHTML = WebUtil.getUrlContent(comicUrl);
		return comicPageHTML;
	}
	
	public List<String> getFullAllEpisodeUrl(String comicPageHTML) {
		List<String> rawEpisodeUrl = parseAllEpisodeUrl(comicPageHTML);
		List<String> fullEpisodeUrl = convertEpisodeUrl(rawEpisodeUrl);
		return fullEpisodeUrl;
	}
	
	public String getEpisodeBaseUrl(int catId) {
		String baseUrl = "http://new.comicvip.com/show/cool-";
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
		return baseUrl;
	}
	
	public List<String> convertEpisodeUrl(List<String> episodeUrlList) {
		int catId;
		String baseUrl = "";
		String newUrl = "";
		List<String> newEpisodeUrlList = new ArrayList<String>();
		
		for (String url : episodeUrlList) {
			catId = Integer.valueOf(url.split(",")[1]);
			baseUrl = getEpisodeBaseUrl(catId);
			
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
		
		// the latest episode's name is different with other
		if (allEpisodeName.size() > 0)
			allEpisodeName.set(allEpisodeName.size()-1, allEpisodeName.get(allEpisodeName.size()-1).replaceAll("<font ([^>])*?>", "").replaceAll("<img(.)*?>", "").replaceAll("</font>", "").replaceAll("<script>(.)*?</script>", ""));
		
		return allEpisodeName;
	}
	
	
	public Episode parseEpisode(String episodeUrl, String episodeName) {
		int index = Integer.parseInt(episodeUrl.split("ch=")[1]);
		long updateDate = TimerUtil.getCurrentTime();
		List<String> imageUrlList = new ImageListParser().getImageList(episodeUrl);
		
		Episode espisode = new Episode(index, imageUrlList.size(), updateDate, episodeName, episodeUrl, imageUrlList);
		return espisode;
	}
}
