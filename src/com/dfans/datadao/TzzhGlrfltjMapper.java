package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.TzzhGlrfltj;

public interface TzzhGlrfltjMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TzzhGlrfltj record);

    int insertSelective(TzzhGlrfltj record);

    TzzhGlrfltj selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TzzhGlrfltj record);

    int updateByPrimaryKey(TzzhGlrfltj record);

	List<TzzhGlrfltj> selectAll(Map m);

	String selectCount(Map m);
}