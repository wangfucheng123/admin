package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.BankDeposit;

public interface BankDepositMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BankDeposit record);

    int insertSelective(BankDeposit record);

    BankDeposit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BankDeposit record);

    int updateByPrimaryKey(BankDeposit record);

	List<BankDeposit> selectAll(Map m);

	String selectCount(Map m);
}