package com.dfans.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.dfans.dao.RightTreeMapper;
import com.dfans.dao.SJNoticeMapper;
import com.dfans.dao.UserRightRoleMapper;
import com.dfans.model.LogModel;
import com.dfans.model.RightTree;
import com.dfans.model.User;
import com.dfans.model.UserRightRole;
import com.dfans.utils.JSonUtil;
import com.dfans.utils.LogUtil;
import com.dfans.utils.StringUtil;
import com.dfans.utils.TreeUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/rightTree")
public class RightTreeController {
	@Autowired
	private SJNoticeMapper notice;
	@Autowired
	private RightTreeMapper rightTreeDao;
	@Autowired
	private RightTreeMapper rightTreeMapper;
	private static Gson gson = new GsonBuilder().create();
	@Autowired
	private UserRightRoleMapper userRightRoleMapper;

	@RequestMapping("getTree")
	@ResponseBody
	public Object getTree(HttpServletRequest request) {
		List<RightTree> list = rightTreeDao.selectTree(Integer.parseInt(request.getParameter("rootId")));
		List<Map> ret = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			Map m = new HashMap();
			m.put("id", "" + list.get(i).getId());
			m.put("name", list.get(i).getName());
			m.put("price", list.get(i).getPrice());
			m.put("type", "" + list.get(i).getType());
			m.put("code", list.get(i).getCode());
			String parent = "" + list.get(i).getParentId();
			if (parent.equals("0")) {
				parent = "";
			}
			m.put("parentId", parent);
			ret.add(m);
		}
		System.out.println(TreeUtils.getTree(ret));
		return JSONObject.fromObject(TreeUtils.getTree(ret));
	}
	
	
	@RequestMapping("getSjTree")
	@ResponseBody
	public Object getSjTree(HttpServletRequest request) {
		List<Map> list = rightTreeDao.selectSjTree(Integer.parseInt(request.getParameter("rootId")));
		List<Map> ret = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			Map m = new HashMap();
			m.put("id", "" + list.get(i).get("id"));
			m.put("name", list.get(i).get("name"));
			m.put("url", list.get(i).get("url"));
			m.put("type", "" + list.get(i).get("type"));
			m.put("code", list.get(i).get("code"));
			m.put("code1", list.get(i).get("code1"));
			m.put("code2", list.get(i).get("code2"));
			m.put("img_url", list.get(i).get("img_url"));
			m.put("sort", list.get(i).get("sort"));
			m.put("status", list.get(i).get("status"));
			String parent = "" + list.get(i).get("pid");
			if (parent.equals("0")) {
				parent = "";
			}
			m.put("parentId", parent);
			ret.add(m);
		}
		System.out.println(TreeUtils.getTree(ret));
		return JSONObject.fromObject(TreeUtils.getTree(ret));
	}

	@RequestMapping("getCheckTree")
	@ResponseBody
	public Object getCheckTree(HttpServletRequest request) {
		List<RightTree> list = rightTreeDao.selectTree(Integer.parseInt(request.getParameter("rootId")));
		List<Map> ret = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			Map m = new HashMap();
			m.put("id", "" + list.get(i).getId());
			m.put("name", list.get(i).getName());

			String parent = "" + list.get(i).getParentId();
			if (parent.equals("0")) {
				parent = "";
			}
			m.put("parentId", parent);
			ret.add(m);
		}
		return JSONObject.fromObject(TreeUtils.getCheckTree(ret));
	}

	@RequestMapping("deleTree")
	@ResponseBody
	public String deleTree(RightTree rightTree) {
		int i = rightTreeDao.deleteByPrimaryKey(rightTree.getId());
		return gson.toJson(null);
	}
	
	
	@RequestMapping("deleSjTree")
	@ResponseBody
	public String deleSjTree(HttpServletRequest request) {
		int i = rightTreeDao.deleteSjByPrimaryKey(Integer.parseInt(request.getParameter("id")));
		return gson.toJson(null);
	}
	
	
	@RequestMapping("getTreeById")
	public void getTreeById(HttpServletResponse response,String id) {
		RightTree rightTree = rightTreeMapper.selectByPrimaryKey(Integer.parseInt(id));
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(gson.toJson(rightTree));
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping("getSjTreeById")
	public void getSjTreeById(HttpServletResponse response,String id) {
		Map menuTree = rightTreeMapper.selectSjByPrimaryKey(Integer.parseInt(id));
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(gson.toJson(menuTree));
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("saveTree")
	@ResponseBody
	public String saveTree(RightTree rightTree, HttpServletRequest request) {
		int i = 0;
		if (rightTree.getName() != null) {
			try {
				rightTree.setName(URLDecoder.decode(rightTree.getName(), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (rightTree.getId() == null || rightTree.getId().equals("")) {
			i = rightTreeDao.insertSelective(rightTree);
		} else {
			i = rightTreeDao.updateByPrimaryKeySelective(rightTree);
		}
		Map ret = new HashMap();
		if (i > 0) {
			ret.put("ret", "OK");
		}
		return gson.toJson(ret);
	}
	
	
	
	@RequestMapping("saveSjTree")
	@ResponseBody
	public String saveSjTree(HttpServletRequest request) {
		int i = 0;
		String name=request.getParameter("name");
		//String id=;
		if (name != null) {
			try {
				name=(URLDecoder.decode(name, "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Map m=new HashMap();
		m.put("id",request.getParameter("id"));
		m.put("name",name);
		m.put("pid",request.getParameter("pid"));
		m.put("code",request.getParameter("code"));
		m.put("code1",request.getParameter("code1"));
		m.put("code2",request.getParameter("code2"));
		m.put("type",request.getParameter("type"));
		m.put("status",request.getParameter("status"));
		m.put("url",request.getParameter("url"));
		m.put("img_url",request.getParameter("img_url"));
		m.put("sort",request.getParameter("sort"));
		if (request.getParameter("id") == null || request.getParameter("id").equals("")) {
			i = rightTreeDao.insertSjSelective(m);
		} else {
			i = rightTreeDao.updateByPrimaryKeySjSelective(m);
		}
		Map ret = new HashMap();
		if (i > 0) {
			ret.put("ret", "OK");
		}
		return gson.toJson(ret);
	}
	
	
	@RequestMapping("changeSjTree")
	@ResponseBody
	public String changeSjTree(HttpServletRequest request) {
		String id=request.getParameter("id");
		String pid=request.getParameter("pid");
		Map m=new HashMap();
		m.put("id",id);
		m.put("pid",pid);
		int i = rightTreeDao.changeSjSelective(m);
		
		Map ret = new HashMap();
		if (i > 0) {
			ret.put("ret", "OK");
		}
		return gson.toJson(ret);
	}
	
	
	
	
	
	@RequestMapping("editState")
	@ResponseBody
	public String editState(String id, HttpServletRequest request) {
		int i = 0; 
		Map ret = new HashMap();
		if (i > 0) {
			ret.put("ret", "OK");
		}
		return gson.toJson(ret);
	}
	@RequestMapping("getTreeList")
	public void getTreeList(HttpServletResponse response, HttpServletRequest request) {
		List<RightTree> list = rightTreeDao.selectTreeList();
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

	@RequestMapping({ "/getrightTrees" })
	public void getrightTrees(HttpServletRequest req, HttpServletResponse response, Model model) {
		List<RightTree> rightTrees = rightTreeMapper.rightTrees();
		if (rightTrees != null && rightTrees.size() > 0) {
			for (RightTree tree : rightTrees) {
				if (tree.getContent() != null) {
					tree.setContent(tree.getContent().replaceAll("<p>", "").replaceAll("</p>", ""));
				}
			}
			try {
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().write(gson.toJson(rightTrees));
				response.getWriter().flush();
				response.getWriter().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	@RequestMapping("saveUserRightRole")
	public void saveUserRightRole(HttpServletResponse response, HttpServletRequest request, String id, String userId,
			String rightId, String startDate, String endDate) {
		UserRightRole record = new UserRightRole();
		record.setRightId(rightId);
		record.setUserId(Integer.parseInt(userId));
		record.setStartDate(string2Date(startDate, NYR_SFM));
		record.setEndDate(string2Date(endDate, NYR_SFM));
		record.setUpdateTime(new Date());
		int res = 0;
		UserRightRole r=userRightRoleMapper.selectByuIdAndtId(userId, rightId);
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(id);
		String m="";
		String respStr = "{\"flag\":\"false\"}"; 
			if (!id.equals("0") && isNum.matches()) {
				record.setId(id);
				res = userRightRoleMapper.updateByExample(record);
				m="修改user权限";
			} else if(r==null) {
				record.setId("SJ" + System.currentTimeMillis());
				res = userRightRoleMapper.insert(record);
				m="保存user权限";
			}  else{
				respStr = "{\"flag\":\"re\"}";
			}
		if (res > 0) {
			respStr = "{\"flag\":\"true\"}";
		}
		JSonUtil.retJosn(response, respStr);
		LogModel rec=LogUtil.recordLog(request, Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName(), m); 
		notice.logRecord(rec); 
	}

	@RequestMapping({ "/remove_authority" })
	public void remove_authority(HttpServletResponse response, String id) {
		int res = userRightRoleMapper.deleteRole(id);
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
	}

	@RequestMapping({ "/getTreeListByUserId" })
	public void getTreeListByUserId(HttpServletResponse response, String userid) {
		List<UserRightRole> rightTrees = userRightRoleMapper.selectRoleByUserId(userid);
		if (rightTrees != null && rightTrees.size() > 0) {
			try {
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().write(gson.toJson(rightTrees));
				response.getWriter().flush();
				response.getWriter().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public static String date2String(Date date, String pattern) {
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		String reDate = df.format(date);
		return reDate;
	}

	public static Date string2Date(String date, String pattern) {
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		Date reDate = null;
		try {
			reDate = df.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return reDate;
	}

	public static final String NYR = "yyyy-MM-dd";
	public static final String NYR_SFM = "yyyy-MM-dd HH:mm:ss";
}
