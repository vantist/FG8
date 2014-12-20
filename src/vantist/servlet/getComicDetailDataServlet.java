package vantist.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vantist.parse8comic.Chapter;
import vantist.parse8comic.Detail;
import vantist.parse8comic.Parse8Comic;

import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

/*
 * 取得漫畫所有集數, page等資訊
 */
@SuppressWarnings("serial")
public class getComicDetailDataServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		String URL = req.getParameter("URL");
		Parse8Comic parse8Comic = new Parse8Comic();
		Detail detail = parse8Comic.getSingleDetail(URL);
		List<Chapter> chapters = detail.getChapters();
		
		JSONArray jsonArray = new JSONArray();
		JSONObject json;
		
		for (Chapter chapter : chapters) {
			try {
				json = new JSONObject();
				json.put("URL", chapter.getUrl());
				json.put("NAME", chapter.getName());
				jsonArray.put(json);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		resp.setContentType("text/html; charset=Big5");
		resp.setCharacterEncoding("Big5");
		resp.getWriter().println( jsonArray.toString() );
	}
}
