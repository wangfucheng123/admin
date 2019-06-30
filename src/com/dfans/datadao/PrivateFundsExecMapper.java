package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.ExecZxpjjg;
import com.dfans.model.PrivateFundsExec;

public interface PrivateFundsExecMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PrivateFundsExec record);

    int insertSelective(PrivateFundsExec record);

    PrivateFundsExec selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PrivateFundsExec record);

    int updateByPrimaryKey(PrivateFundsExec record);

	List<ExecZxpjjg> selectAll(Map m);

	String selectCount(Map m);
}