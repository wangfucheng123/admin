package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.FuturesHistory;

public interface FuturesHistoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FuturesHistory record);

    int insertSelective(FuturesHistory record);

    FuturesHistory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FuturesHistory record);

    int updateByPrimaryKeyWithBLOBs(FuturesHistory record);

    int updateByPrimaryKey(FuturesHistory record);

	List<FuturesHistory> selectAll(Map m);

	String selectCount(Map m);
}