package com.dfans.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dfans.cache.Memory;
import com.dfans.dao.AliOrderMapper;
import com.dfans.dao.UserRightRoleMapper;
import com.dfans.model.AliOrder;
import com.dfans.model.UserRightRole;
import com.dfans.service.ISJ_VirtualCoin;
import com.dfans.utils.DateUtils;
import com.dfans.utils.StringUtil;

@Service("virtualCoin")
public class SJ_VirtualCoin implements ISJ_VirtualCoin {
	@Autowired
	private AliOrderMapper aliOrderMapper;
	@Autowired
	private UserRightRoleMapper userRightRoleMapper;
	public String getOrderId(HttpServletRequest req) {
		String userid = StringUtil.getUserId(req);
		AliOrder aliOrder = new AliOrder();
		aliOrder.setOrderId("CZ" + System.currentTimeMillis());
		aliOrder.setCreatedAt(new Date());
		aliOrder.setPrice(req.getParameter("priceNum"));
		aliOrder.setTreeId("top_up");
		aliOrder.setUserId(Integer.parseInt(userid));
		aliOrder.setNum(0);
		int count = aliOrderMapper.insert(aliOrder);
		if (count == 1) {
			return aliOrder.getOrderId();
		}
		return "";
	}
	public int updateChannelLimit(String orderId){
		AliOrder aliOrder = aliOrderMapper.selectByPrimaryKey(orderId); 
		int res=0;
		if (aliOrder != null) {
			Date date = new Date();
			UserRightRole record = userRightRoleMapper.selectByuIdAndtId(
					String.valueOf(aliOrder.getUserId()),
					String.valueOf(aliOrder.getTreeId()));
			if (aliOrder.getStatus() == 0) {
				if (record != null) {
					if (date.getTime() > record.getEndDate().getTime()) {
						record.setStartDate(date);
						record.setEndDate(DateUtils.datePlus(aliOrder.getNum(),
								date));
					}else
					if (date.getTime() <= record.getEndDate().getTime()) {
						record.setEndDate(DateUtils.datePlus(aliOrder.getNum(),
								record.getEndDate()));
					}
					record.setOrderId(orderId); 
					res=userRightRoleMapper.updateByExample(record);
				} else {
					record = new UserRightRole();
					record.setOrderId(orderId);
					record.setRightId(aliOrder.getTreeId());
					record.setUserId(aliOrder.getUserId());
					record.setStartDate(date);
					record.setEndDate(DateUtils.datePlus(aliOrder.getNum(),
							date));
					record.setUpdateTime(date);
					res=userRightRoleMapper.insert(record);
				}
				aliOrder.setStatus(1);
				aliOrder.setUpdatedAt(new Date());
				aliOrderMapper.updateByPrimaryKey(aliOrder);Memory.payOrderMap.put(orderId, "{status:ok}");
				 
			}  
		} 	return res;
	}
}
