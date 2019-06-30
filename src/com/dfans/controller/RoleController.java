package com.dfans.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dfans.dao.RoleMapper;
import com.dfans.dao.RoleRightMapper;
import com.dfans.model.Admin;
import com.dfans.model.CscRole;
import com.dfans.model.RightTree;
import com.dfans.model.Role;
import com.dfans.model.RoleRight;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@Controller 
@RequestMapping("/role")
public class RoleController {

	
	@Autowired
	private RoleMapper roleDao;
	
	@Autowired
	private RoleRightMapper roleRightDao;
	
	private static Gson gson = new GsonBuilder().create();
	
	@RequestMapping("roleList")
	@ResponseBody
	public String getRoleList(HttpServletRequest request) {
		List<Role> list=roleDao.selectAll();
		Map ret=new HashMap();
		ret.put("content",list);
		return gson.toJson(ret);
	}
	
	@RequestMapping("roleRightList")
	@ResponseBody
	public String getRoleRightList(RoleRight roleRight) {
		List<RoleRight> list=roleRightDao.selectByRoleId(roleRight.getRoleId());
		return gson.toJson(list);
	}
	
	
	@RequestMapping("deleRole")
	@ResponseBody
	public String deleRole(Role role) {
		int i=roleDao.deleteByPrimaryKey(role.getId());
		roleRightDao.deleteByRole(role.getId());
		return gson.toJson(null);
	}
	
	@RequestMapping("saveRole")
	@ResponseBody
	public String saveRole(HttpServletRequest request) {
		int i=0;
		Role role=new Role();
		if(request.getParameter("id")==null || request.getParameter("id").equals(""))
		{
			role.setId(null);
		}
		else
		{
			role.setId(Integer.parseInt(request.getParameter("id")));
		}
		try {
			role.setName(URLDecoder.decode(request.getParameter("name"),"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(role.getId()==null || role.getId().equals(""))
		{
			i=roleDao.insertSelective(role);
		}
		else
		{
			i=roleDao.updateByPrimaryKeySelective(role);
		}
		roleRightDao.deleteByRole(role.getId());
		if(request.getParameter("rightId")!=null && !request.getParameter("rightId").equals(""))
		{
			String temp[]=request.getParameter("rightId").split(",");
			for(int k=0;k<temp.length;k++)
			{
				RoleRight roleRight=new RoleRight();
				roleRight.setRightId(Integer.parseInt(temp[k]));
				roleRight.setRoleId(role.getId());
				roleRightDao.insertSelective(roleRight);
			}
		}
		Map ret=new HashMap();
		if(i>0)
		{
			ret.put("ret","OK");
		}
		return gson.toJson(ret);
	}
	
}
