package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.FundHolderStructure;

public interface FundHolderStructureMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FundHolderStructure record);

    int insertSelective(FundHolderStructure record);

    FundHolderStructure selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FundHolderStructure record);

    int updateByPrimaryKey(FundHolderStructure record);

	List<FundHolderStructure> selectAll(Map m);

	String selectCount(Map m);
}