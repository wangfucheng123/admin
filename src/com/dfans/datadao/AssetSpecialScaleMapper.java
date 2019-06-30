package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.AssetSpecialScale;

public interface AssetSpecialScaleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AssetSpecialScale record);

    int insertSelective(AssetSpecialScale record);

    AssetSpecialScale selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AssetSpecialScale record);

    int updateByPrimaryKey(AssetSpecialScale record);

	List<AssetSpecialScale> selectAll(Map m);

	String selectCount(Map m);

	List<Map> selectSpecialScale(Map m);
}