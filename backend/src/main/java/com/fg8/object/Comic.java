package com.fg8.object;

import java.util.List;

import com.fg8.utils.TimerUtil;
import com.google.gson.Gson;

public class Comic {
	private int id;
	private String name;
	private String url;
	private String update;
	private long updateDateDB;
	private String author;
	private String picUrl;
	private Detail detail;
	private List<Episode> episodeList;
	
	public Comic() {}
	
	public Comic(int id, String name, String url, String update, String picUrl, String author, Detail detail, List<Episode> episodeList) {
		this.id = id;
		this.name = name;
		this.url = url;
		this.update = update;
		this.updateDateDB = TimerUtil.getCurrentTime();
		this.picUrl = picUrl;
		this.author = author;
		this.detail = detail;
		this.setEpisodeList(episodeList);
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Comic [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", url=");
		builder.append(url);
		builder.append(", update=");
		builder.append(update);
		builder.append(", updateDateDB=");
		builder.append(updateDateDB);
		builder.append(", author=");
		builder.append(author);
		builder.append(", picUrl=");
		builder.append(picUrl);
		builder.append(", detail=");
		builder.append(detail);
		builder.append(", episodeList=");
		builder.append(episodeList);
		builder.append("]");
		return builder.toString();
	}
	
	public static Comic getComic(String json) {
		Comic comic = new Gson().fromJson(json, Comic.class);
		return comic;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public long getUpdateDateDB() {
		return updateDateDB;
	}

	public void setUpdateDateDB(long updateDateDB) {
		this.updateDateDB = updateDateDB;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Detail getDetail() {
		return detail;
	}

	public void setDetail(Detail detail) {
		this.detail = detail;
	}

	public List<Episode> getEpisodeList() {
		return episodeList;
	}

	public void setEpisodeList(List<Episode> episodeList) {
		this.episodeList = episodeList;
	}
}
