package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.BankMainQuota;

public interface BankMainQuotaMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BankMainQuota record);

    int insertSelective(BankMainQuota record);

    BankMainQuota selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BankMainQuota record);

    int updateByPrimaryKey(BankMainQuota record);

	List<BankMainQuota> selectAll(Map m);

	String selectCount(Map m);

	List<Map> selectMainQuota(Map m);

	List<Map> selectMainQuotaToChart(Map m);

	String selectBankMainQuotaMaxDate();
}