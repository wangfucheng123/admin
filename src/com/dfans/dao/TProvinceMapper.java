package com.dfans.dao;

import java.util.List;
import java.util.Map;

import com.dfans.model.TProvince;

public interface TProvinceMapper {
    int deleteByPrimaryKey(Integer proid);

    int insert(TProvince record);

    int insertSelective(TProvince record);

    TProvince selectByPrimaryKey(Integer proid);

    int updateByPrimaryKeySelective(TProvince record);

    int updateByPrimaryKey(TProvince record);

	List<TProvince> selectAll();

	TProvince selectByName(TProvince s);

	List<TProvince> selectIdByName(Map m);
}