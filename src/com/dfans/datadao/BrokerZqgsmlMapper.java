package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.BrokerZqgsml;

public interface BrokerZqgsmlMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BrokerZqgsml record);

    int insertSelective(BrokerZqgsml record);

    BrokerZqgsml selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BrokerZqgsml record);

    int updateByPrimaryKey(BrokerZqgsml record);

	List<BrokerZqgsml> selectAll(Map m);

	String selectCount(Map m);
}