package com.dfans.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dfans.cache.Memory;
import com.dfans.dao.AliOrderMapper;
import com.dfans.dao.RightTreeMapper;
import com.dfans.dao.UserOrderMapper;
import com.dfans.dao.UserRightRoleMapper;
import com.dfans.model.AliOrder;
import com.dfans.model.RightTree;
import com.dfans.model.SJChannelPrice;
import com.dfans.model.UserOrder;
import com.dfans.model.UserRightRole;
import com.dfans.service.PayService;
import com.dfans.utils.DateUtils;
import com.dfans.utils.StringUtil;
import com.github.wxpay.sdk.WXPay;
import com.pay.FuAnWXConfig;

@Service("payService")
public class PayServiceImpl implements PayService {

	@Autowired
	private RightTreeMapper rightTreeMapper;

	@Autowired
	private AliOrderMapper aliOrderMapper;

	@Autowired
	private UserRightRoleMapper userRightRoleMapper;

	@Autowired
	private UserOrderMapper userOrderMapper;

	final private static String wx_notify_url = "http://sj.dfans.cn/admin/pay/wechat_notify.do";

	/**
	 * 频道权限订单生成
	 * 
	 * @param channelPrice
	 * @param req
	 * @return
	 */
	public String getWXUrlByChannelOrder(SJChannelPrice channelPrice,
			String orderId, HttpServletRequest req) {
		try {
			RightTree tree = rightTreeMapper.selectByPrimaryKey(Integer
					.parseInt(channelPrice.getChannelId()));
			return getWXUrl(tree.getName() + channelPrice.getLimits() + "年",
					orderId, channelPrice.getPrice(), req);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 生成频道权限订单，并返回订单ID
	 * @param channelPrice
	 * @param req
	 * @param prefix VC WX
	 * @return
	 */
	public String getOrderIdChannelOrder(SJChannelPrice channelPrice,
			HttpServletRequest req,String prefix) {
		AliOrder aliOrder = new AliOrder();
		aliOrder.setOrderId(prefix + System.currentTimeMillis());
		aliOrder.setCreatedAt(new Date());
		aliOrder.setPrice(channelPrice.getPrice().toString());
		aliOrder.setTreeId(channelPrice.getChannelId());
		aliOrder.setUserId(Integer.parseInt(StringUtil.getUserId(req)));
		aliOrder.setNum(channelPrice.getLimits());
		int count = aliOrderMapper.insert(aliOrder);
		if (count == 1) {
			return aliOrder.getOrderId();
		}
		return "";
	}

	/**
	 * 生成用户订单，并获取微信支付URL
	 * 
	 * @param channelPrice
	 * @param req
	 * @return
	 */
	public String getWXUrlByUserOrder(SJChannelPrice channelPrice,
			HttpServletRequest req) {
		UserOrder userOrder = userOrderMapper.selectByPrimaryKey(channelPrice
				.getChannelId());
		if (userOrder != null) {
			return getWXUrl(userOrder.getTitle() ==null ? userOrder.getContent() :userOrder.getTitle() , userOrder.getId(),
					channelPrice.getPrice(), req);
		}
		return "";
	}

	/**
	 * 修改频道订单支付成功状态
	 * 
	 * @param out_trade_no
	 * @return
	 */
	public String doChannelOrder(String out_trade_no) {  
		/**
		 * 
		 */
		AliOrder aliOrder = aliOrderMapper.selectByPrimaryKey(out_trade_no); 
		if(out_trade_no.toLowerCase().contains("cz")){
			aliOrder.setStatus(1);
			aliOrder.setUpdatedAt(new Date());
			aliOrderMapper.updateByPrimaryKey(aliOrder);
			Memory.payOrderMap.put(out_trade_no, "{status:ok}");
			return setXML("SUCCESS", "支付成功");
		}
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
					record.setOrderId(out_trade_no); 
					userRightRoleMapper.updateByExample(record);
				} else {
					record = new UserRightRole();
					record.setOrderId(out_trade_no);
					record.setRightId(aliOrder.getTreeId());
					record.setUserId(aliOrder.getUserId());
					record.setStartDate(date);
					record.setEndDate(DateUtils.datePlus(aliOrder.getNum(),
							date));
					record.setUpdateTime(date);
					userRightRoleMapper.insert(record);
				}
				aliOrder.setStatus(1);
				aliOrder.setUpdatedAt(new Date());
				aliOrderMapper.updateByPrimaryKey(aliOrder);
			}
			Memory.payOrderMap.put(out_trade_no, "{status:ok}");
			return setXML("SUCCESS", "支付成功");
		} else {
			return setXML("FAIL", "无该订单");
		}
	}

	/**
	 * 修改用户订单支付成功状态
	 * 
	 * @param out_trade_no
	 * @return
	 */
	public String doUserOrder(String out_trade_no) {
		UserOrder userOrder = userOrderMapper.selectByPrimaryKey(out_trade_no);
		if (userOrder == null) {
			return setXML("FAIL", "无该订单");
		}
		if (!userOrder.getStatus().equals("6")) {
			userOrder.setStatus("6");
			userOrderMapper.updateByPrimaryKeySelective(userOrder);
		}
		Memory.payOrderMap.put(out_trade_no, "{status:ok}");
		return setXML("SUCCESS", "支付成功");
	}

	/**
	 * 生成返回结果
	 * */
	public static String setXML(String return_code, String return_msg) {
		return "<xml><return_code><![CDATA[" + return_code
				+ "]]></return_code><return_msg><![CDATA[" + return_msg
				+ "]]></return_msg></xml>";
	}

	private String getWXUrl(String body, String orderId, Float price,
			HttpServletRequest req) {
		
		try {
			FuAnWXConfig config = new FuAnWXConfig();
			WXPay wxpay = new WXPay(config);
			Map<String, String> data = new HashMap<String, String>();
			data.put("body", body);
			data.put("out_trade_no", orderId);
			data.put("device_info", "");
			data.put("fee_type", "CNY");
			data.put("total_fee",
					String.valueOf(new Float(price * 100).intValue()));
			data.put("spbill_create_ip", "61.50.125.146");
			data.put("notify_url", wx_notify_url);
			data.put("trade_type", "NATIVE"); // 此处指定为扫码支付
			data.put("product_id", "" + orderId);
			System.out.println("data = " + data);
			Map<String, String> resp = wxpay.unifiedOrder(data);
			System.out.println("map : " + resp);
			if (resp != null) {
				return resp.get("code_url");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

}
