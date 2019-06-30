package com.dfans.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.dfans.model.MenuTree;
import com.dfans.model.RightTree;

public interface RightTreeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RightTree record);

    int insertSelective(RightTree record);

    RightTree selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RightTree record);

    int updateByPrimaryKey(RightTree record);

	List<RightTree> selectTree(Integer rootId);
	int editState(@Param("id")Integer id);
	List<RightTree> rightTrees();
	List<RightTree> sj_rightTrees(@Param("uid")String uid);
	RightTree sj_selectByPrimaryKey(@Param("id")Integer id,@Param("uid")String uid);
	List<RightTree> selectTreeList();
	Map selectByCode(String parameter);

	List<Map> selectSjTree(int parseInt);

	int insertSjSelective(Map m);

	int updateByPrimaryKeySjSelective(Map m);

	int deleteSjByPrimaryKey(Integer id);

	Map selectSjByPrimaryKey(int parseInt);

	int changeSjSelective(Map m);

	List<MenuTree> selectSjTreeList(); 
}