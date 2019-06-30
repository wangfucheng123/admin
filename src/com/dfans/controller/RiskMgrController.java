package com.dfans.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dfans.dao.RiskMgrMapper;
import com.dfans.model.RiskMgr;
import com.dfans.service.RiskMgrService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping(value = "/riskmgr")
public class RiskMgrController {
	
	@Autowired
	private RiskMgrService riskMgrService;
	private static Gson gson = new GsonBuilder().create();

	@Autowired
	private RiskMgrMapper riskMgrMapper;
	
	@RequestMapping("/getRiskData")
	public void getRiskDatas() {
	}
	
	@RequestMapping("/getPara")
	public RiskMgr getParaByid(Integer riskId) {
		RiskMgr riskMgr = riskMgrService.selectByPrimaryKey(riskId);
		return riskMgr;
	}
	
	
	@RequestMapping({ "/getRiskParas" })
	@ResponseBody
	public void getRiskParas(HttpServletRequest req , HttpServletResponse res, RiskMgr model) {
		Map<String, Object> map = new HashMap<String,Object>();
		System.out.println("jinru.....");
		map.put("id", model.getId());
		map.put("account" , model.getAccount());
		map.put("singleLimit", model.getSingleLimit());
		map.put("totalLimit", model.getTotalLimit());
		map.put("totalMoney", model.getTotalMoney());
		map.put("trustSpeed", model.getTrustSpeed());
		map.put("cancelCount", model.getCancelCount());
		//添加分页参数
		map.put("pg","pg");
		map.put("page", Integer.parseInt(req.getParameter("page")) * 10);
		List<RiskMgr> list = riskMgrMapper.getRiskParams(map);
		retJosn(res, list);
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
