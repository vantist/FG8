package com.fg8.handler;

import com.fg8.firebase.AllComicsFirebase;
import com.fg8.object.ComicsList;

public class AllComicsHandler {
	AllComicsFirebase mAllComicsFirebase = new AllComicsFirebase();
	
	public ComicsList getAllComics() {
		return mAllComicsFirebase.getAllComicsList();
	}
}
