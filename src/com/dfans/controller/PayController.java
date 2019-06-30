package com.dfans.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
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

import com.dfans.cache.Memory;
import com.dfans.dao.SJChannelPriceMapper;
import com.dfans.model.AliOrder;
import com.dfans.model.SJChannelPrice;
import com.dfans.service.PayService;
import com.dfans.service.impl.PayServiceImpl;
import com.dfans.utils.QRCodeUtil;
import com.dfans.utils.StringUtil;
import com.github.wxpay.sdk.WXPay;
import com.pay.FuAnWXConfig;
import com.wxpay.config.WxPayConfig;
import com.wxpay.utils.WeixinPayUtil;


@Controller 
@RequestMapping(value = "/pay")
public class PayController {
	
	@Autowired
	private SJChannelPriceMapper channelPriceMapper;

	@Resource(name="payService")
	private PayService payService;

	/**
	 * 生成微信订单二维码
	 * @param request
	 * @param response
	 * @param price
	 * @param channelPriceId
	 * @throws IOException
	 */
	@RequestMapping({ "/getQrcode" })
	public void getImage(String price, String channelPriceId, String orderId, HttpServletRequest request, HttpServletResponse response) throws IOException {
		if(StringUtil.isEmpty(price) && StringUtil.isEmpty(channelPriceId)) {
			return;
		}
		if(StringUtil.isEmpty(channelPriceId)) {
			channelPriceId = price;
		}
		// 禁止图像缓存。
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		try {
			// 将图像输出到Servlet输出流中。
			ServletOutputStream sos = response.getOutputStream();
			String code_url = getCordUrl(request, orderId, channelPriceId);
			System.out.println("code_url:" + code_url);
			if(StringUtils.isNotEmpty(code_url)) {
			    ImageIO.write(QRCodeUtil.createImageUrl(code_url), "jpeg", sos);
			    sos.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取微信订单URL
	 * @param req
	 * @param channelPriceId
	 * @return
	 */
	public String getCordUrl(HttpServletRequest req,String orderId, String channelPriceId) {
		SJChannelPrice channelPrice = channelPriceMapper
				.selectByPrimaryKey(Integer.parseInt(channelPriceId));
		if (channelPrice == null) {
			return "";
		}
		// 用户订单
		if (channelPrice.getChannelId().toLowerCase().contains("sj")) {
			return payService.getWXUrlByUserOrder(channelPrice, req);
		} else {
			if (orderId == null) {
				return "";
			}
			// 频道开通支付
			return payService.getWXUrlByChannelOrder(channelPrice, orderId, req);
		}
	}
	

	/**
	 * 微信异步回调，不会跳转页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/wechat_notify.do")
	public synchronized void weixinReceive(HttpServletRequest request,HttpServletResponse response, Model model){
		System.out.println("==开始进入h5支付回调方法==");
		String xml_review_result = WeixinPayUtil.getXmlRequest(request);
		System.out.println("微信支付结果:"+xml_review_result);
		String noticeStr = "";
		Map<String, String> resultMap = null;
		try {
			resultMap = WeixinPayUtil.doXMLParse(xml_review_result);
			System.out.println("resultMap:"+resultMap);
			if(resultMap != null && resultMap.size() > 0){
				String sign_receive = (String)resultMap.get("sign");
				System.out.println("sign_receive:"+sign_receive);
				resultMap.remove("sign");
				String checkSign = WeixinPayUtil.getSign(resultMap,WxPayConfig.partner_key);
				System.out.println("checkSign:"+checkSign);

				//签名校验成功
				if(checkSign != null && sign_receive != null &&
						checkSign.equals(sign_receive.trim())){
					System.out.println("weixin receive check Sign sucess");
                    try{
                    	//获得返回结果
                    	String return_code = (String)resultMap.get("return_code");
                    
                    	if("SUCCESS".equals(return_code)){
                    		String out_trade_no = (String)resultMap.get("out_trade_no");
                    		//String transaction_id = (String)resultMap.get("transaction_id");
                    		//String resultCode= (String)resultMap.get("result_code");
                    		System.out.println("weixin pay sucess,out_trade_no:"+out_trade_no);
                    		if (out_trade_no != null) {
                    			// 频道支付订单
                    			if (out_trade_no.toLowerCase().contains("wx")) {
                    				noticeStr = payService.doChannelOrder(out_trade_no);                    				
                    			}else if(out_trade_no.toLowerCase().contains("cz")){ 
                    				noticeStr = payService.doChannelOrder(out_trade_no);    
                    			}else {
                    				// 用户订单
                    				noticeStr = payService.doUserOrder(out_trade_no);                    				
                    			}
                    		} else {
                    			noticeStr = PayServiceImpl.setXML("FAIL", "通信失败");
                    		}
                    		
                    	}else{
                    		noticeStr = PayServiceImpl.setXML("FAIL", "通信失败");
                    	}
                    }catch(Exception e){
                    	e.printStackTrace();
                    }
				}else{
					//签名校验失败
					System.out.println("weixin receive check Sign fail");
                    String checkXml = "<xml><return_code><![CDATA[FAIL]]></return_code>"+
                    					"<return_msg><![CDATA[check sign fail]]></return_msg></xml>";  
                    response.getWriter().print(new ByteArrayInputStream(checkXml.getBytes()));
				}
			}
			response.getWriter().print(new ByteArrayInputStream(noticeStr.getBytes(Charset.forName("UTF-8"))));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	 public static void main(String[] args) throws Exception {

	        FuAnWXConfig config = new FuAnWXConfig();
	        WXPay wxpay = new WXPay(config);

	        Map<String, String> data = new HashMap<String, String>();
	        data.put("body", "腾讯充值中心-QQ会员充值");
	        data.put("out_trade_no", "2017091010595900000012");
	        data.put("device_info", "");
	        data.put("fee_type", "CNY");
	        data.put("total_fee", "1");
	        data.put("spbill_create_ip", "123.12.12.123");
	        data.put("notify_url", "http://sj.dfans.cn/admin/pay/wechat_notify.do");
	        data.put("trade_type", "NATIVE");  // 此处指定为扫码支付
	        data.put("product_id", "12");
	        try {
	            Map<String, String> resp = wxpay.unifiedOrder(data);
	            System.out.println(resp);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
		



}
