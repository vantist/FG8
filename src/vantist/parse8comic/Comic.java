package vantist.parse8comic;

public class Comic {
	private String name;
	private String url;
	private String update;
	private String picUrl;
	private Detail detail;
	
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
