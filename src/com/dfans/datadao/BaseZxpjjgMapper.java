package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.BaseZxpjjg;

public interface BaseZxpjjgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseZxpjjg record);

    int insertSelective(BaseZxpjjg record);

    BaseZxpjjg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseZxpjjg record);

    int updateByPrimaryKey(BaseZxpjjg record);

	List<BaseZxpjjg> selectAll(Map m);

	String selectCount(Map m);

	List<Map> fuzzyQueryZxpjjg(Map m);

	List<Map> selectZxpjjgList(Map m);

	List<Map> selectZxpj(Map m);

	List<Map> selectExec(Map m);

	List<Map> selectEmp(Map m);

	List<Map> fuzzyQueryZxpjjg2(Map m);
}