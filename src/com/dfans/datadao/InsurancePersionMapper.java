package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.InsurancePersion;

public interface InsurancePersionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InsurancePersion record);

    int insertSelective(InsurancePersion record);

    InsurancePersion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InsurancePersion record);

    int updateByPrimaryKey(InsurancePersion record);

    List<InsurancePersion> selectAll(Map<?, ?> m);
    
    String selectCount(Map<?, ?> m);

	List<Map> selectPersionByTime(Map<String, Object> m);

	List<Map> selectPersionByCompany(Map<String, Object> m);

	String selectInsurancePersionMaxDate();
}