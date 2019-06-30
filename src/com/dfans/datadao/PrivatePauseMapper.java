package com.dfans.datadao;

import com.dfans.model.PrivatePause;

public interface PrivatePauseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PrivatePause record);

    int insertSelective(PrivatePause record);

    PrivatePause selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PrivatePause record);

    int updateByPrimaryKey(PrivatePause record);
}