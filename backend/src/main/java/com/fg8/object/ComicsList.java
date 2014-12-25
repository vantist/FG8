package com.fg8.object;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

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
	
	public ComicsList(String json) {
		ComicsList = new HashMap<String, Comic>();
		ObjectMapper mapper = new ObjectMapper();
		try {
			ComicsList = mapper.readValue(json, new TypeReference<HashMap<String, Comic>>(){});
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
