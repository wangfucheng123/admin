package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.PrivateFundsBase;
import com.dfans.model.PrivateFundsBaseWithBLOBs;

public interface PrivateFundsBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PrivateFundsBase record);

    int insertSelective(PrivateFundsBase privateFundsBase);

    PrivateFundsBase selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PrivateFundsBase privateFundsBase);

    int updateByPrimaryKeyWithBLOBs(PrivateFundsBase record);

    int updateByPrimaryKey(PrivateFundsBase record);

	List<PrivateFundsBase> selectAll(Map m);

	String selectCount(Map m);

	String selectIdByName(String comName);

	List<Map> selectPrivateCount(Map m);

	List<Map> fuzzyQueryPrivate(Map m);

	List<Map> selectPrivateList(Map m);

	List<Map> selectPrivateBase(Map m);

	List<Map> selectPrivateExec(Map m);

	List<Map> selectPrivateExecFront(Map m);

	List<Map> selectPrivatePauseBack(Map m);

	List<Map> fuzzyQueryPrivate2(Map m);
}