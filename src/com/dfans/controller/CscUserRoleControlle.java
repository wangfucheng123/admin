package com.dfans.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.dfans.dao.CscUserRoleMapper;
import com.dfans.dao.SJNoticeMapper;
import com.dfans.model.CscUserRole;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/cscUserRole")
public class CscUserRoleControlle {

	@Autowired
	private CscUserRoleMapper cscUserRoleDao;
	private static Gson gson = new GsonBuilder().create();
	@Autowired
	private SJNoticeMapper notice;

	@RequestMapping("userRole")
	@ResponseBody
	public String getUserList(Integer userId, HttpServletRequest request) {
		Map<?, ?> cscUserRoleInfo= cscUserRoleDao.selectByUserId(userId);
		return gson.toJson(cscUserRoleInfo);
	}

	@RequestMapping("saveUserRole")
	@ResponseBody
	public String saveCscUserRole(CscUserRole cscUserRole,
			HttpServletRequest request) throws UnsupportedEncodingException {

		int k = Integer.parseInt(cscUserRoleDao.selectCount(cscUserRole));
		int i = 0;
		Map<String, String> ret = new HashMap<String, String>();
		if (k == 0) {
			i = cscUserRoleDao.insertSelective(cscUserRole);
			if (i > 0) {
				ret.put("ret", "OK");
			}
		} 
		return gson.toJson(ret);

	}
	
	
	@RequestMapping("updateUserRole")
	@ResponseBody
	public String updateUserRole(CscUserRole cscUserRole, HttpServletRequest request) {
		int i = cscUserRoleDao.updateByPrimaryKeySelective(cscUserRole);
		Map<String, Object> ret = new HashMap<String, Object>();
		/*LogModel record = LogUtil.recordLog(request, Thread.currentThread()
				.getStackTrace()[1].getClassName(), Thread.currentThread()
				.getStackTrace()[1].getMethodName(), "更新用户角色");
		notice.logRecord(record);*/
		if (i > 0) {
			ret.put("ret", "OK");
		}
		return gson.toJson(ret);
	}
	
	@RequestMapping("updateByUserId")
	@ResponseBody
	public String updateUserRoleByUserId(CscUserRole cscUserRole, HttpServletRequest request) {
		int i = cscUserRoleDao.updateByUserId(cscUserRole);
		Map<String, Object> ret = new HashMap<String, Object>();
		/*LogModel record = LogUtil.recordLog(request, Thread.currentThread()
				.getStackTrace()[1].getClassName(), Thread.currentThread()
				.getStackTrace()[1].getMethodName(), "更新用户角色");
		notice.logRecord(record);*/
		if (i > 0) {
			ret.put("ret", "OK");
		}
		return gson.toJson(ret);
	}
	
	

}
