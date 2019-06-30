package com.dfans.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.dfans.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

	List<User> selectAll(Map m);

	List<Map> selectUnionCscAll(Map<String, Object> m);
	
	List<User> getList(@Param("sql")String pSql);
	String selectCount(Map m);
	String total(@Param("sql")String pSql);
	int updateAccount(User user);

	User getLogin(User user);

	User getUser(User user);
	
	User getHeadPortrait(User user);

	int upPassword(User user);
	
	int updateHeadPortrait(User user);
	
	int updatePhone(User user);
	
	int updateEmail(User user);
	
	int insertFeedback(Map<?, ?> map);

	String selectByPhone(User user);
	String selectByName(User user);
	List<String> selectAllUserId(Map m);
	List<String> phoneList(@Param("phone")String phone);

	int insertVersion(Map m);

	Map selectVersion(String flag);
	
	Map searchHistory(Map<?, ?> map);
	
	int insertHistory(Map<?, ?> map);
	
	int updateHistory(Map<?, ?> map);

	User getChkLogin(User temp);

}