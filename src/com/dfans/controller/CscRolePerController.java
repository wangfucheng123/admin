package com.dfans.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dfans.dao.CscDataMapper;
import com.dfans.dao.CscFunctionMapper;
import com.dfans.dao.CscMenuMapper;
import com.dfans.dao.CscRolePermissionMapper;
import com.dfans.dao.SJNoticeMapper;
import com.dfans.model.CscMenuPer;
import com.dfans.model.CscRolePermission;
import com.dfans.service.CscRolePerService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/cscRolePermission")
public class CscRolePerController {
	@Autowired
	private SJNoticeMapper notice;
	@Autowired
	private CscRolePermissionMapper cscRolePermissonDao;

	@Autowired
	private CscMenuMapper cscMenuDao;
	@Autowired
	private CscFunctionMapper cscFunctiondao;
	@Autowired
	private CscDataMapper cscDatadao;

	private static Gson gson = new GsonBuilder().create();

	@Resource(name = "cscRolePerService")
	private CscRolePerService cscRolePerService;

	@RequestMapping("selectRolePermissions")
	@ResponseBody
	public String getRolePermissions(Integer roleId, HttpServletRequest request) {
		if (roleId == null) {
			return gson.toJson(null);
		}
		Map<Object, Object> perMap = new HashMap<Object, Object>();
		List<CscRolePermission> list = cscRolePermissonDao
				.selectRolePermissions(roleId);
		List<Integer> functionIdList = new ArrayList<Integer>();
		List<Integer> menuIdList = new ArrayList<Integer>();
		List<Integer> dataIdList = new ArrayList<Integer>();
		List<Map<Object, Object>> functionList = new ArrayList<Map<Object, Object>>();
		//List<Map<Object, Object>> menuList = new ArrayList<Map<Object, Object>>();
		List<CscMenuPer> menuList = new ArrayList<CscMenuPer>();
		List<Map<Object, Object>> dataList = new ArrayList<Map<Object, Object>>();

		for (int i = 0; i < list.size(); i++) {
			Map<String, Integer> rolePermissionIdMap = new HashMap<String, Integer>();
			rolePermissionIdMap.put("rolePermissionId", list.get(i)
					.getRolePermissionId());
			if (list.get(i).getFunctionId() != null) {
				functionIdList.add(list.get(i).getFunctionId());
				// functionList.add(rolePermissionIdMap);
			} else if (list.get(i).getMenuId() != null) {
				menuIdList.add(list.get(i).getMenuId());

			} else if (list.get(i).getDataId() != null) {
				dataIdList.add(list.get(i).getDataId());
			}

		}
		if (functionIdList.size() > 0) {
			functionList = cscFunctiondao.selectUnionPerByList(functionIdList);
		}
		if (menuIdList.size() > 0) {
			//menuList = cscMenuDao.selectUnionPerByList(menuIdList);
			menuList = cscMenuDao.selectUnionModelPerByList(menuIdList);
			Collections.sort(menuList, new Comparator<CscMenuPer>() {
				@Override
				public int compare(CscMenuPer m1, CscMenuPer m2) {
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
			
			
		}
		if (dataIdList.size() > 0) {
			dataList = cscDatadao.selectUnionPerByList(dataIdList);
		}

		perMap.put("menus", menuList);
		perMap.put("functions", functionList);
		perMap.put("datas", dataList);
		return gson.toJson(perMap);
	}

	@RequestMapping("selectUserPermissions")
	@ResponseBody
	public String getUserPermissions(Integer userId, HttpServletRequest request) {
		if (userId == null) {
			return gson.toJson(null);
		}
		Map<Object, Object> perMap = new HashMap<Object, Object>();
		List<CscRolePermission> list = cscRolePermissonDao
				.selectUserPermissions(userId);
		List<Integer> functionIdList = new ArrayList<Integer>();
		List<Integer> menuIdList = new ArrayList<Integer>();
		List<Integer> dataIdList = new ArrayList<Integer>();
		List<Map<Object, Object>> functionList = new ArrayList<Map<Object, Object>>();
		//List<Map<Object, Object>> menuList = new ArrayList<Map<Object, Object>>();
		List<CscMenuPer> menuList = new ArrayList<CscMenuPer>();
		List<Map<Object, Object>> dataList = new ArrayList<Map<Object, Object>>();

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getFunctionId() != null) {
				functionIdList.add(list.get(i).getFunctionId());
			} else if (list.get(i).getMenuId() != null) {
				menuIdList.add(list.get(i).getMenuId());
			} else if (list.get(i).getDataId() != null) {
				dataIdList.add(list.get(i).getDataId());
			}
		}

		if (functionIdList.size() > 0) {
			functionList = cscFunctiondao.selectUnionPerByList(functionIdList);
		}
		if (menuIdList.size() > 0) {
			//menuList = cscMenuDao.selectUnionPerByList(menuIdList);
			menuList = cscMenuDao.selectUnionModelPerByList(menuIdList);
			Collections.sort(menuList, new Comparator<CscMenuPer>() {
				@Override
				public int compare(CscMenuPer m1, CscMenuPer m2) {
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
		}
		if (dataIdList.size() > 0) {
			dataList = cscDatadao.selectUnionPerByList(dataIdList);
		}
		perMap.put("menus", menuList);
		perMap.put("functions", functionList);
		perMap.put("datas", dataList);
		return gson.toJson(perMap);
	}

	@RequestMapping("save_rolePermission")
	@ResponseBody
	public String save_rolePermission(HttpServletResponse response,
			HttpServletRequest request, CscRolePermission model) {
		return cscRolePerService.save_rolePermission(model, response, request);

	}

	@RequestMapping("updateRolePermission")
	@ResponseBody
	public String updateRole(CscRolePermission model,
			HttpServletResponse response, HttpServletRequest request) {
		int i = cscRolePermissonDao.updateByPrimaryKeySelective(model);
		Map<String, String> ret = new HashMap<String, String>();
		if (i > 0) {
			ret.put("ret", "OK");
		}
		return gson.toJson(ret);
	}

	@RequestMapping("deleRolePermission")
	@ResponseBody
	public String deleRolePermission(Integer rolePermissionId,
			HttpServletResponse response, HttpServletRequest request) {

		return cscRolePerService.deleRolePermission(rolePermissionId, response,
				request);

	}

}
