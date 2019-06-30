package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.FuturesBase;

public interface FuturesBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FuturesBase record);

    int insertSelective(FuturesBase record);

    FuturesBase selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FuturesBase record);

    int updateByPrimaryKey(FuturesBase record);

	List<FuturesBase> selectAll(Map m);

	String selectCount(Map m);

	List<Map> fuzzyQueryFutures(Map m);

	List<Map> selectQhgsList(Map m);

	List<Map> selectFuturesBase(Map m);

	List<Map> selectFuturesHistory(Map m);

	List<Map> selectFuturesBranches(Map m);

	List<Map> selectFuturesExec(Map m);

	List<Map> selectFuturesEmp(Map m);

	List<Map> selectFuturesPartner(Map m);

	List<Map> selectFuturesFinancial(Map m);

	List<Map> selectFuturesIntegrity(Map m);

	List<Map> fuzzyQueryFutures2(Map m);
}