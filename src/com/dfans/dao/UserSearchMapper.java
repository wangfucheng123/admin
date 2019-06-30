package com.dfans.dao;

import java.util.List;

import com.dfans.model.UserSearch;

public interface UserSearchMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserSearch record);

    int insertSelective(UserSearch record);

    UserSearch selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserSearch record);

    int updateByPrimaryKeyWithBLOBs(UserSearch record);

    int updateByPrimaryKey(UserSearch record);

	List<UserSearch> selectByUser(int parseInt);

	int deleteByUser(UserSearch userSearch);

	UserSearch selectBySearch(UserSearch userSearch);
}