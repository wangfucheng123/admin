package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.FundManagecomRank;

public interface FundManagecomRankMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FundManagecomRank record);

    int insertSelective(FundManagecomRank record);

    FundManagecomRank selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FundManagecomRank record);

    int updateByPrimaryKey(FundManagecomRank record);

	List<FundManagecomRank> selectAll(Map m);

	String selectCount(Map m);

	List<FundManagecomRank> selectSpecialAll(Map m);

	String selectSpecialCount(Map m);

	List<FundManagecomRank> selectNonCurrencyAll(Map m);

	String selectNonCurrencyCount(Map m);

	List<Map> selectManageComRank(Map m);

	List<Map> selectSpecialComRank(Map m);

	List<Map> selectNonCurrencyRank(Map m);

	String selectFundManagecomRankMaxDate();
}