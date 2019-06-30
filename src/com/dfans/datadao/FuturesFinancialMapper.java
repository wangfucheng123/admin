package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.FuturesFinancial;

public interface FuturesFinancialMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FuturesFinancial record);

    int insertSelective(FuturesFinancial record);

    FuturesFinancial selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FuturesFinancial record);

    int updateByPrimaryKeyWithBLOBs(FuturesFinancial record);

    int updateByPrimaryKey(FuturesFinancial record);

	List<FuturesFinancial> selectAll(Map m);

	String selectCount(Map m);
}