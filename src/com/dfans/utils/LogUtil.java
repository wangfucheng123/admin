package com.dfans.utils;

import javax.servlet.http.HttpServletRequest;

import com.dfans.model.Admin;
import com.dfans.model.LogModel; 
public class LogUtil  { 
	public static LogModel recordLog(HttpServletRequest request, String classname, String mothod, String msg){
		LogModel log=new LogModel();
		log.setClassname(classname);
		log.setMothod(mothod);
		log.setMsg(((Admin)request.getSession().getAttribute("admin")).getName()+msg);
		log.setLogname(((Admin)request.getSession().getAttribute("admin")).getName());
		log.setUsername(((Admin)request.getSession().getAttribute("admin")).getRealname());
		return log;
	} 
}
