package vantist.parse8comic;

import java.util.ArrayList;
import java.util.List;

import vantist.util.VUtil;

public class AllComic {
	private final String comicURL = "http://www.8comic.com/comic/all.html";
	
	private VUtil vUtil;
	private List<Comic> comics = new ArrayList<Comic>();
	
	public AllComic(VUtil vUtil) {
		this.vUtil = vUtil;
	}

	private String getUpdateContent() {
		return vUtil.getUrlContent(comicURL);
	}
	
	private List<String> getUrlList(String content) {
		List<String> replaceRegexList = new ArrayList<String>();
		replaceRegexList.add("<a href=\"");
		replaceRegexList.add("\" onmouseover");
		
		String pattern = "<a href=\"([^<]*?)\" onmouseover"; 
		
		return vUtil.getRegexList(content, pattern, replaceRegexList, "");
	}
	
	private List<String> getPicList(String content) {
		List<String> replaceRegexList = new ArrayList<String>();
		replaceRegexList.add("onmouseover='showthumb\\(");
		replaceRegexList.add(",this");
		
		String pattern = "onmouseover='showthumb\\(([^<]*?),this";
		
		return vUtil.getRegexList(content, pattern, replaceRegexList, "");	
	}
	
	private List<String> getNameList(String content) {
		List<String> replaceRegexList = new ArrayList<String>();
		replaceRegexList.add("'hidethumb\\(\\);'>");
		replaceRegexList.add("</a>");
		
		String pattern = "'hidethumb\\(\\);'>([^<])*?</a>";
		
		return vUtil.getRegexList(content, pattern, replaceRegexList, "");		
	}
	
	private void addComic(String url, String name, String picUrl) {
		Comic comic = new Comic();
		comic.setUrl(url);
		comic.setName(name);
		comic.setPicUrl(picUrl);
		
		Detail detail = new Detail(vUtil, url);
		comic.setDetail(detail);

		comics.add(comic);
	}
	
	private void clearComics() {
		comics.clear();
	}
	
	public void refresh() {
		String content = "";
		List<String> url;
		List<String> name;
		List<String> picUrl;
		
		String newPicUrl;
		String newUrl;
		
		clearComics();

		content = getUpdateContent();
		url = getUrlList(content);
		name = getNameList(content);
		picUrl = getPicList(content);
		
		for (int i = 0; i < url.size(); i++) {
			newPicUrl = "http://www.8comic.com/pics/0/" + picUrl.get(i) + ".jpg";
			newUrl = "http://www.8comic.com" + url.get(i);
			
			addComic(newUrl, name.get(i), newPicUrl);
		}
	}
	
	public List<Comic> getComics() {
		return comics;
	}
}
