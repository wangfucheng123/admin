package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.FundPositionChange;

public interface FundPositionChangeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FundPositionChange record);

    int insertSelective(FundPositionChange record);

    FundPositionChange selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FundPositionChange record);

    int updateByPrimaryKey(FundPositionChange record);

	List<FundPositionChange> selectAll(Map m);

	String selectCount(Map m);

	String selectPositionChangeMaxDate(Map m);
}