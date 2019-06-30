package com.dfans.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dfans.cache.Memory;
import com.dfans.dao.AliOrderMapper;
import com.dfans.dao.SJChannelPriceMapper;
import com.dfans.dao.SjConsumeritemsMapper;
import com.dfans.dao.SjVirtualcoinMapper;
import com.dfans.dao.UserMapper;
import com.dfans.model.SJChannelPrice;
import com.dfans.model.User;
import com.dfans.service.ISJ_VirtualCoin;
import com.dfans.service.PayService;
import com.dfans.utils.QRCodeUtil;
import com.dfans.utils.StringUtil;
import com.github.wxpay.sdk.WXPay;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pay.FuAnWXConfig;

@Controller
@RequestMapping(value = "/virtualCoin")
public class SJ_VirtualCoin {
	@Autowired
	private SjVirtualcoinMapper virtualcoinMapper;
	@Autowired
	private SjConsumeritemsMapper consumeritemsMapper;
	private static Gson gson = new GsonBuilder().create();
	@Autowired
	private SJChannelPriceMapper channelPriceMapper;
	@Resource(name = "virtualCoin")
	private ISJ_VirtualCoin virtualCoin;
	@Resource(name = "payService")
	private PayService payService;
	@Autowired
	private AliOrderMapper aliOrderMapper;
	@Autowired
	private UserMapper userDao;
	final double DISCOUNT=0.98;
	/**
	 * 充值  返回二維碼的 url
	 * @param req
	 */
	@RequestMapping(value = "/top_up")
	@ResponseBody
	public String top_up(HttpServletRequest req) {
		String userid = StringUtil.getUserId(req);
		String orderId=virtualCoin.getOrderId(req);
		
		if (!StringUtils.isEmpty(orderId)) { 
			Map<String, Object> map = new HashMap<String, Object>();
			BigDecimal bg = new BigDecimal(Integer.parseInt(req.getParameter("amount"))*DISCOUNT/10).setScale(2, RoundingMode.UP);
			map.put("qrcode", "../virtualCoin/getQrcode.do?pricenum="+bg.doubleValue()+"&orderId="+orderId);
			map.put("orderId", orderId);
			return gson.toJson(map);
		}
		return "";
	}
	/**
	 * 充值页
	 * @return
	 */
	@RequestMapping(value = "/toTopup")
	public String toTopup(HttpServletRequest req, Model m ) {   
		User user=userDao.selectByPrimaryKey(  Integer.parseInt(StringUtil.getUserId(req)));
		m.addAttribute("phone", user.getPhone());
		return "top_up";
	}
	/**
	 * 充值成功
	 * @return
	 */
	@RequestMapping(value = "/topup_suc")
	public String topUpsuc(String amount,HttpServletRequest req) {  
		String userid = StringUtil.getUserId(req);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", userid);
		map.put("amount", "+" + amount);
		map.put("remark", "");
		map.put("type", "金币充值");
		int rs = consumeritemsMapper.saveCoinsItem(map); 
		if(StringUtils.isNotEmpty(virtualcoinMapper.isExist(userid))){
			int rs0 = virtualcoinMapper.updateCoins(userid, Integer.parseInt(req.getParameter("amount")),"plus");
		}else{
			int rs0 = virtualcoinMapper.saveCoins(userid, Integer.parseInt(req.getParameter("amount")));
		} 
		return "topup_suc";
	}
	/**
	 *  终端使用页面
	 * @param req
	 * @param response
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/toMyVirtualCoin")
	public String toMyVirtualCoin(HttpServletRequest req, HttpServletResponse response, Model m) {
		String userid = StringUtil.getUserId(req);
		int amount = virtualcoinMapper.findCoins(userid);
		String isSign = consumeritemsMapper.isSign(userid);
		if ("0".equals(isSign)) {
			m.addAttribute("isSigned", "");
		} else
			m.addAttribute("isSigned", "isSigned");
		m.addAttribute("amount", amount);
		return "virtual_coin";
	}

	/**
	 * 签到
	 */
	@RequestMapping(value = "/sign")
	@ResponseBody
	public String sign(HttpServletRequest req) {
		String userid = StringUtil.getUserId(req);
		int amount = 2;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", userid);
		map.put("amount", "+" + amount);
		map.put("remark", "");
		map.put("type", "签到得金币");
		int rs = consumeritemsMapper.saveCoinsItem(map);
		if(StringUtils.isNotEmpty(virtualcoinMapper.isExist(userid))){
			int rs0 = virtualcoinMapper.updateCoins(userid, amount,"plus");
		}else{
			int rs0 = virtualcoinMapper.saveCoins(userid, amount);
		} 
		if (rs > 0) {
			int rest = virtualcoinMapper.findCoins(userid);
			return gson.toJson(rest);
		}
		return "";
	}

	/**
	 * 记录查询 flag： minus 使用记录 plus 获取记录 空 全部记录 获取明细
	 */
	@RequestMapping(value = "/findItems")
	@ResponseBody
	public String findItems(HttpServletRequest req, String flag) {
		String userid = StringUtil.getUserId(req);
		List<Map<String, String>> map = consumeritemsMapper.findItems(userid, flag,
				Integer.parseInt(req.getParameter("start")), Integer.parseInt(req.getParameter("num")));
		Map<String, String> m = new HashMap<String, String>();
		String totles = consumeritemsMapper.totles(userid, flag);
		m.put("totles", totles);
		map.add(m);
		return gson.toJson(map);
	}

	/**
	 * 消费 修改总数
	 */
	@RequestMapping(value = "/updateCoins")
	@ResponseBody
	public String updateCoins(HttpServletRequest req) {
		String orderId = "";
		SJChannelPrice channelPrice = channelPriceMapper
				.selectByPrimaryKey(Integer.parseInt(req.getParameter("price")));
		if (!channelPrice.getChannelId().toLowerCase().contains("sj")) {
			// 频道开通支付
			orderId = payService.getOrderIdChannelOrder(channelPrice, req, "VC");
		}
		if (!StringUtils.isEmpty(orderId)) {
			int res=virtualCoin.updateChannelLimit(orderId);
			String userid = StringUtil.getUserId(req);
			int amount = Integer.parseInt(req.getParameter("amount"));
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userid", userid);
			map.put("amount", "-"+amount);
			map.put("remark", "订单号：" + orderId);
			map.put("type", "购物消费");
			int rs = consumeritemsMapper.saveCoinsItem(map);
			int rs0 = virtualcoinMapper.updateCoins(userid, amount,"minus");
			if(rs0>0){
				return orderId;
			} 
		}return "";
	}

	/**
	 * 虚拟币总数
	 */
	@RequestMapping(value = "/coins")
	@ResponseBody
	public String coins(HttpServletRequest req) {
		String userid = StringUtil.getUserId(req);
		int amount = virtualcoinMapper.findCoins(userid);
		return gson.toJson(amount);
	}
	/**
	 * 此处是充值的二维码 有别于订单的业务
	 * @param request
	 * @param response
	 * @param pricenum 充值额
	 * @param channelPriceId
	 * @throws IOException
	 */
	@RequestMapping({ "/getQrcode" })
	public void getImage(String pricenum, String channelPriceId, String orderId, HttpServletRequest request, HttpServletResponse response) throws IOException {
		if(StringUtil.isEmpty(pricenum)) {
			return;
		} 
		// 禁止图像缓存。
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		try {
			// 将图像输出到Servlet输出流中。
			ServletOutputStream sos = response.getOutputStream();
			String code_url = getWXUrl("金币充值", orderId, Float.parseFloat(pricenum),request);
			System.out.println("code_url:" + code_url);
			if(StringUtils.isNotEmpty(code_url)) {
			    ImageIO.write(QRCodeUtil.createImageUrl(code_url), "jpeg", sos);
			    sos.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static String getWXUrl(String body, String orderId, Float price,
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
	} @RequestMapping("getOrderStatus")
	@ResponseBody
	public Object getOrderStatus(HttpServletRequest req, String orderId) {
		String status = Memory.payOrderMap.get(orderId);
		Map map = new HashMap();
		if (status == null) {
			map.put("status", "");
		} else {
			map.put("status", "OK");
		}
		return gson.toJson(map);
	}
	private static String wx_notify_url = "http://sj.dfans.cn/admin/pay/wechat_notify.do";
}
