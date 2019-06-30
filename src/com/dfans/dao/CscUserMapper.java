package com.dfans.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.dfans.model.CscUser;
import com.dfans.model.CscUserAndRole;
import com.dfans.model.User;


public interface CscUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CscUser record);

    int insertSelective(CscUser record);

    CscUser selectByPrimaryKey(Integer id);
    
    int updateByPrimaryKeySelective(CscUser record);

    int updateByPrimaryKey(CscUser record);
    
 
    List<CscUserAndRole> selectAll(Map m);
	//List<User> getList(@Param("sql")String pSql);
	String selectCount(Map m);
	String total(@Param("sql")String pSql);
	
	String selectByPhone(CscUser user);

	CscUser selectByUserId(Integer userId);

	CscUser selectById(Integer id);

    
    
    
}