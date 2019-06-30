package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.AssetPrivateScale;

public interface AssetPrivateScaleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AssetPrivateScale record);

    int insertSelective(AssetPrivateScale record);

    AssetPrivateScale selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AssetPrivateScale record);

    int updateByPrimaryKey(AssetPrivateScale record);

	List<AssetPrivateScale> selectAll(Map m);

	String selectCount(Map m);

	List<Map> selectPrivateScale(Map m);
}