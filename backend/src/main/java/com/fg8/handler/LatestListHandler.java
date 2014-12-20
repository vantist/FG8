package com.fg8.handler;

import java.util.List;

import com.fg8.database.LatestListDatabase;
import com.fg8.object.Comic;
import com.fg8.parser.LatestListParser;
import com.fg8.utils.TimerUtil;

public class LatestListHandler {
	private static final long REFRESH_INTERVAL = 1800000; // 30 mins
	private LatestListDatabase mLatestListDatabase;
	private LatestListParser mLatestListParser;
	
	public LatestListHandler() {
		mLatestListDatabase = new LatestListDatabase();
		mLatestListParser = new LatestListParser();
	}
	
	private boolean isOverInterval() {
		if (TimerUtil.getCurrentTime() - mLatestListDatabase.getLatestUpdateDate() > REFRESH_INTERVAL) {
			return true;
		}
		return false;
	}
	
	public List<Comic> refresh() {
		List<Comic> latestList = mLatestListDatabase.getLatestList();
		if (latestList.isEmpty() || isOverInterval()) {
			mLatestListParser.refresh();
			latestList = mLatestListParser.getLatestComicList();
		}
		
		// thread
		mLatestListDatabase.updateLatestList(latestList);
		
		return latestList;
	}
}
