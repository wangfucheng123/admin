package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.FuturesSubprime;

public interface FuturesSubprimeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FuturesSubprime record);

    int insertSelective(FuturesSubprime record);

    FuturesSubprime selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FuturesSubprime record);

    int updateByPrimaryKeyWithBLOBs(FuturesSubprime record);

    int updateByPrimaryKey(FuturesSubprime record);

	List<FuturesSubprime> selectAll(Map m);

	String selectCount(Map m);
}