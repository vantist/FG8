package vantist.parse8comic;

import java.util.ArrayList;
import java.util.List;

import vantist.util.VUtil;

public class Detail {
	private String detailUrl;
	private List<Chapter> chapters = new ArrayList<Chapter>();
	
	private VUtil vUtil;
	
	public Detail(VUtil vUtil, String detailUrl) {
		this.vUtil = vUtil;
		this.detailUrl = detailUrl;
	}
	
	private String getDetailContent() {
		return vUtil.getUrlContent(detailUrl);
	}
	
	private List<String> getUrlList(String content) {
		List<String> replaceRegexList = new ArrayList<String>();
		replaceRegexList.add("cview\\('");
		replaceRegexList.add("\\);return false;");
		
		String pattern = "cview\\(([^<]*?)\\);return false;"; 
		
		return vUtil.getRegexList(content, pattern, replaceRegexList, "");
	}
		
	private List<String> getNameList(String content) {
		List<String> replaceRegexList = new ArrayList<String>();
		replaceRegexList.add("cview\\(([^<])*?\\);return false;\" id=\"([^\"])*?\" class=\"([^\"])*?\">");
		replaceRegexList.add("</a>");
		
		String pattern = "cview\\(([^<])*?\\);return false;\" id=\"([^\"])*?\" class=\"([^\"])*?\">.*?</a>";
		
		return vUtil.getRegexList(content, pattern, replaceRegexList, "");		
	}
	
	private void addChapter(String url, String name) {
		Chapter chapter = new Chapter(this.vUtil, url);
		chapter.setName(name);
		
		chapters.add(chapter);
	}
	
	private void clearChapters() {
		chapters.clear();
	}
	
	public void refresh() {
		String content = "";
		List<String> url;
		List<String> name;
		
		String newUrl;

		String baseUrl;
		int catId;
		
		clearChapters();

		content = getDetailContent();
		url = getUrlList(content);
		name = getNameList(content);
		
		// 最新一集例外處理
		if (name.size() > 0)
			name.set(name.size()-1, name.get(name.size()-1).replaceAll("<font ([^>])*?>", "").replaceAll("<img(.)*?>", "").replaceAll("</font>", "").replaceAll("<script>(.)*?</script>", ""));
		
		for (int i = 0; i < url.size(); i++) {
			catId = Integer.valueOf(url.get(i).split(",")[1]);
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
			
			
			newUrl = baseUrl + url.get(i).split(",")[0].replace(".html'", "").replace("-", ".html?ch=");
			
			addChapter(newUrl, name.get(i).trim());
		}
	}

	public List<Chapter> getChapters() {
		return chapters;
	}

	public void setChapters(List<Chapter> chapters) {
		this.chapters = chapters;
	}

	public String getDetailUrl() {
		return detailUrl;
	}

	public void setDetailUrl(String detailUrl) {
		this.detailUrl = detailUrl;
	}
}
