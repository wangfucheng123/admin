package com.dfans.service.impl;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dfans.cache.UserMemoy;
import com.dfans.dao.AccountChangeMapper;
import com.dfans.dao.OrderMapper;
import com.dfans.dao.RightTreeMapper;
import com.dfans.dao.UserMapper;
import com.dfans.dao.UserRightRoleMapper;
import com.dfans.model.AccountChange;
import com.dfans.model.Admin;
import com.dfans.model.Order;
import com.dfans.model.RightTree;
import com.dfans.model.User;
import com.dfans.model.UserRightRole;
import com.dfans.service.UserService;
import com.dfans.utils.DateUtils;
import com.dfans.utils.DesUtils;
import com.dfans.utils.IpUtils;

@Service("userService")
public class UserServiceImpl implements UserService {

	
	@Autowired
	private RightTreeMapper rightTreeDao;
	
	@Autowired
	private UserMapper userDao;
	
	@Autowired
	private UserRightRoleMapper userRightRoleDao;
	
	@Autowired
	private AccountChangeMapper accountChangeDao;
	
	@Autowired
	private OrderMapper orderDao;
	
	public Map save(HttpServletRequest request) throws ParseException {
		Map ret=new HashMap();
		String roleId=request.getParameter("roleId");
		String userId=request.getParameter("userId");
		String rightId[]=request.getParameter("rightId").split(",");
		String startDate[]=request.getParameter("startDate").split(",");
		String sel[]=request.getParameter("sel").split(",");
		String num[]=request.getParameter("num").split(",");
		String dis[]=request.getParameter("dis").split(",");
		System.out.println("rightid="+rightId[0]);
		if(request.getParameter("rightId").equals("") || request.getParameter("rightId")==null)
		{
			User temp=new User();
			temp.setId(Integer.parseInt(userId));
			temp.setRoleId(Integer.parseInt(roleId));
			int i=userDao.updateByPrimaryKeySelective(temp);
			userRightRoleDao.deleteByUser(Integer.parseInt(userId));
			ret.put("ret","OK");
			return ret;
		}
		Order order=new Order();
		User user=userDao.selectByPrimaryKey(Integer.parseInt(userId));
		String tempPrice="";
		for(int i=0;i<rightId.length;i++)
		{
			RightTree temp=rightTreeDao.selectByPrimaryKey(Integer.parseInt(rightId[i]));
			tempPrice=tempPrice+temp.getPrice()+",";
		}
		if(!tempPrice.equals(""))
		{
			tempPrice=tempPrice.substring(0,tempPrice.length()-1);
		}
		String price[]=tempPrice.split(",");
		float totlePrice=0;
		for(int i=0;i<price.length;i++)
		{
			int t=1;
			if(sel[i].equals("1"))
			{
				t=12;
			}
			float p=Float.parseFloat(price[i])*Integer.parseInt(num[i])*t*Float.parseFloat(dis[i]);
			totlePrice=totlePrice+p;
		}
		if(totlePrice>user.getBalance())
		{
			ret.put("ret","余额不足！");
			return ret;
		} 
		userRightRoleDao.deleteByUser(Integer.parseInt(userId));
		for(int i=0;i<rightId.length;i++)
		{
			UserRightRole record=new UserRightRole();
			record.setUserId(Integer.parseInt(userId));
			
			record.setRightId(rightId[i]);
			int t=1;
			if(sel[i].equals("1"))
			{
				t=12;
			}
			if(!"-".equals(startDate[i])&&!"".equals(startDate[i])){
				record.setStartDate(DateUtils.sdf.parse(startDate[i]));
				record.setEndDate(DateUtils.sdf.parse(DateUtils.reNextMonthDate(startDate[i], Integer.parseInt(num[i])*t)));
				order.setStartDate(DateUtils.sdf.parse(startDate[i]));
				order.setEndDate(DateUtils.sdf.parse(DateUtils.reNextMonthDate(startDate[i], Integer.parseInt(num[i])*t)));
				}
			userRightRoleDao.insert(record);
			
			
			order.setAdminId(((Admin) request.getSession().getAttribute("admin")).getId()); 
			order.setPrice(Float.parseFloat(price[i])*Integer.parseInt(num[i])*t*Float.parseFloat(dis[i]));
			order.setUserId(Integer.parseInt(userId));
			order.setRightId(Integer.parseInt(rightId[i]));
			order.setDis(Float.parseFloat(dis[i]));
			orderDao.insertSelective(order);
		}
		
		user.setBalance(-totlePrice);
		userDao.updateAccount(user);
		
		User temp=new User();
		temp.setId(Integer.parseInt(userId));
		temp.setRoleId(Integer.parseInt(roleId));
		int i=userDao.updateByPrimaryKeySelective(temp);

		AccountChange accountChange=new AccountChange();
		accountChange.setAdminId(((Admin) request.getSession().getAttribute("admin")).getId());
		accountChange.setAmount(user.getBalance());
		accountChange.setUserId(user.getId());
		accountChange.setType(1);
		accountChangeDao.insertSelective(accountChange);
		ret.put("ret","OK");
		return ret;
	}

	public Map getLogin(HttpServletRequest request) throws Exception {
		Map ret=new HashMap();
		String phone=request.getParameter("phone");
		String password = request.getParameter("password");
//		DesUtils des = null;
//		try {
//			des = new DesUtils("shujin");
//			password = des.decrypt(request.getParameter("password"));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		User temp=new User();
		temp.setPhone(phone);
		temp.setPassword(password);
		User tempU=userDao.getChkLogin(temp);
		if(tempU==null)
		{
			ret.put("status","0");
			ret.put("msg","该用户不存在！");
			return ret;
		}
		User user=userDao.getLogin(temp);
		if(user==null)
		{
			ret.put("status","0");
			ret.put("msg","错误的用户名或密码！");
		}
		else
		{
			ret.put("status","1");
			ret.put("userId",user.getId());
			ret.put("headPortrait",user.getHeadPortrait());
			//ret.put("screenNum",user.getScreenNum());
			List<Map> userRight=userRightRoleDao.selectNowAll(user.getId());
			Map sn=new HashMap();
			sn.put("name", "屏幕数");
			sn.put("rightId", user.getScreenNum());
			sn.put("code","screenNum");
			userRight.add(sn);
			ret.put("right",userRight);
			
//			String version=request.getParameter("version");
//			if(StringUtils.isNotEmpty(version)){
//				Map ver=userDao.selectVersion();
//				//System.out.println(ver.toString());
//				if(Integer.parseInt(ver.get("version").toString().replace(".",""))>Integer.parseInt(version.replace(".","")))
//				{
//					ret.put("type",ver.get("type"));
//					ret.put("url",ver.get("url"));
//				}
//			}
			try{
			Map m=new HashMap();
			m.put("ip",IpUtils.getIpAddr(request));
			m.put("mAddr",request.getParameter("mAddr"));
			UserMemoy.userMap.put(""+user.getId(),m);
			}
			catch(Exception e)
			{
				Map m=new HashMap();
				m.put("ip",IpUtils.getIpAddr(request));
				m.put("mAddr","");
				UserMemoy.userMap.put(""+user.getId(),m);
			}
		}
		return ret;
	}

}
