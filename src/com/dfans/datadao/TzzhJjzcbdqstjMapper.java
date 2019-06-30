package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.TzzhJjzcbdqstj;

public interface TzzhJjzcbdqstjMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TzzhJjzcbdqstj record);

    int insertSelective(TzzhJjzcbdqstj record);

    TzzhJjzcbdqstj selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TzzhJjzcbdqstj record);

    int updateByPrimaryKey(TzzhJjzcbdqstj record);

	List<TzzhJjzcbdqstj> selectAll(Map m);

	String selectCount(Map m);
}