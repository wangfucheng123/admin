package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.FundGmjjgstj;

public interface FundGmjjgstjMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FundGmjjgstj record);

    int insertSelective(FundGmjjgstj record);

    FundGmjjgstj selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FundGmjjgstj record);

    int updateByPrimaryKeyWithBLOBs(FundGmjjgstj record);

    int updateByPrimaryKey(FundGmjjgstj record);

	List<FundGmjjgstj> selectAll(Map m);

	String selectCount(Map m);

	List<Map> selectGssl(Map m);
}