package com.dfans.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dfans.dao.ActivityMapper;
import com.dfans.model.Activity;

@Controller
@RequestMapping("/userRisg")
public class UserRisgController {
	
	@Autowired
	ActivityMapper activityDao;
	
	@RequestMapping({ "/addActi" })
	@ResponseBody
	public String userRisg(Activity activity,HttpServletRequest hsr) {
		/* HttpServletRequest hsr = null; */
		/* String acti_name = hsr.getParameter("acti_name"); */
		String acti_name = (String) hsr.getAttribute("acti_name");
		System.out.println(activity.toString());
		int insert = activityDao.insert(activity);
		return insert+"";
	}
	@RequestMapping({ "/getAllActi" })
	@ResponseBody
	private List<Activity> getAllActi() {
		List<Activity> selectAll = activityDao.selectAll();
		
		return selectAll;
	}
}
