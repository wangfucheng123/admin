package com.dfans.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dfans.model.UserFeedBack; 

public interface UserFeedBackMapper { 

    List<UserFeedBack> selectList(@Param("sql")String sql);

    UserFeedBack selectByPrimaryKey(Integer id);

   
}