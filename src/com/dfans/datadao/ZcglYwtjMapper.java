package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.ZcglYwtj;

public interface ZcglYwtjMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ZcglYwtj record);

    int insertSelective(ZcglYwtj record);

    ZcglYwtj selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ZcglYwtj record);

    int updateByPrimaryKey(ZcglYwtj record);

	List<ZcglYwtj> selectAll(Map m);

	String selectCount(Map m);
}