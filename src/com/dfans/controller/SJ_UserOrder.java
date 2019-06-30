package com.dfans.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.util.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dfans.dao.RiskMgrMapper;
import com.dfans.dao.SJChannelPriceMapper;
import com.dfans.dao.SJConsumerDataMapper;
import com.dfans.dao.SJNoticeMapper;
import com.dfans.dao.UserOrderMapper;
import com.dfans.model.LogModel;
import com.dfans.model.RiskMgr;
import com.dfans.model.SJChannelPrice;
import com.dfans.model.SJConsumerData;
import com.dfans.model.UserOrder;
import com.dfans.service.RiskMgrService;
import com.dfans.utils.LogUtil;
import com.dfans.utils.StringUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping(value = "/userOrder")
public class SJ_UserOrder {
	@Autowired
	private UserOrderMapper userOrder;
	@Autowired
	private SJConsumerDataMapper consumerData;
	@Autowired
	private SJNoticeMapper notice;
	private static Gson gson = new GsonBuilder().create();
	@Autowired
	private SJChannelPriceMapper channelPrice;
	
	@RequestMapping({ "/getUserOrders" })
	public void getUserOrders(HttpServletResponse response, HttpServletRequest req, UserOrder model,String fromwhere) {
		Map<String ,Object> map=new HashMap<String ,Object>();
		map.put("name", model.getName());
		map.put("status", model.getStatus());
		map.put("fromwhere", fromwhere);
		map.put("sdate", model.getStartDate());
		map.put("edate", model.getEndDate()); 
		List<UserOrder> cts = userOrder.getUserOrders(map); 
		map.put("pg", "pg"); 
		map.put("page",Integer.parseInt(req.getParameter("page")) * 10);
		List<UserOrder> list = userOrder.getUserOrders(map);
		Map rmap = new HashMap();
		rmap.put("count", cts.size());
		rmap.put("content", list);
		retJosn(response, rmap);
	}
	@RequestMapping({ "/getOrders" })
	public void getOrders(HttpServletResponse response, HttpServletRequest req, UserOrder model,String fromwhere) {
 		Map<String ,Object> map=new HashMap<String ,Object>();
		map.put("name", model.getName());
		map.put("status", model.getStatus());
		map.put("mtype", model.getMtype());
		map.put("sdate", model.getStartDate());
		map.put("edate", model.getEndDate());  
		List<UserOrder> cts = userOrder.getOrders(map);
		map.put("pg", "pg"); 
		map.put("page",Integer.parseInt(req.getParameter("page"))* 10); 
		List<UserOrder> list = userOrder.getOrders(map);
		Map rmap = new HashMap();
		rmap.put("count", cts.size());
		rmap.put("content", list);
		retJosn(response, rmap);
	}
	@RequestMapping({ "/deleUserOrder" })
	public void deleUserOrder(HttpServletRequest request, HttpServletResponse response, UserOrder model) {
		userOrder.deleteByPrimaryKey(model.getId());
		LogModel record=LogUtil.recordLog(request, Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName(), "删除数据订单"); 
		notice.logRecord(record);
	}

	@RequestMapping({ "/getOrderDataById" })
	public void getOrderDataById(HttpServletResponse response, UserOrder model) {
		UserOrder order = userOrder.getOrderById(model.getId());
		retJosn(response, order);
	}
 
	@RequestMapping({ "/getStatus" })
	public void getStatus(HttpServletResponse response, UserOrder model) {
		List<Object> list = userOrder.getStatus();
		retJosn(response, list);
	}
	@RequestMapping({ "/getOrderStatus" })
	public void getOrderStatus(HttpServletResponse response, UserOrder model) {
		List<Object> list = userOrder.getOrderStatus();
		retJosn(response, list);
	}
	@RequestMapping({ "/updateStatus" })
	public String updateStatus(HttpServletResponse response, UserOrder model, String url, String cid,String type,String flag,
			String description) { 
		SJConsumerData cData = new SJConsumerData();
		String[] urls = new String[] {};
		String[] descs = new String[] {};
		String[] ids = new String[] {};
		String[] types = new String[] {};
		if (!StringUtils.isEmpty(url)) {
			urls = url.split(","); 
		}
		if (!StringUtils.isEmpty(cid)) {
			ids = cid.split(","); 
		}
		if (!StringUtils.isEmpty(description)) {
			descs = description.split(","); 
		}
		if (!StringUtils.isEmpty(type)) {
			types = type.split(","); 
		}
		int num = urls.length;
		for (int i = 0; i < num; i++) {
			if (ids.length == 0 || StringUtils.isEmpty(ids[i].trim())) {
				cData.setUrl(urls[i]);
				cData.setDescription(descs[i]);
				cData.setUserId(model.getId());
				cData.setType(types[i]);
				cData.setCreatedate(DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
				cData.setUpdatedate(DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
				consumerData.insert(cData);
			}else{
				cData.setId(Integer.parseInt(ids[i]));
				cData.setUrl(urls[i]);
				cData.setDescription(descs[i]);
				cData.setUserId(model.getId());
				cData.setType(types[i]); 
				cData.setUpdatedate(DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss")); 
				consumerData.updateByPrimaryKey(cData);
			}
		}
		userOrder.updateByPrimaryKeySelective(model);
		
		if(!StringUtils.isEmpty(model.getPrice())&&!"0".equals(model.getPrice())){
			SJChannelPrice mod=new SJChannelPrice ();
			mod.setChannelId(model.getId());
			mod.setPrice(Float.parseFloat(model.getPrice()));
			List<SJChannelPrice> list=channelPrice.selectByChannelId(model.getId());
			if(null!=list&&list.size()>0){
				SJChannelPrice cp=list.get(0);
				cp.setPrice(Float.parseFloat(model.getPrice()));
				channelPrice.updateByPrimaryKey(cp);
			}else{
				channelPrice.insert(mod);
			}
		}
		if (StringUtils.isEmpty(flag)) {
			return "redirect:/backed/sj_order/list.jsp";
		}else{
			return "redirect:/backed/sj_order/orderlist.jsp";
		}
		
	}

	@RequestMapping({ "/getCData" })
	public void getCData(HttpServletResponse response, SJConsumerData model) {
		List<SJConsumerData> list = consumerData.selectDatasByUserId(model.getUserId());
		retJosn(response, list);
	}
	@RequestMapping({ "/getCusPage" })
	public void getCusPage(HttpServletResponse response, String uid) {
		List<SJConsumerData> list = consumerData.getCusPage(uid);
		for(SJConsumerData page:list){
			if("研究报告".equals(page.getDescription())||"机构预测".equals(page.getDescription())||"基金深度数据".equals(page.getDescription())){
				page.setSubTitle("中信建投"); 
				page.setPicUrl("");
			}else if("A股监测器".equals(page.getDescription())||"新股备忘".equals(page.getDescription())){
				page.setSubTitle("深圳信息");
				page.setPicUrl("");	
			}else if("新三板".equals(page.getDescription())){
				page.setSubTitle("恒生聚源");
				page.setPicUrl("");
			}
		}
		retJosn(response, list);
	}
	@RequestMapping({ "/removeCData" })
	public void removeCData(HttpServletRequest request,HttpServletResponse response, SJConsumerData model) {
		int res = consumerData.deleteByPrimaryKey(model.getId());
		LogModel record=LogUtil.recordLog(request, Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName(), "删除定制数据"); 
		notice.logRecord(record);
	} 
 
	
	public static void retJosn(HttpServletResponse response, Object obj) {
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(gson.toJson(obj));
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
