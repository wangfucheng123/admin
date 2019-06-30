package com.dfans.service;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.dfans.model.AliOrder;
import com.dfans.model.RightTree;
import com.dfans.model.SJChannelPrice;
import com.dfans.model.UserOrder;
import com.dfans.utils.StringUtil;


public interface PayService {
	
	/**
	 * 频道权限订单生成
	 * 
	 * @param channelPrice
	 * @param req
	 * @return
	 */
	public String getWXUrlByChannelOrder(SJChannelPrice channelPrice, String orderId,
			HttpServletRequest req) ;

	/**
	 * 生成用户订单，并获取微信支付URL
	 * 
	 * @param channelPrice
	 * @param req
	 * @return
	 */
	public String getWXUrlByUserOrder(SJChannelPrice channelPrice,
			HttpServletRequest req) ;
	
	/**
	 * 修改频道订单支付成功状态
	 * @param out_trade_no
	 * @return
	 */
    String doChannelOrder(String out_trade_no);
    
    /**
     * 修改用户订单支付成功状态
     * @param out_trade_no
     * @return
     */
    String doUserOrder(String out_trade_no);
    
    /**
	 * 生成频道权限订单，并返回订单ID
	 * @param channelPrice
	 * @param req
	 * @return
	 */
	String getOrderIdChannelOrder(SJChannelPrice channelPrice,
			HttpServletRequest req,String prefix);

}
