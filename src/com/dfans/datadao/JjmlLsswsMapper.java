package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.JjmlLssws;

public interface JjmlLsswsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JjmlLssws record);

    int insertSelective(JjmlLssws record);

    JjmlLssws selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JjmlLssws record);

    int updateByPrimaryKey(JjmlLssws record);

	List<JjmlLssws> selectAll(Map m);

	String selectCount(Map m);

	List<Map> selectLsswsList(Map m);

	List<Map> fuzzyQueryLssws(Map m);

	List<Map> selectLawyer(Map m);

	List<Map> fuzzyQueryLssws2(Map m);
}