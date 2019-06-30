package com.dfans.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface SjVirtualcoinMapper {
	int saveCoins(@Param("userid")String userid,@Param("amount")Integer amount); 

	int updateCoins(@Param("userid")String userid,@Param("amount")Integer amount,@Param("flag")String flag);

	int findCoins(String userid);
	
	String isExist(@Param("userid")String userid);
}