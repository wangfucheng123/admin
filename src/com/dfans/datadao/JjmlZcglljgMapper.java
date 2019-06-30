package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.JjmlZcglljg;

public interface JjmlZcglljgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JjmlZcglljg record);

    int insertSelective(JjmlZcglljg record);

    JjmlZcglljg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JjmlZcglljg record);

    int updateByPrimaryKey(JjmlZcglljg record);

	List<Map> fuzzyQueryZcglljg(Map m);

	List<JjmlZcglljg> selectAll(Map m);

	String selectCount(Map m);

	List<Map> selectZcglljgList(Map m);

	List<Map> selectAssetManage(Map m);

	List<Map> fuzzyQueryZcglljg2(Map m);
}