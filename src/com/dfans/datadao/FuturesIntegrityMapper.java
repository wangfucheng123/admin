package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.FuturesIntegrity;

public interface FuturesIntegrityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FuturesIntegrity record);

    int insertSelective(FuturesIntegrity record);

    FuturesIntegrity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FuturesIntegrity record);

    int updateByPrimaryKey(FuturesIntegrity record);

	List<FuturesIntegrity> selectAll(Map m);

	String selectCount(Map m);
}