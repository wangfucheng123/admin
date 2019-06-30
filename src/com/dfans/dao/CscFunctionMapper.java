package com.dfans.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.dfans.model.CscFunction;

public interface CscFunctionMapper {
    int deleteByPrimaryKey(Integer functionId);

    int insert(CscFunction record);

    int insertSelective(CscFunction record);

    CscFunction selectByPrimaryKey(Integer functionId);

    int updateByPrimaryKeySelective(CscFunction record);

    int updateByPrimaryKey(CscFunction record);

	List<CscFunction> selectAll();

	List<CscFunction> selectAllPage(Map<String, Object> m);

	String selectCount(Map<String, Object> m);

	String selectCountByFunctionName(CscFunction cscFunction);

	List<CscFunction> selectByList(@Param("ids")List<Integer> functionList);

	List<Map<Object, Object>> selectUnionPerByList(@Param("ids")List<Integer> functionIdList);
}