package com.dfans.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dfans.model.SJConsumerData;
import com.dfans.model.User;

public interface SJConsumerDataMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(SJConsumerData record);

	int insertSelective(SJConsumerData record);

	SJConsumerData selectByPrimaryKey(Integer id);

	List<SJConsumerData> selectDatasByUserId(@Param("userid") String userid);

	int updateByPrimaryKeySelective(SJConsumerData record);

	int updateByPrimaryKey(SJConsumerData record);
	List<SJConsumerData> 	getCusPage(@Param("userid") String userid);

	List<User> user_statist(@Param("format") String format,@Param("tableName") String tableName,@Param("sql") String type,@Param("columnName") String columnName);

}