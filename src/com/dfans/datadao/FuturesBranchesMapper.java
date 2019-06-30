package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.FuturesBranches;

public interface FuturesBranchesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FuturesBranches record);

    int insertSelective(FuturesBranches record);

    FuturesBranches selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FuturesBranches record);

    int updateByPrimaryKey(FuturesBranches record);

	List<FuturesBranches> selectAll(Map m);

	String selectCount(Map m);
}