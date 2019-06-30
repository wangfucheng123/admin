package com.dfans.dao;

import java.util.List;

import com.dfans.model.CscRolePermission;

public interface CscRolePermissionMapper {
    int deleteByPrimaryKey(Integer rolePermissionId);

    int insert(CscRolePermission record);

    int insertSelective(CscRolePermission record);

    CscRolePermission selectByPrimaryKey(Integer rolePermissionId);

    int updateByPrimaryKeySelective(CscRolePermission record);

    int updateByPrimaryKey(CscRolePermission record);

	List<CscRolePermission> selectUserPermissions(Integer userId);

	int isExists(CscRolePermission model);

	int isExistsMenuId(Integer menuId);

	int deleteByMenuId(Integer rolePermissionId);

	List<CscRolePermission> selectRolePermissions(Integer roleId);

	int isExistsById(Integer rolePermissionId);


}