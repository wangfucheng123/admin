package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.ExecZxpjjg;
import com.dfans.model.PrivateFundScaleTotal;

public interface PrivateFundScaleTotalMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PrivateFundScaleTotal record);

    int insertSelective(PrivateFundScaleTotal record);

    PrivateFundScaleTotal selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PrivateFundScaleTotal record);

    int updateByPrimaryKey(PrivateFundScaleTotal record);

	List<ExecZxpjjg> selectAll(Map m);

	String selectCount(Map m);

	List<Map> selectPrivateFundScale(Map m);
}