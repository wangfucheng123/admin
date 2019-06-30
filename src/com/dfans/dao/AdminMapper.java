package com.dfans.dao;

import java.util.List;
import java.util.Map;

import com.dfans.model.Admin;

public interface AdminMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

	Admin login(Admin admin);

	List<Admin> selectAll(Map m);
	Admin selectByPassKey(Admin a);
	String selectCount(Map m);
}