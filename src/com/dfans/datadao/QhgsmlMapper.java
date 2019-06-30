package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.Qhgsml;

public interface QhgsmlMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Qhgsml record);

    int insertSelective(Qhgsml record);

    Qhgsml selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Qhgsml record);

    int updateByPrimaryKey(Qhgsml record);

	List<Qhgsml> selectAll(Map m);

	String selectCount(Map m);
}