package com.dfans.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dfans.model.SJConsumerData;
import com.dfans.model.SJPagetotal;

public interface SJPagetotalMapper {
	List<SJPagetotal> avgBydate(@Param("format") String format);
	SJConsumerData getById(@Param("id") String id);
	List<SJPagetotal> totalBydate(@Param("format") String format);
	List<SJConsumerData> getListByType(@Param("sj_id") String sj_id,@Param("type") String type);
	List<SJPagetotal> downlaod_list(@Param("format") String format, @Param("source") String source,@Param("tableName") String tableName, @Param("columnName") String columnName);
	List<SJPagetotal> data_downlaod_statist(@Param("format") String format, @Param("pid") String pid, @Param("tableName") String tableName, @Param("columnName") String columnName);
	int save(SJPagetotal record);
	int saveData(SJPagetotal record);
}