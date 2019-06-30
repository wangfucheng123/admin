package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.FuturesJymx;

public interface FuturesJymxMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FuturesJymx record);

    int insertSelective(FuturesJymx record);

    FuturesJymx selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FuturesJymx record);

    int updateByPrimaryKey(FuturesJymx record);

	List<FuturesJymx> selectAll(Map m);

	String selectCount(Map m);

	List<Map> selectJymxData(Map m);

	List<Map> selectJytjChart(Map m);

	String selectFuturesJymxMaxDate();
}