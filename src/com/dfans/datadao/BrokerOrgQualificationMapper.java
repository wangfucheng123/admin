package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.BrokerOrgQualification;

public interface BrokerOrgQualificationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BrokerOrgQualification record);

    int insertSelective(BrokerOrgQualification record);

    BrokerOrgQualification selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BrokerOrgQualification record);

    int updateByPrimaryKey(BrokerOrgQualification record);

	List<BrokerOrgQualification> selectAll(Map m);

	String selectCount(Map m);
}