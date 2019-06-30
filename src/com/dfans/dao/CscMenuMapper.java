package com.dfans.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.dfans.model.CscFunction;
import com.dfans.model.CscMenu;
import com.dfans.model.CscMenuPer;

public interface CscMenuMapper {
    int deleteByPrimaryKey(Integer menuId);

    int insert(CscMenu record);

    int insertSelective(CscMenu record);

    CscMenu selectByPrimaryKey(Integer menuId);

    int updateByPrimaryKeySelective(CscMenu record);

    int updateByPrimaryKey(CscMenu record);

	List<CscMenu> selectAll();
	
	List<CscMenu> selectByUserId(Integer id);

	List<CscMenu> selectAllPage(Map<String, Object> m);

	String selectCount(Map<String, Object> m);

	String selectCountByMenuName(CscMenu cscMenu);

	int isExistParent(Integer menuId);

	CscMenu getMenuParentId(Integer menuId);
	
	int getParentId(Integer menuId);
	

	List<CscMenu> getMenuChildrenId(Integer menuId);

	String selectCountByParentId(CscMenu cscMenu);

	List<CscMenu> selectMenuChildren(Integer menuId);

	List<CscMenu> selectByList(@Param("ids")List<Integer> menuIdList);
	
	List<Map<Object, Object>> selectUnionPerByList(@Param("ids")List<Integer> menuIdList);
	
	List<CscMenuPer> selectUnionModelPerByList(@Param("ids")List<Integer> menuIdList);

	List<CscMenu> selectAllChildren(Integer menuId);
}