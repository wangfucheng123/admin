package com.dfans.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dfans.dao.CscMenuMapper;
import com.dfans.dao.CscRolePermissionMapper;
import com.dfans.dao.SJNoticeMapper;
import com.dfans.model.CscMenu;
import com.dfans.model.CscRolePermission;
import com.dfans.service.CscRolePerService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service("cscRolePerService")
public class CscRolePerServiceImpl implements CscRolePerService {
	@Autowired
	private SJNoticeMapper notice;
	@Autowired
	private CscRolePermissionMapper cscRolePermissonDao;
	@Autowired
	private CscMenuMapper cscMenuDao;
	
	private static Gson gson = new GsonBuilder().create();

	@Override
	@Transactional
	// 事务注解，需要在spring 配置文件中开启事务注解驱动
	public String deleRolePermission(Integer rolePermissionId,
			HttpServletResponse response, HttpServletRequest request) {
		// TODO Auto-generated method stub
		int i = 0;
		CscRolePermission model = cscRolePermissonDao.selectByPrimaryKey(rolePermissionId);
		// 关联菜单表得到该菜单的所有子菜单，如有先删除权利表中子菜单权利，再删除该节点
		List<CscMenu> chidrenIds = cscMenuDao.selectAllChildren(model.getMenuId());
		if (model.getMenuId() != null && chidrenIds.size() > 0) {
			for (int k = chidrenIds.size() - 1; k >= 0; k--) {
				//cscRolePermissonDao.deleteByMenuId(model.getMenuId());
				i = cscRolePermissonDao.deleteByMenuId(chidrenIds.get(k).getMenuId());
			}

		} else {
			i = cscRolePermissonDao.deleteByPrimaryKey(model
					.getRolePermissionId());
		}
		Map<String, String> ret = new HashMap<String, String>();
		if (i > 0) {
			ret.put("ret", "OK");
		}

		return gson.toJson(ret);
	}

	@Override
	@Transactional
	public String save_rolePermission(CscRolePermission model,
			HttpServletResponse response, HttpServletRequest request) {
		int res = 0;
		if (cscRolePermissonDao.isExists(model) == 0) {

			if (model.getMenuId() != null) {
				//判断有父级菜单权限才能添加子集权限
				int parentId = cscMenuDao.getParentId(model.getMenuId());
				if (parentId == 0|| cscRolePermissonDao.isExistsMenuId(Integer(parentId)) != 0) {
					// cas1:查询该菜单下的所有子菜单，添加父级权限时候，子集权限默认选中
					List<CscMenu> childrenIds = cscMenuDao.selectAllChildren(model.getMenuId());
					int roleId=model.getRoleId();
					for(int i=0;i<childrenIds.size();i++){
						CscRolePermission permodel=new CscRolePermission();
						permodel.setRoleId(roleId);
						permodel.setMenuId(childrenIds.get(i).getMenuId());
						permodel.setTemp(i);
						res = cscRolePermissonDao.insertSelective(permodel);
					}
					//cas2:默认添加父级菜单权限时候不添加子集菜单权限
					//res = cscRolePermissonDao.insertSelective(model);
				}
			} else if (model.getFunctionId() != null|| model.getDataId() != null) {
				res = cscRolePermissonDao.insertSelective(model);
			}
		}

		Map<String, String> ret = new HashMap<String, String>();
		if (res > 0) {
			ret.put("flag", "true");
			ret.put("ret", "OK");
		}

		return gson.toJson(ret);
	}

	private Integer Integer(int parentId) {
		// TODO Auto-generated method stub
		return null;
	}

}
