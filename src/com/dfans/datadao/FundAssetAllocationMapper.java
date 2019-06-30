package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.FundAssetAllocation;

public interface FundAssetAllocationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FundAssetAllocation record);

    int insertSelective(FundAssetAllocation record);

    FundAssetAllocation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FundAssetAllocation record);

    int updateByPrimaryKey(FundAssetAllocation record);

	List<FundAssetAllocation> selectAll(Map m);

	String selectCount(Map m);
}