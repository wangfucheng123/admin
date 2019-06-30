package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.BrokerRank;

public interface BrokerRankMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BrokerRank record);

    int insertSelective(BrokerRank record);

    BrokerRank selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BrokerRank record);

    int updateByPrimaryKey(BrokerRank record);

	List<BrokerRank> selectAll(Map m);

	String selectCount(Map m);

	List<Map> selectQsRank(Map m);

	String selectName1(Map _map);

	String selectName2(Map _map);

	String selectId(Map com_map);
}