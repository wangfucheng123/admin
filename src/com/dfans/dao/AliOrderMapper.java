package com.dfans.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dfans.model.AliOrder;

public interface AliOrderMapper {
    int deleteByPrimaryKey(Integer orderId);

    int insert(AliOrder record);

    int insertSelective(AliOrder record);

    AliOrder selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(AliOrder record);

    int updateByPrimaryKey(AliOrder record);
    
    List<AliOrder> getData(@Param("treeId")String treeId,@Param("sql")String sql);
    
    List<AliOrder> getPieData(@Param("sql")String sql);
}