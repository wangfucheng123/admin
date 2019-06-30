package com.dfans.dao;

import com.dfans.model.VisitRoom;

public interface VisitRoomMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VisitRoom record);

    int insertSelective(VisitRoom record);

    VisitRoom selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VisitRoom record);

    int updateByPrimaryKeyWithBLOBs(VisitRoom record);

    int updateByPrimaryKey(VisitRoom record);
}