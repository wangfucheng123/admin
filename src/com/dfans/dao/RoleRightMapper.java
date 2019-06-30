package com.dfans.dao;

import java.util.List;
import java.util.Map;

import com.dfans.model.RoleRight;

public interface RoleRightMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoleRight record);

    int insertSelective(RoleRight record);

    RoleRight selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoleRight record);

    int updateByPrimaryKey(RoleRight record);

	int deleteByRole(Integer id);

	List<RoleRight> selectByRoleId(Integer roleId);

	List<Map> selectAllByRoleId(int parseInt);
}