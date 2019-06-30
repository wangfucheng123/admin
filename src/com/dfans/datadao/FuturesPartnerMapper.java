package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.FuturesPartner;

public interface FuturesPartnerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FuturesPartner record);

    int insertSelective(FuturesPartner record);

    FuturesPartner selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FuturesPartner record);

    int updateByPrimaryKeyWithBLOBs(FuturesPartner record);

    int updateByPrimaryKey(FuturesPartner record);

	List<FuturesPartner> selectAll(Map m);

	String selectCount(Map m);
}