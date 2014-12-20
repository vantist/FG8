package com.fg8.database;

import java.util.ArrayList;
import java.util.List;

import com.fg8.object.Comic;

public class LatestListDatabase {

	public List<Comic> getLatestList() {
		return new ArrayList<Comic>();
	}
	
	public List<Comic> updateLatestList(List<Comic> latestList) {
		// update
		return getLatestList();
	}

	
	public long getLatestUpdateDate() {
		return 0;
	}
	
	public long updateLatestUpdateDate(long dateTime) {
		// set dateTime
		return dateTime;
	}
}
