package com.dfans.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dfans.dao.AdminMapper;
import com.dfans.dao.SJNoticeMapper;
import com.dfans.dao.UserOrderMapper;
import com.dfans.model.Admin;
import com.dfans.model.LogModel;
import com.dfans.utils.LogUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



@Controller 
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private SJNoticeMapper notice;
	@Autowired
	private AdminMapper adminDao;
	
	@Autowired
	private UserOrderMapper userOrderDao;
	
	private static Gson gson = new GsonBuilder().create();
	
	@RequestMapping("adminList")
	@ResponseBody
	public String getAdminList(HttpServletRequest request) {
		Map m=new HashMap();
		m.put("page",Integer.parseInt(request.getParameter("page"))*10);
		
		List<Admin> list=adminDao.selectAll(m);
		String count=adminDao.selectCount(m);
		Map ret=new HashMap();
		ret.put("count",count);
		ret.put("content",list);
		return gson.toJson(ret);
	}
	
	
	@RequestMapping("userOrderList")
	@ResponseBody
	public String getUserOrderList(HttpServletRequest request) {
		Map m=new HashMap();
		m.put("page",Integer.parseInt(request.getParameter("page"))*10);
		if(request.getParameter("status")!=null && !request.getParameter("status").equals(""))
		{
			m.put("status",request.getParameter("status"));
		}
		List<Map> list=userOrderDao.selectAll(m);
		String count=userOrderDao.selectCount(m);
		Map ret=new HashMap();
		ret.put("count",count);
		ret.put("content",list);
		return gson.toJson(ret);
	}
	
	//deleUserOrder
	
	
	@RequestMapping("admin")
	@ResponseBody
	public String getAdmin(Admin admin) {
		admin=adminDao.selectByPrimaryKey(admin.getId());
		return gson.toJson(admin);
	}
	
	@RequestMapping("saveAdmin") 
	public void saveAdmin(Admin admin,HttpServletResponse response,HttpServletRequest request) {
		String m="";
		int i=0;
		if(admin.getId()==null || admin.getId().equals(""))
		{
			i=adminDao.insertSelective(admin);
		m="保存系统用户";}
		else
		{
			i=adminDao.updateByPrimaryKeySelective(admin);m="修改系统用户详细";
		}
		String respStr="";
		Map ret=new HashMap();
		if(i>0)
		{	ret.put("flag","true");
			ret.put("ret","OK");
			respStr="{\"flag\":\"true\"}";
		}
		try {
			response.getWriter().write(respStr);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		LogModel record=LogUtil.recordLog(request, Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName(), m); 
		notice.logRecord(record); 
	}
	
	@RequestMapping("deleAdmin") 
	public void deleAdmin(Admin admin,HttpServletResponse response,HttpServletRequest request) {
		int i=adminDao.deleteByPrimaryKey(admin.getId());
		try {
			response.getWriter().write("");
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		LogModel record=LogUtil.recordLog(request, Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName(), "删除系统用户"); 
		notice.logRecord(record);
	}
	
	@RequestMapping("editPass") 
	public void editPass(Admin admin,HttpServletResponse response,HttpServletRequest request,String pword,String id) { 
		if(StringUtils.isEmpty(id)){
			id=((Admin) request.getSession().getAttribute("admin")).getId().toString();
		}
		admin.setId(Integer.parseInt(id));
		Admin a=adminDao.selectByPassKey(admin);
		String respStr=""; 
		if (a != null) {
			a.setPassword(pword);
			int res = adminDao.updateByPrimaryKeySelective(a);
			if (res > 0) {
				respStr = "{\"flag\":\"true\"}";
			}
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
		LogModel record=LogUtil.recordLog(request, Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName(), "修改密码"); 
		notice.logRecord(record); 
	}
	@RequestMapping("adminStatus")
	@ResponseBody
	public String adminStatus(Admin admin,HttpServletRequest request) {
		int i=adminDao.updateByPrimaryKeySelective(admin);
		return gson.toJson(null);
	}
	
}
