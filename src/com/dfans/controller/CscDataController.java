package com.dfans.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.dfans.dao.CscDataMapper;
import com.dfans.dao.SJNoticeMapper;
import com.dfans.model.CscData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping(value = "/cscData")
public class CscDataController {
	@Autowired
	private CscDataMapper cscDataDao;
	private static Gson gson = new GsonBuilder().create();
	@Autowired
	private SJNoticeMapper notice;

	@RequestMapping("getDataList")
	@ResponseBody
	public String getDataList(HttpServletRequest request) {
		List<CscData> list = cscDataDao.selectAll();
		return gson.toJson(list);
	}

	@RequestMapping("dataList")
	@ResponseBody
	public String getDataListByPage(HttpServletRequest request) {
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("page", Integer.parseInt(request.getParameter("page")) * 10);
		List<CscData> list = cscDataDao.selectAllPage(m);
		String count = cscDataDao.selectCount(m);
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("count", count);
		ret.put("content", list);
		return gson.toJson(ret);
	}

	@RequestMapping("saveData")
	@ResponseBody
	public String saveCscData(CscData cscData,
			HttpServletRequest request) throws UnsupportedEncodingException {
		int i = 0;
		Map<String, String> ret = new HashMap<String, String>();
		String count = "0";
		count = cscDataDao.selectCountByDataName(cscData);
		if (count != null && !count.equals("0")) {
			ret.put("ret", "数据已经存在");
		} else {
			i = cscDataDao.insertSelective(cscData);
		}				
		if (i > 0) {
			ret.put("ret", "OK");
		}
		return gson.toJson(ret);
	}

	@RequestMapping("deleData")
	@ResponseBody
	public String deleCscData(Integer dataId, HttpServletRequest request) {
		int i = cscDataDao.deleteByPrimaryKey(dataId);
		Map<String, Object> ret = new HashMap<String, Object>();
		if (i > 0) {
			ret.put("ret", "OK");
		}
		return gson.toJson(ret);
	
	}
	@RequestMapping("updateData")
	@ResponseBody
	public String updateData(CscData cscData, HttpServletRequest request) {
		int i = cscDataDao.updateByPrimaryKeySelective(cscData);
		Map<String, Object> ret = new HashMap<String, Object>();
		if (i > 0) {
			ret.put("ret", "OK");
		}
		return gson.toJson(ret);
	}
}
