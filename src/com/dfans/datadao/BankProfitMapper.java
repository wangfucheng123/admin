package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.BankProfit;

public interface BankProfitMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BankProfit record);

    int insertSelective(BankProfit record);

    BankProfit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BankProfit record);

    int updateByPrimaryKey(BankProfit record);

	List<BankProfit> selectAll(Map m);

	String selectCount(Map m);
}