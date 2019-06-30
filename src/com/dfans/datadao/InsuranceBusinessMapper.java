package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.InsuranceBusiness;

public interface InsuranceBusinessMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InsuranceBusiness record);

    int insertSelective(InsuranceBusiness record);

    InsuranceBusiness selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InsuranceBusiness record);

    int updateByPrimaryKey(InsuranceBusiness record);
    
    List<InsuranceBusiness> selectAll(Map<?, ?> m);
    
    String selectCount(Map<?, ?> m);

	List<Map> selectInsuranceByTime(Map<String, Object> m);

	List<Map> selectInsuranceByField(Map<String, Object> m);

	String selectInsuranceBusinessMaxDate();    
    
}