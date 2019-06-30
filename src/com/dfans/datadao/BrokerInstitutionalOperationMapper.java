package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.BrokerInstitutionalOperation;

public interface BrokerInstitutionalOperationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BrokerInstitutionalOperation record);

    int insertSelective(BrokerInstitutionalOperation record);

    BrokerInstitutionalOperation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BrokerInstitutionalOperation record);

    int updateByPrimaryKey(BrokerInstitutionalOperation record);

	List<BrokerInstitutionalOperation> selectAll(Map m);

	String selectCount(Map m);

	String selectId(String comName);
}