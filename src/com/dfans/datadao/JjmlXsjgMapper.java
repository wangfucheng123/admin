package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.JjmlXsjg;

public interface JjmlXsjgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JjmlXsjg record);

    int insertSelective(JjmlXsjg record);

    JjmlXsjg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JjmlXsjg record);

    int updateByPrimaryKey(JjmlXsjg record);

	List<JjmlXsjg> selectAll(Map m);

	String selectCount(Map m);

	List<Map> fuzzyQueryXsgs(Map m);

	List<Map> selectJjxsgsList(Map m);

	List<Map> selectSaleCom(Map m);

	List<Map> fuzzyQueryXsgsDetail(Map m);
}