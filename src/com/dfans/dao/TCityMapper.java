package com.dfans.dao;

import java.util.List;
import java.util.Map;

import com.dfans.model.TCity;

public interface TCityMapper {
    int deleteByPrimaryKey(Integer cityid);

    int insert(TCity record);

    int insertSelective(TCity record);

    TCity selectByPrimaryKey(Integer cityid);

    int updateByPrimaryKeySelective(TCity record);

    int updateByPrimaryKey(TCity record);

	List<TCity> selectByProvince(Map m);

	TCity selectByName(TCity tCity);
}