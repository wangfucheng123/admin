package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.JjmlZfjsjg;

public interface JjmlZfjsjgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JjmlZfjsjg record);

    int insertSelective(JjmlZfjsjg record);

    JjmlZfjsjg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JjmlZfjsjg record);

    int updateByPrimaryKey(JjmlZfjsjg record);

	List<JjmlZfjsjg> selectAll(Map m);

	String selectCount(Map m);

	List<Map> selectZfjsList(Map m);

	List<Map> fuzzyQueryZfjs(Map m);

	List<Map> selectPay(Map m);

	List<Map> fuzzyQueryZfjs2(Map m);
}