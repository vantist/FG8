package com.fg8.firebase;

import com.fg8.object.Comic;
import com.fg8.object.ComicsList;
import com.fg8.utils.FirebaseUtil;
import com.firebase.client.Firebase;

public class AllComicsFirebase {
	private static final String URL_END = ".json";
	private static final String ALL_COMICS_URL = "https://fg8.firebaseio.com/ComicsList";
	private static final String COMIC_URL = "https://fg8.firebaseio.com/ComicsList/";
	
		Comic mComic = null;
	
	public ComicsList getAllComicsList() {
		String url = ALL_COMICS_URL.concat(URL_END);
		String allComicsJson = FirebaseUtil.getFirebaseRawDataFromRest(url);
		ComicsList allComics = new ComicsList(allComicsJson);
		return allComics;
	}
	
	public Comic getComic(int comicID) {
		String url = COMIC_URL.concat(Integer.toString(comicID)).concat(URL_END);
		String comicJson = FirebaseUtil.getFirebaseRawDataFromRest(url);
		Comic comic = Comic.getComic(comicJson);
		return comic;
	}
	
	public void saveComic(Comic comic) {
		// get allcomics list add update
		ComicsList allComics = getAllComicsList();
		allComics.saveComic(comic);

		// store into firebase
		Firebase firebaseRef = FirebaseUtil.getFirebase();
		firebaseRef.setValue(allComics);
	}
}
