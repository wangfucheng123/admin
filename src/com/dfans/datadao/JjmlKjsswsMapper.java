package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.JjmlKjssws;

public interface JjmlKjsswsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JjmlKjssws record);

    int insertSelective(JjmlKjssws record);

    JjmlKjssws selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JjmlKjssws record);

    int updateByPrimaryKey(JjmlKjssws record);

	List<JjmlKjssws> selectAll(Map m);

	String selectCount(Map m);

	List<Map> selectKjswsList(Map m);

	List<Map> fuzzyQueryKjssws(Map m);

	List<Map> selectAccountant(Map m);

	List<Map> fuzzyQueryKjssws2(Map m);
}