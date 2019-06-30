package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.FuturesExec;
import com.dfans.model.FuturesExecWithBLOBs;

public interface FuturesExecMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FuturesExecWithBLOBs record);

    int insertSelective(FuturesExecWithBLOBs record);

    FuturesExecWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FuturesExecWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(FuturesExecWithBLOBs record);

    int updateByPrimaryKey(FuturesExec record);

	List<FuturesExecWithBLOBs> selectAll(Map m);

	String selectCount(Map m);
}