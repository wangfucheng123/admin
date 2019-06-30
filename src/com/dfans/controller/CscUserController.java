package com.dfans.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dfans.dao.CscUserMapper;
import com.dfans.dao.SJNoticeMapper;
import com.dfans.model.CscUser;
import com.dfans.model.CscUserAndRole;
import com.dfans.model.LogModel;
import com.dfans.utils.LogUtil;
import com.dfans.utils.MD5Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/cscUser")
public class CscUserController {

	@Autowired
	private SJNoticeMapper notice;
	@Autowired
	private CscUserMapper cscUserDao;
	private static Gson gson = new GsonBuilder().create();

	@RequestMapping("userList")
	@ResponseBody
	public String getUserList(HttpServletRequest request) {
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("page", Integer.parseInt(request.getParameter("page")) * 10);

		List<CscUserAndRole> list = cscUserDao.selectAll(m);
		String count = cscUserDao.selectCount(m);
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("count", count);
		ret.put("content", list);
		return gson.toJson(ret);
	}
	
	
	@RequestMapping("userListById")
	@ResponseBody
	public String getUserListById(Integer userId,HttpServletRequest request) {
		CscUser list = cscUserDao.selectByPrimaryKey(userId);
		return gson.toJson(list);
	}
	@RequestMapping("userById")
	@ResponseBody
	public String getUserById(Integer id,HttpServletRequest request) {
		CscUser cscUser = cscUserDao.selectById(id);
		return gson.toJson(cscUser);
	}
	@RequestMapping("userListByUserId")
	@ResponseBody
	public String getUserListByUserId(Integer userId,HttpServletRequest request) {
		CscUser list = cscUserDao.selectByUserId(userId);
		return gson.toJson(list);
	}
	
	

	@RequestMapping("saveCscUser")
	@ResponseBody
	public String saveCscUser(CscUser cscUser, HttpServletRequest request)
			throws UnsupportedEncodingException {
		int i = 0;
		Map<String, String> ret = new HashMap<String, String>();
		if (cscUser.getPhone() != null && !cscUser.getPhone().equals("")) {
			String count = "0";
			count = cscUserDao.selectByPhone(cscUser);
			if (!count.equals("0")) {
				ret.put("ret", "该手机号已被使用！");
				return gson.toJson(ret);
			}
		}
		if (cscUser.getId() == null || cscUser.getId().equals("")) {
			cscUser.setPassword(MD5Utils.MD5(cscUser.getPassword() + "_shujin"));
			i = cscUserDao.insertSelective(cscUser);
		} else {

			i = cscUserDao.updateByPrimaryKeySelective(cscUser);
		}

		if (i > 0) {
			ret.put("ret", "OK");
		}
		return gson.toJson(ret);
	}
	
	
	@RequestMapping("deleCscUser")
	@ResponseBody
	public String deleCscUser(CscUser cscUser, HttpServletRequest request) {
		int i = cscUserDao.deleteByPrimaryKey(cscUser.getId());
		LogModel record=LogUtil.recordLog(request, Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName(), "删除user"); 
		notice.logRecord(record);
		Map<String, Object> ret = new HashMap<String, Object>();
		if (i > 0) {
			ret.put("ret", "OK");
		}
		return gson.toJson(i);
	}
	

}
