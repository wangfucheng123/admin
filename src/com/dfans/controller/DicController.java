package com.dfans.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dfans.dao.DicMapper;
import com.dfans.model.Dic;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller 
@RequestMapping("/dic")
public class DicController {
	
	@Autowired
	private DicMapper dicDao;
	
	private static Gson gson = new GsonBuilder().create();

	@RequestMapping("getDicType")
	@ResponseBody
	public String getUserLogin(HttpServletRequest request) {
		List<Dic> ret=dicDao.selectByType(Integer.parseInt(request.getParameter("type")));
		return gson.toJson(ret);
	}
	
	
	@RequestMapping("getDicById")
	@ResponseBody
	public String getDicById(HttpServletRequest request) {
		List<Dic> ret=dicDao.getDicById(Integer.parseInt(request.getParameter("id")));
		return gson.toJson(ret);
	}
}
