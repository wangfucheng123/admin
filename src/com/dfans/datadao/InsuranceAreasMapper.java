package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.InsuranceAreas;

public interface InsuranceAreasMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InsuranceAreas record);

    int insertSelective(InsuranceAreas record);

    InsuranceAreas selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InsuranceAreas record);

    int updateByPrimaryKey(InsuranceAreas record);

	List<InsuranceAreas> selectAll(Map<String, Object> pageMap);

	String selectCount(Map<String, Object> pageMap);

	List<Map> selectAreasByTime(Map<String, Object> m);

	List<Map> selectAreasByArea(Map<String, Object> m);

	String selectInsuranceAreasMaxDate();

	
}