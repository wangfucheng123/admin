package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.FundCpslpm;

public interface FundCpslpmMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FundCpslpm record);

    int insertSelective(FundCpslpm record);

    FundCpslpm selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FundCpslpm record);

    int updateByPrimaryKey(FundCpslpm record);

	List<FundCpslpm> selectAll(Map m);

	String selectCount(Map m);

	List<Map> selectZczqhRank(Map m);

	String selectZczqhRankMaxDate();
}