package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.BankDebtMonth;

public interface BankDebtMonthMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BankDebtMonth record);

    int insertSelective(BankDebtMonth record);

    BankDebtMonth selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BankDebtMonth record);

    int updateByPrimaryKey(BankDebtMonth record);

	List<BankDebtMonth> selectAll(Map m);

	String selectCount(Map m);
}