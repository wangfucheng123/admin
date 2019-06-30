package com.dfans.datadao;

import com.dfans.model.BrokerGradeRank;

public interface BrokerGradeRankMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BrokerGradeRank record);

    int insertSelective(BrokerGradeRank record);

    BrokerGradeRank selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BrokerGradeRank record);

    int updateByPrimaryKey(BrokerGradeRank record);
}