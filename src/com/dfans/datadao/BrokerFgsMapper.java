package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.BrokerFgs;

public interface BrokerFgsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BrokerFgs record);

    int insertSelective(BrokerFgs record);

    BrokerFgs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BrokerFgs record);

    int updateByPrimaryKey(BrokerFgs record);

	List<BrokerFgs> selectAll(Map m);

	String selectCount(Map m);
}