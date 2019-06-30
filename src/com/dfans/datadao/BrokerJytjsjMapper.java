package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.BrokerJytjsj;

public interface BrokerJytjsjMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BrokerJytjsj record);

    int insertSelective(BrokerJytjsj record);

    BrokerJytjsj selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BrokerJytjsj record);

    int updateByPrimaryKey(BrokerJytjsj record);

	List<BrokerJytjsj> selectAll(Map m);

	String selectCount(Map m);

	List<Map> selectFinancialTjYear(Map m);

	List<Map> selectFinancialTjQuarter(Map m);

	List<Map> getCount();
}