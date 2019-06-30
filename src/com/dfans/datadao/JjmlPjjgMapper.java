package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.JjmlPjjg;

public interface JjmlPjjgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JjmlPjjg record);

    int insertSelective(JjmlPjjg record);

    JjmlPjjg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JjmlPjjg record);

    int updateByPrimaryKey(JjmlPjjg record);

	List<JjmlPjjg> selectAll(Map m);

	String selectCount(Map m);

	List<Map> selectPjjgList(Map m);

	List<Map> fuzzyQueryPjjg(Map m);

	List<Map> selectEvaluate(Map m);

	List<Map> fuzzyQueryPjjg2(Map m);
}