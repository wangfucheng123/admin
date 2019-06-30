package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.PrivateManageList;

public interface PrivateManageListMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PrivateManageList record);

    int insertSelective(PrivateManageList record);

    PrivateManageList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PrivateManageList record);

    int updateByPrimaryKey(PrivateManageList record);

	List<PrivateManageList> selectAll(Map m);

	String selectCount(Map m);
}