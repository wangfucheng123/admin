package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.BankDebtQuarter;

public interface BankDebtQuarterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BankDebtQuarter record);

    int insertSelective(BankDebtQuarter record);

    BankDebtQuarter selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BankDebtQuarter record);

    int updateByPrimaryKey(BankDebtQuarter record);

	List<BankDebtQuarter> selectAll(Map m);

	String selectCount(Map m);
}