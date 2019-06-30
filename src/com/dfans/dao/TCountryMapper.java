package com.dfans.dao;

import java.util.List;

import com.dfans.model.TCountry;

public interface TCountryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TCountry record);

    int insertSelective(TCountry record);

    TCountry selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TCountry record);

    int updateByPrimaryKey(TCountry record);

	List<TCountry> selectAll();
}