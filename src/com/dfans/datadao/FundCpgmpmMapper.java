package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.FundCpgmpm;

public interface FundCpgmpmMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FundCpgmpm record);

    int insertSelective(FundCpgmpm record);

    FundCpgmpm selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FundCpgmpm record);

    int updateByPrimaryKey(FundCpgmpm record);

	List<FundCpgmpm> selectAll(Map m);

	String selectCount(Map m);

	List<Map> selectZczqhRank(Map m);
}