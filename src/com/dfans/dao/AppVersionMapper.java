package com.dfans.dao;

import com.dfans.model.AppVersion;

public interface AppVersionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AppVersion record);

    int insertSelective(AppVersion record);

    AppVersion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AppVersion record);

    int updateByPrimaryKey(AppVersion record);

	int updateByVersionSelective(AppVersion appVersion);

	int deleteByVersion(AppVersion version);

	AppVersion selectByNew(Integer falg);
}