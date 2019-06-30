package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.BrokerAgent;

public interface BrokerAgentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BrokerAgent record);

    int insertSelective(BrokerAgent record);

    BrokerAgent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BrokerAgent record);

    int updateByPrimaryKey(BrokerAgent record);

	List<BrokerAgent> selectAll(Map m);

	String selectCount(Map m);
}