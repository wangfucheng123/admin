package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.AssetScaleRank;

public interface AssetScaleRankMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AssetScaleRank record);

    int insertSelective(AssetScaleRank record);

    AssetScaleRank selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AssetScaleRank record);

    int updateByPrimaryKey(AssetScaleRank record);

	List<AssetScaleRank> selectAllActive(Map m);

	String selectCountActive(Map m);

	List<AssetScaleRank> selectAllRank(Map m);

	String selectCountRank(Map m);

	List<Map> selectActiveRank(Map m);

	List<Map> selectScaleRank(Map m);

	String selectAssetScaleRankMaxDate();
}