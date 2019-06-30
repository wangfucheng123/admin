package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.FundManager;

public interface FundManagerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FundManager record);

    int insertSelective(FundManager record);

    FundManager selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FundManager record);

    int updateByPrimaryKey(FundManager record);

	List<FundManager> selectAll(Map m);

	String selectCount(Map m);
}