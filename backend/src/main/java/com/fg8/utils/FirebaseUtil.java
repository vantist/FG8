package com.fg8.utils;

import com.firebase.client.Firebase;

public class FirebaseUtil {
	private static final String FIREBASE_URL = "https://fg8.firebaseio.com/";
	private static final String COMICS_LIST_URL = "ComicsList";

	static public Firebase getFirebase() {
		return new Firebase(FIREBASE_URL); 
	}
	
	static public Firebase getFirebase(String url) {
		return new Firebase(FIREBASE_URL + url); 
	}
	
	static public Firebase getComicsListFirebase() {
		return getFirebase().child(COMICS_LIST_URL);
	}
}
