package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.BankEmpLegal;

public interface BankEmpLegalMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BankEmpLegal record);

    int insertSelective(BankEmpLegal record);

    BankEmpLegal selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BankEmpLegal record);

    int updateByPrimaryKey(BankEmpLegal record);

	List<BankEmpLegal> selectAll(Map m);

	String selectCount(Map m);
}