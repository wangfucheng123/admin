package com.dfans.dao;

import com.dfans.model.AppUser;

public interface AppUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AppUser record);

    int insertSelective(AppUser record);

    AppUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AppUser record);

    int updateByPrimaryKeyWithBLOBs(AppUser record);

    int updateByPrimaryKey(AppUser record);

	AppUser selectByMac(String mac);

	int updateByMacSelective(AppUser appUser);
}