package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.FundIndustryConfig;

public interface FundIndustryConfigMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FundIndustryConfig record);

    int insertSelective(FundIndustryConfig record);

    FundIndustryConfig selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FundIndustryConfig record);

    int updateByPrimaryKey(FundIndustryConfig record);

	List<FundIndustryConfig> selectAll(Map m);

	String selectCount(Map m);
}