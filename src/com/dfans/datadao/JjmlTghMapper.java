package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.JjmlTgh;

public interface JjmlTghMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JjmlTgh record);

    int insertSelective(JjmlTgh record);

    JjmlTgh selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JjmlTgh record);

    int updateByPrimaryKey(JjmlTgh record);

	List<JjmlTgh> selectAll(Map m);

	String selectCount(Map m);

	List<Map> selectTghList(Map m);

	List<Map> fuzzyQueryTgh(Map m);

	List<Map> selectTrustBank(Map m);

	List<Map> fuzzyQueryTgh2(Map m);
}