package com.dfans.dao;

import com.dfans.model.DataCompany;

public interface DataCompanyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DataCompany record);

    int insertSelective(DataCompany record);

    DataCompany selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DataCompany record);

    int updateByPrimaryKey(DataCompany record);
}