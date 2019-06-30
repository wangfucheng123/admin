package com.dfans.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dfans.cache.UserMemoy;
import com.dfans.dao.AppUserMapper;
import com.dfans.dao.AppVersionMapper;
import com.dfans.dao.UserMapper;
import com.dfans.model.AppUser;
import com.dfans.model.AppVersion;
import com.dfans.model.User;
import com.dfans.utils.IpUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@Controller 
@RequestMapping("/app")
public class AppController {
	
	@Autowired
	private AppUserMapper appUserDao;
	
	@Autowired
	private UserMapper userDao;
	
	@Autowired
	private AppVersionMapper appVersionDao;

	private static Gson gson = new GsonBuilder().create();
	
	@RequestMapping("saveMsg")
	@ResponseBody
	public String saveMsg(AppUser appUser,HttpServletRequest request) {
		Map ret=new HashMap();
		int i=0;
		AppUser temp=appUserDao.selectByMac(appUser.getMac());
		if(temp==null)
		{
			i=appUserDao.insertSelective(appUser);
		}
		else
		{
			if(temp.getStatus()==0)
			{
				i=appUserDao.updateByMacSelective(appUser);
			}
		}
		ret.put("status", i);
		return gson.toJson(ret);
	}
	
	
	@RequestMapping("appLogin")
	@ResponseBody
	public String appLogin(AppUser appUser,HttpServletRequest request) {
		Map ret=new HashMap();
		String phone=request.getParameter("phone");
		String password = request.getParameter("password");

		User temp=new User();
		temp.setPhone(phone);
		temp.setPassword(password);
		User tempU=userDao.getChkLogin(temp);
		if(tempU==null)
		{
			ret.put("status","0");
			ret.put("msg","该用户不存在！");
			return gson.toJson(ret);
		}
		User user=userDao.getLogin(temp);
		if(user==null)
		{
			ret.put("status","0");
			ret.put("msg","错误的用户名或密码！");
		}
		else
		{
			ret.put("status","1");
			ret.put("userId",user.getId());
			AppUser auser=appUserDao.selectByMac(appUser.getMac());
			ret.put("msg",auser.getFile());
			
		}
		return gson.toJson(ret);
	}
	
	
	@RequestMapping("getAppMsg")
	@ResponseBody
	public String getAppMsg(AppUser appUser,HttpServletRequest request) {
		Map ret=new HashMap();
		AppUser auser=appUserDao.selectByMac(appUser.getMac());
		if(auser==null)
		{
			ret.put("status","0");
			ret.put("msg","");
		}
		else
		{
			ret.put("status","1");
			ret.put("msg",auser.getFile());
		}
		return gson.toJson(ret);
	}
	
	
	@RequestMapping("saveAppVersion")
	@ResponseBody
	public String saveAppVersion(AppVersion appVersion,HttpServletRequest request) {
		Map ret=new HashMap();
		int i=0;
		try
		{
			i=appVersionDao.insertSelective(appVersion);
		}
		catch(Exception e)
		{
			i=appVersionDao.updateByVersionSelective(appVersion);
		}
		ret.put("status", i);
		return gson.toJson(ret);
	}
	
	
	@RequestMapping("delAppVersion")
	@ResponseBody
	public String delAppVersion(AppVersion appVersion,HttpServletRequest request) {
		Map ret=new HashMap();
		int i=0;
		i=appVersionDao.deleteByVersion(appVersion);
		ret.put("status", i);
		return gson.toJson(ret);
	}
	
	@RequestMapping("getNewAppVersion")
	@ResponseBody
	public String getNewAppVersion(HttpServletRequest request) {
		AppVersion appVersion=appVersionDao.selectByNew(Integer.parseInt(request.getParameter("flag")));
		return gson.toJson(appVersion);
	}
	
}
