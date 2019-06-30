package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.BrokerEmpSituation;

public interface BrokerEmpSituationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BrokerEmpSituation record);

    int insertSelective(BrokerEmpSituation record);

    BrokerEmpSituation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BrokerEmpSituation record);

    int updateByPrimaryKey(BrokerEmpSituation record);

	List<BrokerEmpSituation> selectAll(Map m);

	String selectCount(Map m);
}