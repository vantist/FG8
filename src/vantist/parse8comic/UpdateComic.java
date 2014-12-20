package vantist.parse8comic;

import java.util.ArrayList;
import java.util.List;

import vantist.util.VUtil;

public class UpdateComic {
	private final String updateURL = "http://www.8comic.com/comic/u-?.html";
	private final String urlReplaceStr = "?";
	
	private VUtil vUtil;
	private List<Comic> comics = new ArrayList<Comic>();
	
	public UpdateComic(VUtil vUtil) {
		this.vUtil = vUtil;
	}

	private String getUpdateContent(int index) {
		return vUtil.getUrlContent(updateURL.replace(urlReplaceStr, Integer.toString(index)));
	}
		
	private List<String> getUrlList(String content) {
		List<String> replaceRegexList = new ArrayList<String>();
		replaceRegexList.add("<td height=\"30\" nowrap> · <a href='");
		replaceRegexList.add("' >");
		
		String pattern = "<td height=\"30\" nowrap> · <a href='[^<]*?' >"; 
		
		return vUtil.getRegexList(content, pattern, replaceRegexList, "");
	}
	
	private List<String> getNameList(String content) {
		List<String> replaceRegexList = new ArrayList<String>();
		replaceRegexList.add("<td height=\"30\" nowrap> · <a href='[^<]*?' >");
		replaceRegexList.add("</a>");
		
		String pattern = "<td height=\"30\" nowrap> · <a href='[^<]*?' >[^<]*?</a>";
		
		return vUtil.getRegexList(content, pattern, replaceRegexList, "");		
	}
	
	private List<String> getUpdateList(String content) {
		List<String> replaceRegexList = new ArrayList<String>();
		replaceRegexList.add("<td align=\"right\" nowrap>");
		replaceRegexList.add("</td>");
		
		String pattern = "<td align=\"right\" nowrap>[^<]*?</td>";
		
		return vUtil.getRegexList(content, pattern, replaceRegexList, "");	
	}
	
	private void addComic(String url, String name, String update, String picUrl) {
		Comic comic = new Comic();
		comic.setUrl(url);
		comic.setName(name);
		comic.setUpdate(update);
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
		List<String> update;
		
		String picUrl;
		String newUrl;
		
		clearComics();
		for (int i = 0; i < 5; i++) {
			content = getUpdateContent(i+1);
			url = getUrlList(content);
			name = getNameList(content);
			update = getUpdateList(content);
			
			for (int j = 0; j < url.size(); j++) {
				picUrl = "http://www.8comic.com/pics/0/" + url.get(j).replaceAll("/html/", "").replace(".html", "") + ".jpg";
				newUrl = "http://www.8comic.com" + url.get(j);
				
				addComic(newUrl, name.get(j), update.get(j), picUrl);
			}
		}
	}
	
	public List<Comic> getComics() {
		return comics;
	}
}