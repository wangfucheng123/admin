package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.InsurancePension;

public interface InsurancePensionMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(InsurancePension record);

	int insertSelective(InsurancePension record);

	InsurancePension selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(InsurancePension record);

	int updateByPrimaryKey(InsurancePension record);

	List<InsurancePension> selectAll(Map<?, ?> m);

	String selectCount(Map<?, ?> m);

}