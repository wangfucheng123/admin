package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.BaseTzzxgs;

public interface BaseTzzxgsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseTzzxgs record);

    int insertSelective(BaseTzzxgs record);

    BaseTzzxgs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseTzzxgs record);

    int updateByPrimaryKey(BaseTzzxgs record);

	List<Map> selectTzzxgsList(Map m);

	List<BaseTzzxgs> selectAll(Map m);

	String selectCount(Map m);

	List<Map> fuzzyQueryTzzxgs(Map m);

	List<Map> selectTzzx(Map m);

	List<Map> fuzzyQueryTzzxgs2(Map m);
}