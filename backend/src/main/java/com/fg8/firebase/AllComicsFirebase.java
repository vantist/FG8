package com.fg8.firebase;

import com.fg8.object.Comic;
import com.fg8.object.ComicsList;
import com.fg8.utils.FirebaseUtil;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class AllComicsFirebase {
	ComicsList mAllComics = null;

	public ComicsList getAllComicsList() {
		Firebase firebaseRef = FirebaseUtil.getFirebase();
		
		firebaseRef.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnap) {
				mAllComics = (ComicsList) dataSnap.getValue(ComicsList.class);
				System.out.println(mAllComics);
			}
			
			@Override
			public void onCancelled(FirebaseError error) {
				
			}
		});
		
		if (mAllComics == null)
			mAllComics = new ComicsList();
		
		return mAllComics;
	}
	
	public void getComic(int comicID) {
		
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
