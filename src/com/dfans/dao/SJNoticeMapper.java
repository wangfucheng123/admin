package com.dfans.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.dfans.model.LogModel;
import com.dfans.model.SJNotice;

public interface SJNoticeMapper {
	int deleteByPrimaryKey(Integer id);

	int cancelByPrimaryKey(Integer id);

	int insert(SJNotice record);

	int insertSelective(SJNotice record);

	List<SJNotice> getNotices(Map map);

	SJNotice selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(SJNotice record);

	int updateByPrimaryKey(SJNotice record);

	int logRecord(LogModel log);

	List<LogModel> logs(@Param("sql") String sql);
}