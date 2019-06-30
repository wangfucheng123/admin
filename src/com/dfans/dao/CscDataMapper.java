package com.dfans.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.dfans.model.CscData;

public interface CscDataMapper {
    int deleteByPrimaryKey(Integer dataId);

    int insert(CscData record);

    int insertSelective(CscData record);

    CscData selectByPrimaryKey(Integer dataId);

    int updateByPrimaryKeySelective(CscData record);

    int updateByPrimaryKey(CscData record);

	List<CscData> selectAll();

	List<CscData> selectAllPage(Map<String, Object> m);

	String selectCount(Map<String, Object> m);

	String selectCountByDataName(CscData cscData);

	List<CscData> selectByList(@Param("ids")List<Integer> dataIdList);
	
	List<Map<Object, Object>> selectUnionPerByList(@Param("ids")List<Integer> dataIdList);
}