package com.fg8.object;

public class Comic {
	private String name;
	private String url;
	private String update;
	private String picUrl;
	private Detail detail;
	
	public Comic() {}
	
	public Comic(String name, String url, String updateDate, String picUrl, Detail detail) {
		this.name = name;
		this.url = url;
		this.update = updateDate;
		this.picUrl = picUrl;
		this.detail = detail;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Comic [name=");
		builder.append(name);
		builder.append(", url=");
		builder.append(url);
		builder.append(", update=");
		builder.append(update);
		builder.append(", picUrl=");
		builder.append(picUrl);
		builder.append(", detail=");
		builder.append(detail);
		builder.append("]");
		return builder.toString();
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

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public Detail getDetail() {
		return detail;
	}

	public void setDetail(Detail detail) {
		this.detail = detail;
	}
}
