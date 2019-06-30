package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.BrokerBase;

public interface BrokerBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BrokerBase record);

    int insertSelective(BrokerBase record);

    BrokerBase selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BrokerBase record);

    int updateByPrimaryKeyWithBLOBs(BrokerBase record);

    int updateByPrimaryKey(BrokerBase record);

	List<BrokerBase> selectAll(Map m);

	String selectCount(Map m);

	String selectName(String name);

	int selectId(String name);

	List<Map> selectBrokerCount(Map m);

	List<Map> selectTzzxCount(Map m);

	List<Map> selectQhgsCount(Map m);

	List<Map> fuzzyQueryQs(Map m);

	List<Map<String, String>> selectQsList(Map m);

	List<Map> selectZxpjCount(Map m);

	List<Map> selectJjglgs(Map m);

	List<Map> selectJjxsjgCount(Map m);

	List<Map> selectZcgllCount(Map m);

	List<Map> fuzzyQueryQsyyb(Map m);

	List<Map> selectBrokerBase(Map m);

	List<Map> selectBrokerExec(Map m);

	List<Map> selectBrokerEmp(Map m);

	List<Map> selectBrokerFgs(Map m);

	List<Map> selectBrokerYyb(Map m);

	List<Map> selectFuzzyQueryFgs(Map m);

	List<Map> selectInstitutionalOperation(Map m);

	List<Map> selectEmpStatistics(Map m);

	List<Map> selectOrgQualification(Map m);

	String selectDz(String name);

	String selectDzCity(String name);

	List<Map> selectOrgInfoByPro(Map m);

	List<Map> fuzzyQueryOrg(Map m);

	List<Map> fuzzyQueryOrgCount();

	List<Map> fuzzyQueryOrg2(Map m);

	List<Map> fuzzyQueryQs2(Map m);

}