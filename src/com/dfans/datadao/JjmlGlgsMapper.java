package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.JjmlGlgs;

public interface JjmlGlgsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JjmlGlgs record);

    int insertSelective(JjmlGlgs record);

    JjmlGlgs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JjmlGlgs record);

    int updateByPrimaryKey(JjmlGlgs record);

	List<Map> fuzzyQueryGlgs(Map m);

	List<JjmlGlgs> selectAll(Map m);

	String selectCount(Map m);

	List<Map> selectJjglgsList(Map m);

	List<Map> fuzzyQueryGlgs2(Map m);
}