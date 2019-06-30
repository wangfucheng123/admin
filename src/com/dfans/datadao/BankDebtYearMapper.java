package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.BankDebtYear;

public interface BankDebtYearMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BankDebtYear record);

    int insertSelective(BankDebtYear record);

    BankDebtYear selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BankDebtYear record);

    int updateByPrimaryKey(BankDebtYear record);

	List<BankDebtYear> selectAll(Map m);

	String selectCount(Map m);
}