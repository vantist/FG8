package com.fg8.object;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.google.gson.Gson;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class ComicsList {
	private Map<String, Comic> ComicsList;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ComicsList [mComicList=");
		builder.append(ComicsList);
		builder.append("]");
		return builder.toString();
	}

	public ComicsList() {
		ComicsList = new HashMap<String, Comic>();
	}
	
	@SuppressWarnings("unchecked")
	public ComicsList(String json) {
		ComicsList = new HashMap<String, Comic>();
		ComicsList = (Map<String, Comic>) new Gson().fromJson(json, ComicsList.getClass());
	}
	
	public void addComicsList(Map<String, Comic> comicsList) {
		ComicsList.putAll(comicsList);
	}
	
	public void saveComic(Comic comic) {
		ComicsList.put(Integer.toString(comic.getId()), comic);
	}
	
	public Comic getComic(Comic comic) {
		return ComicsList.get(Integer.toString(comic.getId()));
	}
}
