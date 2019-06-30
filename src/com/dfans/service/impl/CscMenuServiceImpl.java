package com.dfans.service.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dfans.dao.CscMenuMapper;
import com.dfans.dao.SJNoticeMapper;
import com.dfans.model.CscMenu;
import com.dfans.service.CscMenuService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service("cscMenuService")
public class CscMenuServiceImpl implements CscMenuService {

	/*
	 * @Autowired private CscMenu cscMenu;
	 */
	@Autowired
	private CscMenuMapper cscMenuDao;
	private static Gson gson = new GsonBuilder().create();
	@Autowired
	private SJNoticeMapper notice;

	@Override
	public String saveMenu(CscMenu cscMenu, HttpServletRequest request) {
		int i = 0;
		Map<String, String> ret = new HashMap<String, String>();
		if (cscMenu.getMenuName() == null || cscMenu.getParentId() == null
				|| cscMenu.getMenuLevel() == null
				|| cscMenu.getMenuOrder() == null) {
			ret.put("ret", "请输入正确的参数");
		} else {
			String count = "0";
			count = cscMenuDao.selectCountByMenuName(cscMenu);
			if (count != null && !count.equals("0")) {
				ret.put("ret", "该菜单已经存在");
			} else {
				/*
				 * 数据校验先查看该节点的父级节点是否存在，不存在有错退出(parent_id 为0除外 ,为根节点)
				 * 存在则再查看该节点的顺序是否正确，查出当前节点的父节点所有的子节点个数
				 */
				String parentIdCount = "0";
				parentIdCount = cscMenuDao.selectCountByParentId(cscMenu);
				if (cscMenu.getParentId() == 0
						|| (parentIdCount != null && !parentIdCount.equals("0"))) {
					List<CscMenu> cscMenuList = cscMenuDao.selectMenuChildren(cscMenu.getParentId());
					/*
					 * int menusLength = cscMenuList.size() + 1; if
					 * (cscMenu.getMenuOrder() == menusLength) { i =
					 * cscMenuDao.insertSelective(cscMenu); }
					 */
					i = cscMenuDao.insertSelective(cscMenu);
				}
			}
			if (i > 0) {
				ret.put("ret", "OK");
			} else {
				ret.put("ret", "false");
			}
		}
		return gson.toJson(ret);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public String deleCscMenu(Integer menuId, HttpServletRequest request) {

		int k = 0;
		Map<String, String> ret = new HashMap<String, String>();
		// 先查看该节点下面有没有子节点
		List<CscMenu> menuList = cscMenuDao.selectAllChildren(menuId);
		if (menuList.size() == 0) {
			k = cscMenuDao.deleteByPrimaryKey(menuId);
		} else {
			for (int i = menuList.size() - 1; i >= 0; i--) {
				k = cscMenuDao.deleteByPrimaryKey(menuList.get(i).getMenuId());
			}
			// k = cscMenuDao.deleteByPrimaryKey(menuId);
		}

		if (k > 0) {
			ret.put("ret", "OK");
		} else {
			ret.put("ret", "false");
		}

		return gson.toJson(ret);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public String saveMenus(List<CscMenu> cscMenuList,
			HttpServletRequest request) throws Exception {
		String ret = null;
		if (cscMenuList.size() == 0) {
			return ret;
		} else {
			// 按照菜单的parent由小到大，再按照兄弟节点的order由小到大
			Collections.sort(cscMenuList, new Comparator<CscMenu>() {
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

			for (int i = 0; i < cscMenuList.size(); i++) {
				ret = saveMenu(cscMenuList.get(i), request);
			}
			// throw new RuntimeException();

		}
		return ret;
	}

	@Override
	public String deleCscMenus(List<CscMenu> cscMenuList,
			HttpServletRequest request) {
		String ret = null;
		if (cscMenuList.size() == 0) {
			return ret;
		} else {
			// parent 由大到小，order由大到小 倒序刪除
			Collections.sort(cscMenuList, new Comparator<CscMenu>() {
				@Override
				public int compare(CscMenu m1, CscMenu m2) {
					int parentSort = m1.getParentId() - m2.getParentId();
					if (parentSort < 0) {
						return 1;
					} else if (parentSort > 0) {
						return -1;
					} else {
						int orderSort = m1.getMenuOrder() - m2.getMenuOrder();
						if (orderSort < 0) {
							return 1;
						} else {
							return -1;
						}

					}
				}
			});

			for (int i = 0; i < cscMenuList.size(); i++) {
				ret = deleCscMenu(cscMenuList.get(i).getMenuId(), request);
			}

		}

		return ret;
	}

}
