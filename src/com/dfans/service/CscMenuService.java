package com.dfans.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.dfans.model.CscMenu;

public interface CscMenuService {
	public String saveMenu(CscMenu cscMenu, HttpServletRequest request);

	public String deleCscMenu(Integer menuId, HttpServletRequest request);

	public String saveMenus(List<CscMenu> cscMenuList, HttpServletRequest request) throws Exception;

	public String deleCscMenus(List<CscMenu> cscMenuList,
			HttpServletRequest request);

}
