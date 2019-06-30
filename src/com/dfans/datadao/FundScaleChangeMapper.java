package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.FundScaleChange;

public interface FundScaleChangeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FundScaleChange record);

    int insertSelective(FundScaleChange record);

    FundScaleChange selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FundScaleChange record);

    int updateByPrimaryKey(FundScaleChange record);

	List<FundScaleChange> selectAll(Map m);

	String selectCount(Map m);
}