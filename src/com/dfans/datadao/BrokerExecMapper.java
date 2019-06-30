package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.BrokerExec;

public interface BrokerExecMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BrokerExec record);

    int insertSelective(BrokerExec record);

    BrokerExec selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BrokerExec record);

    int updateByPrimaryKey(BrokerExec record);

	List<BrokerExec> selectAll(Map m);

	String selectCount(Map m);
}