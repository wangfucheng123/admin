package com.dfans.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.dfans.model.User;
import com.dfans.model.UserOederData;
import com.dfans.model.UserOrder;

public interface UserOrderMapper {
	int deleteByPrimaryKey(String id);

	int insert(UserOrder record);
	int insertOrderTimes(Map map);
	String isExist(@Param("name") String name);
	List<Map<String,String>> getTimes(@Param("name") String name);
	int updateOrderTotTimes();
	int updateOrderTimes(@Param("id") String id);
	List<Map<String,String>> getNo();
	
	int insertSelective(UserOrder record); 
	UserOrder selectByPrimaryKey(String id); 
	int updateByPrimaryKeySelective(UserOrder record);

	int updateByPrimaryKeyWithBLOBs(UserOrder record);

	int updateByPrimaryKey(UserOrder record);

	UserOrder getOrderById(String id); 

	List<Map> selectAll(Map m);

	String selectCount(Map m);

	List<UserOederData> selectorderchannel(@Param("userid") String userid);

	List<UserOrder> getDatasByUserId(@Param("userid") String userid);

	UserOrder getDataByUserId(@Param("userid") String userid);

	List<UserOrder> getUserOrders(Map map);

	List<UserOrder> getOrders(Map map);

	List<Object> getStatus();

	List<Object> getOrderStatus();

	List<UserOederData> order_datas(@Param("format") String format, @Param("tableName") String tableName,
			@Param("type") String type, @Param("columnName") String columnName);
}