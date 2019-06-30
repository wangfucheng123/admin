package com.dfans.dao;

import java.util.List;
import java.util.Map;

import com.dfans.model.RiskMgr;

public interface RiskMgrMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskMgr record);

    int insertSelective(RiskMgr record);

    RiskMgr selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskMgr record);

    int updateByPrimaryKey(RiskMgr record);
    
    List<RiskMgr> getRiskParams(Map map);
    
}