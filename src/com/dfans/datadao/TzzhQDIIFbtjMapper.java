package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.TzzhQDIIFbtj;

public interface TzzhQDIIFbtjMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TzzhQDIIFbtj record);

    int insertSelective(TzzhQDIIFbtj record);

    TzzhQDIIFbtj selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TzzhQDIIFbtj record);

    int updateByPrimaryKey(TzzhQDIIFbtj record);

	List<TzzhQDIIFbtj> selectAll(Map m);

	String selectCount(Map m);

	List<Map> selectQDIIFbtj(Map m);

	String selectTzzhQDIIFbtjMaxDate();
}