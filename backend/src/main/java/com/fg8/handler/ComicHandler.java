package com.fg8.handler;

import java.util.List;

import com.fg8.firebase.AllComicsFirebase;
import com.fg8.object.Comic;
import com.fg8.object.Episode;
import com.fg8.parser.ComicParser;

public class ComicHandler {
	AllComicsFirebase mAllComicsFirebase = new AllComicsFirebase();
	private ComicParser mComicParser = new ComicParser();
	
	public Comic getComic(int comicID) {
		Comic comic = null;
		
		comic = mAllComicsFirebase.getComic(comicID);
		
		if (comic == null) {
			System.err.println("ComicHandler.getComic() : " + comicID + " is not in database.");
			comic = mComicParser.getComic(comicID);
			mAllComicsFirebase.saveComic(comic);
		}
		
		return comic;
	}
	
	public Episode getEpisode(int comicID, int episodeIndex) {
		String comicUrl = mComicParser.getComicUrl(comicID);
		String comicPageHTML = mComicParser.getComicPageHTML(comicUrl);
		List<String> episodeUrlList = mComicParser.getFullAllEpisodeUrl(comicPageHTML);
		List<String> episodeNameList = mComicParser.parseAllEpisodeName(comicPageHTML);
		
		Episode episode = null;
		for (int i = 0; i < episodeUrlList.size(); i++) {
			String episodeName = episodeNameList.get(i);
			String episodeUrl = episodeUrlList.get(i);
			if (episodeUrl.contains("ch=" + episodeIndex)) {
				episode = mComicParser.parseEpisode(episodeUrl, episodeName);
				break;
			}
		}
		return episode;
	}
}
