package com.dfans.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dfans.model.SJChannelPrice;

public interface SJChannelPriceMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(SJChannelPrice record);

	int insertSelective(SJChannelPrice record);

	SJChannelPrice selectByPrimaryKey(Integer id); 
	
	List<SJChannelPrice> selectByChannelId(@Param("id")String id);
	
	int updateByPrimaryKeySelective(SJChannelPrice record);

	int updateByPrimaryKey(SJChannelPrice record);
}