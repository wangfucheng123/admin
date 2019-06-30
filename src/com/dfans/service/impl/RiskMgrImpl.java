package com.dfans.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dfans.dao.RiskMgrMapper;
import com.dfans.model.RiskMgr;
import com.dfans.service.RiskMgrService;

@Service
public class RiskMgrImpl implements RiskMgrService {
	
	@Autowired
	private RiskMgrMapper riskMgrDao;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return riskMgrDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(RiskMgr record) {
		return riskMgrDao.insert(record);
	}

	@Override
	public int insertSelective(RiskMgr record) {
		return riskMgrDao.insertSelective(record);
	}

	@Override
	public RiskMgr selectByPrimaryKey(Integer id) {
		return riskMgrDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(RiskMgr record) {
		return riskMgrDao.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKey(RiskMgr record) {
		return riskMgrDao.updateByPrimaryKey(record);
	}

}
