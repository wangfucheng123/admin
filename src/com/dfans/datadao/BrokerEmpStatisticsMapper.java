package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.BrokerEmpStatistics;

public interface BrokerEmpStatisticsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BrokerEmpStatistics record);

    int insertSelective(BrokerEmpStatistics record);

    BrokerEmpStatistics selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BrokerEmpStatistics record);

    int updateByPrimaryKey(BrokerEmpStatistics record);

	List<BrokerEmpStatistics> selectAll(Map m);

	String selectCount(Map m);
}