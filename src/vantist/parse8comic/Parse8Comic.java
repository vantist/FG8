package vantist.parse8comic;

import java.util.List;

import vantist.util.VUtil;

public class Parse8Comic {
	private VUtil vUtil = new VUtil();
	private UpdateComic updateComic = new UpdateComic(vUtil);
	private AllComic allComic = new AllComic(vUtil);
	
	public List<Comic> getUpdateComic() {
		return updateComic.getComics();
	}
	
	public void refreshUpdateComic() {
		updateComic.refresh();
	}
	
	public List<Comic> getAllComic() {
		return allComic.getComics();
	}
	
	public void refreshAllComic() {
		allComic.refresh();
	}
	
	public Chapter getSingleChapter(String picUrl) {
		Chapter chapter = new Chapter(vUtil, picUrl);
		chapter.refresh();
		
		return chapter;
	}
	
	public Detail getSingleDetail(String detailUrl) {
		Detail detail = new Detail(vUtil, detailUrl);
		detail.refresh();
		return detail;
	}
}
