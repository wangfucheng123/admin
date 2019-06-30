package com.dfans.dao;

import java.util.Map;

import com.dfans.model.CscUserRole;

public interface CscUserRoleMapper {
    int deleteByPrimaryKey(Integer userRoleId);

    int insert(CscUserRole record);

    int insertSelective(CscUserRole record);

    CscUserRole selectByPrimaryKey(Integer userRoleId);

    int updateByPrimaryKeySelective(CscUserRole record);

    int updateByPrimaryKey(CscUserRole record);

	Map selectByUserId(Integer userId);

	String selectCount(CscUserRole cscUserRole);

	int updateByUserId(CscUserRole cscUserRole);

	int updateRoleByPrimaryKey(CscUserRole cscUserRole);
    
    
    
}