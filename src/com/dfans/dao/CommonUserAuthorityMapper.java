package com.dfans.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dfans.model.CommonUserAuthority;

public interface CommonUserAuthorityMapper {
	int remove_authority(@Param("id") String id);

	int save_authority(CommonUserAuthority model);

	CommonUserAuthority selectByPrimaryKey(Integer id);

	List<CommonUserAuthority> get_authoritys();
	
	List<CommonUserAuthority> selectCMenu(@Param("pid") String pid,@Param("userid")String userid);

	List<CommonUserAuthority> selectPMenu(List list);
	
	List<CommonUserAuthority> get_authoritys2(Integer id);

	int save_authority_user(CommonUserAuthority model);
	int isExists(CommonUserAuthority model);
	int updateByPrimaryKey(CommonUserAuthority record);
	
	List<CommonUserAuthority> selectDateForTree(@Param("tid") String tid);
	
	int save_menu(CommonUserAuthority record);
	
	int rename(CommonUserAuthority record);
	
	int remove_menu(@Param("id") String id);
}