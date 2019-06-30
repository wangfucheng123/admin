package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.TzzhAhyfl;

public interface TzzhAhyflMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TzzhAhyfl record);

    int insertSelective(TzzhAhyfl record);

    TzzhAhyfl selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TzzhAhyfl record);

    int updateByPrimaryKey(TzzhAhyfl record);

	List<Map> selectAhy(Map m);
}