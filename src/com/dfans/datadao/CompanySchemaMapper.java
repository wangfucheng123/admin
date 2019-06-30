package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.CompanySchema;

public interface CompanySchemaMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CompanySchema record);

    int insertSelective(CompanySchema record);

    CompanySchema selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CompanySchema record);

    int updateByPrimaryKey(CompanySchema record);

	List<CompanySchema> selectAll(Map m);

	String selectCount(Map m);
}