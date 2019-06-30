package com.dfans.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.dfans.model.UserRightRole;

public interface UserRightRoleMapper {
    int insert(UserRightRole record);

    int insertSelective(UserRightRole record);
    
    UserRightRole selectByuIdAndtId(@Param("uId")String uId,@Param("rId")String rId);
    int deleteRole(@Param("id")String id);
	List<Map> selectAll(int parseInt);

	int deleteByUser(int parseInt);

	List<Map> selectNowAll(Integer id);

	String selectRoleByUser(Map param);
	
	int updateByExample(UserRightRole record);
	
	List<UserRightRole> selectRoleByUserId(@Param("userid")String userid);
}