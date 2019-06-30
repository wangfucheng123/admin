package com.dfans.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface SjConsumeritemsMapper {
	int saveCoinsItem(Map<String,Object> map);

	List<Map<String, String>> findItems(@Param("userid")String userid,@Param("flag")String flag,@Param("start")Integer start,@Param("num")Integer num); 
	
	String totles(@Param("userid")String userid,@Param("flag")String flag);
	
	String isSign(@Param("userid")String userid);
}