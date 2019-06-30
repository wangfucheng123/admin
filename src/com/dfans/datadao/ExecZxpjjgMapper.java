package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.ExecZxpjjg;

public interface ExecZxpjjgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ExecZxpjjg record);

    int insertSelective(ExecZxpjjg record);

    ExecZxpjjg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ExecZxpjjg record);

    int updateByPrimaryKey(ExecZxpjjg record);

	List<ExecZxpjjg> selectAll(Map m);

	String selectCount(Map m);
}