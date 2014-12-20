package com.fg8.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fg8.handler.ComicHandler;
import com.fg8.handler.LatestListHandler;
import com.fg8.object.Comic;
import com.fg8.object.Episode;

@Controller
@RequestMapping("/comic")
public class ComicController {
	ComicHandler mComicHandler = new ComicHandler();

	@RequestMapping(value = "/{comicID:\\d*}", method = RequestMethod.GET)
	public @ResponseBody Comic getComic(@PathVariable("comicID") int comicID) {
		Comic comic = mComicHandler.getComic(comicID);
		return comic;
	}

	@RequestMapping(value = "/{comicID:\\d*}/episode/{episodeIndex:\\d*}", method = RequestMethod.GET)
	public @ResponseBody Episode getEpisode(@PathVariable("comicID") int comicID, @PathVariable("episodeIndex") int episodeIndex) {
		Episode episode = mComicHandler.getEpisode(comicID, episodeIndex);
		return episode;
	}

	@RequestMapping(value = "/latest", method = RequestMethod.GET)
	public @ResponseBody List<Comic> getLstestComicList() {
		LatestListHandler latestListHandler = new LatestListHandler();
		return latestListHandler.refresh();
	}
}
