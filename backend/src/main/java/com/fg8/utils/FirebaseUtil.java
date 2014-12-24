package com.fg8.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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
	
	static public String getFirebaseRawDataFromRest(String httpUrl) {
		URL url;
		HttpURLConnection conn;
		
		BufferedReader reader;
		String line;
		StringBuffer output = new StringBuffer();
		
		try {
			url = new URL(httpUrl);
	        conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        while ((line = reader.readLine()) != null) {
	        	output.append(line);
	        }
	        reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.err.println("FirebaseUtil.getFirebaseRawDataFromRest() : " + output.toString());
				
		return output.toString();
	}
}
