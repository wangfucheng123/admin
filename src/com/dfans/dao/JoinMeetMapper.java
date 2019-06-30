package com.dfans.dao;

import java.util.List;
import java.util.Map;

import com.dfans.model.JoinMeet;

public interface JoinMeetMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JoinMeet record);

    int insertSelective(JoinMeet record);

    JoinMeet selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JoinMeet record);

    int updateByPrimaryKey(JoinMeet record);

	void saveJoinMeet(Map m);

	List<Map<String, String>> visitRoomList(Map m);

	List<Map<String, String>> visitRoomNum();

}