package com.fg8.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegUtil {
	
	static public ArrayList<String> getRegexList(String source, String pattern) {
		return getRegexList(source, pattern, null, (List<String>) null);
	}
	
	static public ArrayList<String> getRegexList(String source, String pattern, String replaceRegex) {
		List<String> replaceRegexList = new ArrayList<String>();
		List<String> replaceStringList = new ArrayList<String>();
		
		replaceRegexList.add(replaceRegex);
		replaceStringList.add("");
		
		return getRegexList(source, pattern, replaceRegexList, replaceStringList);
	}
	
	static public ArrayList<String> getRegexList(String source, String pattern, List<String> replaceRegex, String replaceString) {
		List<String> replaceStringList = new ArrayList<String>();
		
		for (int i = 0; i < replaceRegex.size(); i++)
			replaceStringList.add(replaceString);
		
		return getRegexList(source, pattern, replaceRegex, replaceStringList);
	}
	
	static public ArrayList<String> getRegexList(String source, String pattern, List<String> replaceRegex) {
		List<String> replaceStringList = new ArrayList<String>();
		
		for (int i = 0; i < replaceRegex.size(); i++)
			replaceStringList.add("");
		
		return getRegexList(source, pattern, replaceRegex, replaceStringList);
	}
	
	static public ArrayList<String> getRegexList(String source, String pattern, List<String> replaceRegex, List<String> replaceString) {
		ArrayList<String> result = new ArrayList<String>();
		
		try {
	        Pattern p= Pattern.compile(pattern);
	        Matcher m = p.matcher(source);
	        
	        if (replaceRegex != null && replaceString != null) {
	        	String tmp;
	        	while (m.find()) {
	        		tmp = m.group();
	        		for (int i = 0; i < replaceRegex.size(); i++) {
	        			tmp = tmp.replaceAll(replaceRegex.get(i), replaceString.get(i));
	        		}
		        	result.add(tmp);
		        }
	        } else {
	        	while (m.find()) {
		        	result.add(m.group());
		        }	
	        }
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
