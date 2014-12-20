package com.fg8.object;

import com.fg8.utils.TimerUtil;

public class Detail {
	private String mContent;
	private long mUpdateDate;
	
	public Detail() {
		
	}
	
	public Detail(String mContent) {
		this.mContent = mContent;
		this.mUpdateDate = TimerUtil.getCurrentTime();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Detail [mContent=");
		builder.append(mContent);
		builder.append(", mUpdateDate=");
		builder.append(mUpdateDate);
		builder.append("]");
		return builder.toString();
	}

	public String getmContent() {
		return mContent;
	}
	
	public void setmContent(String mContent) {
		this.mContent = mContent;
	}
	
	public long getmUpdateDate() {
		return mUpdateDate;
	}
	
	public void setmUpdateDate(long mUpdateDate) {
		this.mUpdateDate = mUpdateDate;
	}
}
