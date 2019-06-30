package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.TzzhHbxsytj;

public interface TzzhHbxsytjMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TzzhHbxsytj record);

    int insertSelective(TzzhHbxsytj record);

    TzzhHbxsytj selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TzzhHbxsytj record);

    int updateByPrimaryKey(TzzhHbxsytj record);

	List<TzzhHbxsytj> selectAll(Map m);

	String selectCount(Map m);
}