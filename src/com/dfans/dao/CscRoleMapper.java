package com.dfans.dao;

import java.util.List;

import com.dfans.model.CscRole;

public interface CscRoleMapper {
    int deleteByPrimaryKey(Integer roleId);

    int insert(CscRole record);

    int insertSelective(CscRole record);

    CscRole selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(CscRole record);

    int updateByPrimaryKey(CscRole record);

	List<CscRole> selectAll();
}