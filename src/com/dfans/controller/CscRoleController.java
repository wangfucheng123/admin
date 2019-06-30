package com.dfans.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.dfans.dao.CscRoleMapper;
import com.dfans.dao.CscRolePermissionMapper;
import com.dfans.dao.SJNoticeMapper;
import com.dfans.model.CscRole;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/cscRole")
public class CscRoleController {

	@Autowired
	private SJNoticeMapper notice;
	@Autowired
	private CscRoleMapper cscRoleDao;
	@Autowired
	private CscRolePermissionMapper cscRolePermissonDao;
	private static Gson gson = new GsonBuilder().create();

	@RequestMapping("getRoleList")
	@ResponseBody
	public String getRoleList(HttpServletRequest request) {
		return gson.toJson(cscRoleDao.selectAll());
	}

	/*
	 * @RequestMapping("getPermisson")
	 * 
	 * @ResponseBody public String getPermission(HttpServletRequest request,
	 * Integer roleid) { // List list =
	 * cscRolePermissonDao.selectRolePermisson(roleid); List<?> menu_list =
	 * cscRolePermissonDao.selectRoleMenu(roleid); List<?> function_list =
	 * cscRolePermissonDao.selectRoleFunction(roleid); List<?> data_list =
	 * cscRolePermissonDao.selectRoleData(roleid);
	 * 
	 * Map<String, List<?>> ret = new HashMap<String, List<?>>();
	 * ret.put("menu_list", menu_list); ret.put("function_list", function_list);
	 * 
	 * return gson.toJson(ret); }
	 */
	@RequestMapping("saveRole")
	@ResponseBody
	public String saveRole(CscRole cscRole, HttpServletResponse response,
			HttpServletRequest request) {
		int i = 0;
		if (cscRole.getRoleId() == null || cscRole.getRoleId().equals("")) {
			i = cscRoleDao.insertSelective(cscRole);
		}
		
		Map<String, String> ret = new HashMap<String, String>();
		if (i > 0) {
			ret.put("ret", "OK");
		}
		return gson.toJson(ret);
	}

	@RequestMapping("deleRole")
	@ResponseBody
	public String deleRole(String roleId, HttpServletResponse response,
			HttpServletRequest request) {
		int i = cscRoleDao.deleteByPrimaryKey(Integer.parseInt(roleId));
		Map<String, String> ret = new HashMap<String, String>();
		if (i > 0) {
			ret.put("ret", "OK");
		}
		return gson.toJson(ret);
	}

	@RequestMapping("updateRole")
	@ResponseBody
	public String updateRole(CscRole cscRole,HttpServletResponse response, HttpServletRequest request) {
		int i = cscRoleDao.updateByPrimaryKeySelective(cscRole);			
		Map<String, String> ret = new HashMap<String, String>();
		if (i > 0) {
			ret.put("ret", "OK");
		}
		return gson.toJson(ret);
	}

}
