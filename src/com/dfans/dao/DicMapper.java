package com.dfans.dao;

import java.util.List;
import java.util.Map;

import com.dfans.model.Dic;

public interface DicMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Dic record);

    int insertSelective(Dic record);

    Dic selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Dic record);

    int updateByPrimaryKey(Dic record);

	List<Dic> selectByType(int parseInt);

	Dic selectByMap(Map param);

	List<Dic> getDicById(int parseInt);

}