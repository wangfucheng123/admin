package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.BrokerYyb;

public interface BrokerYybMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BrokerYyb record);

    int insertSelective(BrokerYyb record);

    BrokerYyb selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BrokerYyb record);

    int updateByPrimaryKey(BrokerYyb record);

	List<Map> selectMapByYyb(Map m);

	List<BrokerYyb> selectAll(Map m);

	String selectCount(Map m);
}