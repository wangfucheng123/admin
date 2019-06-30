package com.dfans.datadao;


import java.util.List;
import java.util.Map;

import com.dfans.model.InsuranceProperty;

public interface InsurancePropertyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InsuranceProperty record);

    int insertSelective(InsuranceProperty record);

    InsuranceProperty selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InsuranceProperty record);

    int updateByPrimaryKey(InsuranceProperty record);
    
    List<InsuranceProperty> selectAll(Map<?, ?> m);
    
    String selectCount(Map<?, ?> m);

	List<Map> selectPropertyByTime(Map<String, Object> m);

	List<Map> selectPropertyByCompany(Map<String, Object> m);

	String selectInsurancePropertyMaxDate();

}