package com.dfans.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dfans.dao.CommonUserAuthorityMapper;
import com.dfans.dao.SJNoticeMapper;
import com.dfans.model.Admin;
import com.dfans.model.CommonUserAuthority;
import com.dfans.model.LogModel;
import com.dfans.utils.LogUtil;
import com.dfans.utils.StringUtil;
import com.dfans.utils.TreeNodes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping(value = "/authority")
public class SJ_CommonUser_Authority {
	@Autowired
	private CommonUserAuthorityMapper authority;
	private static Gson gson = new GsonBuilder().create();
	@Autowired
	private SJNoticeMapper notice;
	@RequestMapping("/save_authority")
	public void save_authority(HttpServletResponse response, HttpServletRequest request,CommonUserAuthority model) {
		int res = 0;
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(model.getId());
		String respStr = "{\"flag\":\"false\"}";
		if (model.getId() != "0" && isNum.matches()) {
			res = authority.updateByPrimaryKey(model);
		} else {
			if (authority.isExists(model) == 0) {
				res = authority.save_authority_user(model);
			}else{
				respStr = "{\"flag\":\"re\"}";
			}
		} 
		if (res > 0) {
			respStr = "{\"flag\":\"true\"}";
		}
		try {
			response.getWriter().write(respStr);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		LogModel record=LogUtil.recordLog(request, Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName(), "保存用户菜单"); 
		notice.logRecord(record);
	}

	@RequestMapping("/get_authoritys")
	public void get_authoritys(HttpServletResponse response, String userid) {
		List<CommonUserAuthority> list = null;
		if (userid != null && !"".equals(userid)) {
			list = authority.get_authoritys2(Integer.parseInt(userid));
		} else {
			list = authority.get_authoritys();
		}
		if (list != null && list.size() > 0) {
			try {
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().write(gson.toJson(list));
				response.getWriter().flush();
				response.getWriter().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@RequestMapping("/remove_authority")
	public void remove_authority(HttpServletResponse response,HttpServletRequest request, String id) {
		int res = authority.remove_authority(id);
		String respStr = "{\"flag\":\"false\"}";
		if (res > 0) {
			respStr = "{\"flag\":\"true\"}";
		}
		try {
			response.getWriter().write(respStr);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		LogModel record=LogUtil.recordLog(request, Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName(), "删除用户菜单"); 
		notice.logRecord(record);
	}

	@RequestMapping("/getMenus")
	public void getMenus(HttpServletResponse response, HttpServletRequest request,String tid) {
		String userid = StringUtil.getUserId(request);
		List<TreeNodes> nodes = generatorNodes(userid, tid);
		retJosn(response, nodes);
	}

	@RequestMapping("/save_menu")
	@ResponseBody
	public String save_menu(HttpServletResponse response, CommonUserAuthority menu) {
		authority.save_menu(menu);
		return menu.getId();
	}

	@RequestMapping("/rename")
	public void rename(HttpServletResponse response,HttpServletRequest request, CommonUserAuthority menu) {
		try { 
			menu.setName(URLDecoder.decode(menu.getName(), "utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		authority.rename(menu);
		LogModel record=LogUtil.recordLog(request, Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName(), "重命名菜单"); 
		notice.logRecord(record);
	}

	@RequestMapping("/authority_trees")
	public void authority_trees(HttpServletResponse response,HttpServletRequest request,Model model, String id) { 
		Admin a=(Admin) request.getSession().getAttribute("admin");
		List<CommonUserAuthority> list=authority.selectCMenu("",a.getId().toString());
		List<CommonUserAuthority> clist=null;
		Map<String ,List<CommonUserAuthority> > map =new HashMap<String ,List<CommonUserAuthority> >(); 
		List<CommonUserAuthority> plist=authority.selectPMenu(list);
		for(CommonUserAuthority c:plist){ 
			clist=authority.selectCMenu(c.getPid().toString(),a.getId().toString());
			map.put(c.getName(), clist);
		}
		retJosn(response, map);
	}

	@RequestMapping("/remove_menu")
	public void remove_menu(HttpServletResponse response,HttpServletRequest request, String id) {
		authority.remove_menu(id);
		LogModel record=LogUtil.recordLog(request, Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName(), "删除菜单"); 
		notice.logRecord(record);
	}

	
	public List<TreeNodes> generatorNodes(String userid, String tid) {
		List<CommonUserAuthority> list = null; 
		list = authority.selectDateForTree(tid);
		List<TreeNodes> nodes = new ArrayList<TreeNodes>();
		for (CommonUserAuthority a : list) {
			TreeNodes tree = new TreeNodes();
			tree.setOpen(true);
			tree.setState("open");
			tree.setId(a.getId());
			tree.setName(a.getName());  
			tree.setPid(String.valueOf(a.getPid()));  
			nodes.add(tree);
		} 
		return nodes;
	}

	public static void retJosn(HttpServletResponse response, Object obj) {
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(gson.toJson(obj));
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		String str="abcef";
		System.out.println(str.substring(0, str.length()));
	}
}