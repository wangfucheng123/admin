package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.FundGmtjMonth;

public interface FundGmtjMonthMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FundGmtjMonth record);

    int insertSelective(FundGmtjMonth record);

    FundGmtjMonth selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FundGmtjMonth record);

    int updateByPrimaryKey(FundGmtjMonth record);

	List<FundGmtjMonth> selectAll(Map m);

	String selectCount(Map m);

	List<Map> selectGmtjMonthData(Map m);
}