package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.BankDataMonth;

public interface BankDataMonthMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BankDataMonth record);

    int insertSelective(BankDataMonth record);

    BankDataMonth selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BankDataMonth record);

    int updateByPrimaryKey(BankDataMonth record);

	List<BankDataMonth> selectAll(Map m);

	String selectCount(Map m);

	List<Map> selectDataMonth(Map m);

	List<Map> selectDataQuarter(Map m);

	List<Map> selectDataYear(Map m);

	List<Map> selectOwnerEquity(Map m);

	List<Map> selectDeposit(Map m);

	List<Map> selectProfit(Map m);

	List<Map> selectProfit2(Map m);

	List<Map> selectEmpLegal(Map m);

	List<Map> selectOwnerEquity_table(Map m);

	String selectBankDataMonthMaxDate();

	String selectBankDataQuarterMaxDate();

	String selectBankEmpLegalMaxDate();

	String selectBankDataYearMaxDate();
}