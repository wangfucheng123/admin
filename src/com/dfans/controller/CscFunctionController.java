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
import com.dfans.dao.CscFunctionMapper;
import com.dfans.dao.SJNoticeMapper;
import com.dfans.model.CscFunction;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping(value = "/cscFunction")
// 通过这里配置使下面的映射都在/users下
public class CscFunctionController {
	@Autowired
	private CscFunctionMapper cscFunctionDao;
	private static Gson gson = new GsonBuilder().create();
	@Autowired
	private SJNoticeMapper notice;

	@RequestMapping("getFunctionList")
	@ResponseBody
	public String getFunctionList(HttpServletRequest request) {
		List<CscFunction> list = cscFunctionDao.selectAll();
		return gson.toJson(list);
	}

	@RequestMapping("FunctionList")
	@ResponseBody
	public String FunctionList(HttpServletRequest request) {
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("page", Integer.parseInt(request.getParameter("page")) * 10);
		List<CscFunction> list = cscFunctionDao.selectAllPage(m);
		String count = cscFunctionDao.selectCount(m);
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("count", count);
		ret.put("content", list);
		return gson.toJson(ret);
	}

	@RequestMapping("saveFunction")
	@ResponseBody
	public String saveFunction(CscFunction cscFunction,
			HttpServletRequest request) throws UnsupportedEncodingException {
		int i = 0;
		Map<String, String> ret = new HashMap<String, String>();		
		String count = "0";
		count = cscFunctionDao.selectCountByFunctionName(cscFunction);
		if (count != null && !count.equals("0")) {
			ret.put("ret", "该功能已经存在");
		} else {
			i = cscFunctionDao.insertSelective(cscFunction);
		}
		
		if (i > 0) {
			ret.put("ret", "OK");
		}
		return gson.toJson(ret);
	}

	@RequestMapping("deleFunction")
	@ResponseBody
	public String deleFunction(Integer functionId, HttpServletRequest request) {
		int i = cscFunctionDao.deleteByPrimaryKey(functionId);
		Map<String, Object> ret = new HashMap<String, Object>();
		if (i > 0) {
			ret.put("ret", "OK");
		}
		return gson.toJson(ret);
	}
	
	
	@RequestMapping("updateFunction")
	@ResponseBody
	public String updateFunction(CscFunction cscFunction, HttpServletRequest request) {
		int i = cscFunctionDao.updateByPrimaryKeySelective(cscFunction);
		Map<String, Object> ret = new HashMap<String, Object>();
		if (i > 0) {
			ret.put("ret", "OK");
		}
		return gson.toJson(ret);
	}

}
