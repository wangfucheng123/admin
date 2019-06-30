package com.dfans.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dfans.dao.RightTreeMapper;
import com.dfans.dao.SJChannelPriceMapper;
import com.dfans.dao.SJNoticeMapper;
import com.dfans.model.LogModel;
import com.dfans.model.RightTree;
import com.dfans.model.SJChannelPrice;
import com.dfans.utils.LogUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping(value = "/channelPrice")
public class SJ_ChannelPrice {
	@Autowired
	private SJChannelPriceMapper channelPrice;
	@Autowired
	private SJNoticeMapper notice; 
	private static Gson gson = new GsonBuilder().create();

	@RequestMapping({ "/saveChannelPrice" })
	public void saveChannelPrice(HttpServletRequest request, HttpServletResponse response, SJChannelPrice model) {
		String respStr = "{\"flag\":\"false\"}";
		int res = 0; 
		String m="";
		if (!StringUtils.isEmpty(model.getId())) {
			res=channelPrice.updateByPrimaryKey(model);
			m="更改频道信息";
		} else {
			m="保存频道信息";
			res=channelPrice.insert(model);
		}
		if (res > 0) {
			respStr = "{\"flag\":\"true\"}";
		}
		retJosn(response, respStr);
		LogModel record=LogUtil.recordLog(request, Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName(), m); 
		notice.logRecord(record);
	}
	@RequestMapping({ "/getchannelPrices" })
	public void getchannelPrices(HttpServletResponse response, SJChannelPrice model) {  
		List<SJChannelPrice>  list=channelPrice.selectByChannelId(model.getId());
		/**RightTree rightTree=rightTreeMapper.selectByPrimaryKey(Integer.parseInt(model.getId()));
		Map map=new HashMap();
		map.put("rightTree", rightTree);
		map.put("list", list);*/
		retJosn(response, list);
	}
	@RequestMapping({ "/removechannelPrices" })
	public void removechannelPrices(HttpServletRequest request, HttpServletResponse response, SJChannelPrice model) {  
		int res=channelPrice.deleteByPrimaryKey(Integer.parseInt(model.getId())); 
		LogModel record=LogUtil.recordLog(request, Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName(), "删除频道"); 
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
public static void main(String[] args) { 
	
}
}
