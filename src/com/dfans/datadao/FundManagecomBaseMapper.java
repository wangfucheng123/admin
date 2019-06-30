package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.FundManagecomBase;

public interface FundManagecomBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FundManagecomBase record);

    int insertSelective(FundManagecomBase record);

    FundManagecomBase selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FundManagecomBase record);

    int updateByPrimaryKey(FundManagecomBase record);

	List<FundManagecomBase> selectAll(Map m);

	String selectCount(Map m);

	String selectBaseId();

	String selectBaseId(String excelComNamae);

	List<Map> selectComBase(Map m);

	List<Map> selectAssetAllocation(Map m);

	List<Map> selectFundMananger(Map m);

	List<Map> selectManaged(Map m);

	List<Map> selectIndustryConfig(Map m);

	List<Map> selectScaleChange(Map m);

	List<Map> selectHolderStructure(Map m);

	List<Map> selectPositionDetail(Map m);

	List<Map> selectPositionChange(Map m);

}