package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.FundPositionDetail;

public interface FundPositionDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FundPositionDetail record);

    int insertSelective(FundPositionDetail record);

    FundPositionDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FundPositionDetail record);

    int updateByPrimaryKey(FundPositionDetail record);

	List<FundPositionDetail> selectAll(Map m);

	String selectCount(Map m);

	String selectPositionDetailMaxDate();
}