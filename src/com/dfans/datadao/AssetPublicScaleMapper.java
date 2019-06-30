package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.AssetPublicScale;

public interface AssetPublicScaleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AssetPublicScale record);

    int insertSelective(AssetPublicScale record);

    AssetPublicScale selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AssetPublicScale record);

    int updateByPrimaryKey(AssetPublicScale record);

	List<AssetPublicScale> selectAll(Map m);

	String selectCount(Map m);

	List<Map> selectPublicScale(Map m);
}