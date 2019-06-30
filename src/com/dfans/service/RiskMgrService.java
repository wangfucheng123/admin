/**
 * 
 */
package com.dfans.service;

import com.dfans.model.RiskMgr;

/**
 * @author Cheney
 *
 */
public interface RiskMgrService {
	int deleteByPrimaryKey(Integer id);

    int insert(RiskMgr record);

    int insertSelective(RiskMgr record);

    RiskMgr selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskMgr record);

    int updateByPrimaryKey(RiskMgr record);
}
