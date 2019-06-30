package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.FuturesEmp;

public interface FuturesEmpMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FuturesEmp record);

    int insertSelective(FuturesEmp record);

    FuturesEmp selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FuturesEmp record);

    int updateByPrimaryKey(FuturesEmp record);

	List<FuturesEmp> selectAll(Map m);

	String selectCount(Map m);
}