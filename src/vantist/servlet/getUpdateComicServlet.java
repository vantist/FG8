package vantist.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.*;

import vantist.parse8comic.Comic;
import vantist.parse8comic.Parse8Comic;

import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class getUpdateComicServlet extends HttpServlet {
	private static final long serialVersionUID = -4491377151170748812L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {	
		JSONObject json = null;
		JSONArray jsonArray = new JSONArray();
		
		Parse8Comic parse8Comic = new Parse8Comic();
		parse8Comic.refreshUpdateComic();
		List<Comic> comics = parse8Comic.getUpdateComic();
		
		for (Comic comic : comics) {
			try {
				json = new JSONObject();
				json.put("URL", comic.getUrl());
				json.put("NAME", comic.getName());
				json.put("DATE", comic.getUpdate());
				json.put("PIC", comic.getPicUrl());
				jsonArray.put(json);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
		String jsonString = jsonArray.toString();
		
		resp.setContentType("text/html; charset=Big5");
		resp.setCharacterEncoding("Big5");
		resp.getWriter().println(jsonString);
	}
}
