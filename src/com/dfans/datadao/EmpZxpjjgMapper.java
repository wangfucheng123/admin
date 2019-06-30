package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.EmpZxpjjg;

public interface EmpZxpjjgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EmpZxpjjg record);

    int insertSelective(EmpZxpjjg record);

    EmpZxpjjg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EmpZxpjjg record);

    int updateByPrimaryKey(EmpZxpjjg record);

	List<EmpZxpjjg> selectAll(Map m);

	String selectCount(Map m);
}