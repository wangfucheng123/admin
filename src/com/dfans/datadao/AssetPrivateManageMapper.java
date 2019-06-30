package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.AssetPrivateManage;

public interface AssetPrivateManageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AssetPrivateManage record);

    int insertSelective(AssetPrivateManage record);

    AssetPrivateManage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AssetPrivateManage record);

    int updateByPrimaryKey(AssetPrivateManage record);

	List<AssetPrivateManage> selectAll(Map m);

	String selectCount(Map m);

	List<Map> selectPrivateManage(Map m);
}