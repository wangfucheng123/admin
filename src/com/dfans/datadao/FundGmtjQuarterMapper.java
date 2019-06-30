package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.FundGmtjQuarter;

public interface FundGmtjQuarterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FundGmtjQuarter record);

    int insertSelective(FundGmtjQuarter record);

    FundGmtjQuarter selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FundGmtjQuarter record);

    int updateByPrimaryKey(FundGmtjQuarter record);

	List<FundGmtjQuarter> selectAll(Map m);

	String selectCount(Map m);

	List<Map> selectFundGmtjData(Map m);

	String selectFundGmtjQuarterMaxDate();
}