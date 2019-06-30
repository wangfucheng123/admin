package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.BankOwnerEquity;

public interface BankOwnerEquityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BankOwnerEquity record);

    int insertSelective(BankOwnerEquity record);

    BankOwnerEquity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BankOwnerEquity record);

    int updateByPrimaryKey(BankOwnerEquity record);

	List<BankOwnerEquity> selectAll(Map m);

	String selectCount(Map m);
}