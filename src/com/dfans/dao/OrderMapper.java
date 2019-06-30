package com.dfans.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dfans.model.Order;
import com.dfans.model.User;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
    List<User> getDataByUserId(@Param("userid")String userid);
}