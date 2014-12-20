package com.fg8.object;

import java.util.ArrayList;
import java.util.List;

public class Episode {
	private int id;
	private int index;
	private int pageNumber;
	private long updateDate;
	private String name;
	private String url;
	private List<String> imageUrlList = new ArrayList<String>();
	
	public Episode() {}
	
	public Episode(int id, int index, int pageNumber, long updateDate,
			String name, String url, List<String> imageUrlList) {
		this.id = id;
		this.index = index;
		this.pageNumber = pageNumber;
		this.updateDate = updateDate;
		this.name = name;
		this.url = url;
		this.imageUrlList = imageUrlList;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Episode [id=");
		builder.append(id);
		builder.append(", index=");
		builder.append(index);
		builder.append(", pageNumber=");
		builder.append(pageNumber);
		builder.append(", updateDate=");
		builder.append(updateDate);
		builder.append(", name=");
		builder.append(name);
		builder.append(", url=");
		builder.append(url);
		builder.append(", imageUrlList=");
		builder.append(imageUrlList);
		builder.append("]");
		return builder.toString();
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getIndex() {
		return index;
	}
	
	public void setIndex(int index) {
		this.index = index;
	}
	
	public int getPageNumber() {
		return pageNumber;
	}
	
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	
	public long getUpdateDate() {
		return updateDate;
	}
	
	public void setUpdateDate(long updateDate) {
		this.updateDate = updateDate;
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
	
	public List<String> getImageUrlList() {
		return imageUrlList;
	}
	
	public void setImageUrlList(List<String> imageUrlList) {
		this.imageUrlList = imageUrlList;
	}
}
