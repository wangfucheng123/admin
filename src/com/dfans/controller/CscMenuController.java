package com.dfans.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dfans.dao.CscMenuMapper;
import com.dfans.dao.SJNoticeMapper;
import com.dfans.model.CscFunction;
import com.dfans.model.CscMenu;
import com.dfans.service.CscMenuService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping(value = "/cscMenu")
public class CscMenuController {

	@Resource(name = "cscMenuService")
	private CscMenuService cscMenuService;
	@Autowired
	private CscMenuMapper cscMenuDao;
	private static Gson gson = new GsonBuilder().create();
	@Autowired
	private SJNoticeMapper notice;

	@RequestMapping("getMenuList")
	@ResponseBody
	public String getMenuList(HttpServletRequest request) {
		List<CscMenu> list = cscMenuDao.selectAll();
		Collections.sort(list, new Comparator<CscMenu>() {
			@Override
			public int compare(CscMenu m1, CscMenu m2) {
				int parentSort = m1.getParentId() - m2.getParentId();
				if (parentSort > 0) {
					return 1;
				} else if (parentSort < 0) {
					return -1;
				} else {
					int orderSort = m1.getMenuOrder() - m2.getMenuOrder();
					if (orderSort > 0) {
						return 1;
					} else {
						return -1;
					}

				}
			}
		});
		return gson.toJson(list);
	}

	@RequestMapping("menuList")
	@ResponseBody
	public String MenuList(HttpServletRequest request) {
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("page", Integer.parseInt(request.getParameter("page")) * 10);
		List<CscMenu> list = cscMenuDao.selectAllPage(m);
		Collections.sort(list, new Comparator<CscMenu>() {
			@Override
			public int compare(CscMenu m1, CscMenu m2) {
				int parentSort = m1.getParentId() - m2.getParentId();
				if (parentSort > 0) {
					return 1;
				} else if (parentSort < 0) {
					return -1;
				} else {
					int orderSort = m1.getMenuOrder() - m2.getMenuOrder();
					if (orderSort > 0) {
						return 1;
					} else {
						return -1;
					}

				}
			}
		});
		String count = cscMenuDao.selectCount(m);
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("count", count);
		ret.put("content", list);
		return gson.toJson(ret);
	}

	@RequestMapping("getMenuParentId")
	@ResponseBody
	public String getMenuParentId(HttpServletRequest request, Integer menu_id) {
		Integer parentId = null;
		if (cscMenuDao.getMenuParentId(menu_id) != null) {
			parentId = cscMenuDao.getMenuParentId(menu_id).getParentId();
		}
		return gson.toJson(parentId);
	}

	@RequestMapping("saveMenu")
	@ResponseBody
	public String saveMenu(CscMenu cscMenu, HttpServletRequest request)
			throws Exception {
		return cscMenuService.saveMenu(cscMenu, request);
	}

	@RequestMapping("saveMenus")
	@ResponseBody
	public String saveMenus(@RequestBody CscMenu[] menusArr,
			HttpServletRequest request) throws Exception {
		List<CscMenu> cscMenuList = new LinkedList<CscMenu>();
		for (int i = 0; i < menusArr.length; i++) {
			cscMenuList.add(menusArr[i]);
		}
		return cscMenuService.saveMenus(cscMenuList, request);

	}

	@RequestMapping("deleMenu")
	@ResponseBody
	public String deleMenu(Integer menuId, HttpServletRequest request) {
		return cscMenuService.deleCscMenu(menuId, request);
	}

	@RequestMapping("deleMenus")
	@ResponseBody
	public String deleMenus(@RequestBody CscMenu[] menusArr,
			HttpServletRequest request) {
		List<CscMenu> cscMenuList = new LinkedList<CscMenu>();
		for (int i = 0; i < menusArr.length; i++) {
			cscMenuList.add(menusArr[i]);
		}
		return cscMenuService.deleCscMenus(cscMenuList, request);
	}

	@RequestMapping("updateMenu")
	@ResponseBody
	public String updateMenu(CscMenu cscMenu, HttpServletRequest request) {
		int i = cscMenuDao.updateByPrimaryKeySelective(cscMenu);
		Map<String, Object> ret = new HashMap<String, Object>();
		/*
		 * LogModel record = LogUtil.recordLog(request, Thread.currentThread()
		 * .getStackTrace()[1].getClassName(), Thread.currentThread()
		 * .getStackTrace()[1].getMethodName(), "更新菜单");
		 * notice.logRecord(record);
		 */
		if (i > 0) {
			ret.put("ret", "OK");
		}
		return gson.toJson(ret);
	}

}
