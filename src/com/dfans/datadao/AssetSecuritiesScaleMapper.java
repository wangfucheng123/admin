package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.AssetSecuritiesScale;

public interface AssetSecuritiesScaleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AssetSecuritiesScale record);

    int insertSelective(AssetSecuritiesScale record);

    AssetSecuritiesScale selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AssetSecuritiesScale record);

    int updateByPrimaryKey(AssetSecuritiesScale record);

	List<AssetSecuritiesScale> selectAll(Map m);

	String selectCount(Map m);

	List<Map> selectSecuritiesScale(Map m);

	String selectAssetSecuritiesScaleMaxDate();
}