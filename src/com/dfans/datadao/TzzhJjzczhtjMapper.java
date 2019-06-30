package com.dfans.datadao;

import java.util.List;
import java.util.Map;

import com.dfans.model.TzzhJjzczhtj;

public interface TzzhJjzczhtjMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TzzhJjzczhtj record);

    int insertSelective(TzzhJjzczhtj record);

    TzzhJjzczhtj selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TzzhJjzczhtj record);

    int updateByPrimaryKey(TzzhJjzczhtj record);

	List<TzzhJjzczhtj> selectAll(Map m);

	String selectCount(Map m);

	List<Map> selectZczhtj(Map m);

	List<Map> selectBdqstj(Map m);

	List<Map> selectGlrfltj(Map m);

	List<Map> selectHbxsytj(Map m);

	String selectTzzhJjzczhtjMaxDate();

	String selectGlrfltjMaxDate();
}