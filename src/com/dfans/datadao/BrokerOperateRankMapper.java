package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.BrokerOperateRank;

public interface BrokerOperateRankMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BrokerOperateRank record);

    int insertSelective(BrokerOperateRank record);

    BrokerOperateRank selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BrokerOperateRank record);

    int updateByPrimaryKey(BrokerOperateRank record);

	List<BrokerOperateRank> selectAll(Map m);

	String selectCount(Map m);

	String selectId(String brokerName);

	String selectName(Map _map);

	List<Map> selectQsRank(Map m);

	String selectBrokerRankMaxDate();

}