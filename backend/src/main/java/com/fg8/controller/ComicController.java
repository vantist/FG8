package com.fg8.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fg8.parser.ComicLatestParser;
import com.fg8.utils.Comic;

@Controller
@RequestMapping("/comic")
public class ComicController {

	@RequestMapping(value = "/latest", method = RequestMethod.GET)
	public @ResponseBody List<Comic> getLstestComicList() {
		ComicLatestParser comicLatestParser = new ComicLatestParser();
		return comicLatestParser.getLatestComicList();
	}
}
