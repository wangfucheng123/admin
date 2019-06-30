package com.dfans.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dfans.dao.AdminMapper;
import com.dfans.dao.SJNoticeMapper;
import com.dfans.model.Admin;
import com.dfans.model.LogModel;
import com.dfans.utils.LogUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/login")
public class LoginController {
	private Log logger = LogFactory.getLog(this.getClass());
	private static Gson gson = new GsonBuilder().create();
	@Autowired
	private AdminMapper adminDao; 
	@Autowired
	private SJNoticeMapper notice;
	@RequestMapping("checkLogin") 
	public void getLogin(Model model, Admin admin,HttpServletResponse response, HttpServletRequest request) {
		String path = request.getContextPath();
		Admin login = new Admin();
		login = adminDao.login(admin);
		String respStr = "";
		if (login != null) {
			request.getSession().setAttribute("admin", login); 
			LogModel record=LogUtil.recordLog(request, Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName(), "登录"); 
			notice.logRecord(record); 
			respStr = "{\"flag\":\"true\"}";
		} else {
			respStr = "{\"flag\":\"false\"}";

		}
		try {
			response.getWriter().write(respStr);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
}
