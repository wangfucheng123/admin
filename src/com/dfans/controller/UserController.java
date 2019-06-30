package com.dfans.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dfans.cache.Info;
import com.dfans.cache.Memory;
import com.dfans.cache.UserMemoy;
import com.dfans.dao.AccountChangeMapper;
import com.dfans.dao.AppUserMapper;
import com.dfans.dao.CscMenuMapper;
import com.dfans.dao.JoinMeetMapper;
import com.dfans.dao.RightTreeMapper;
import com.dfans.dao.RoleRightMapper;
import com.dfans.dao.SJNoticeMapper;
import com.dfans.dao.SjConsumeritemsMapper;
import com.dfans.dao.SjVirtualcoinMapper;
import com.dfans.dao.TCityMapper;
import com.dfans.dao.TCountryMapper;
import com.dfans.dao.TProvinceMapper;
import com.dfans.dao.UserMapper;
import com.dfans.dao.UserMsgMapper;
import com.dfans.dao.UserOrderMapper;
import com.dfans.dao.UserRightRoleMapper;
import com.dfans.model.AccountChange;
import com.dfans.model.Admin;
import com.dfans.model.AppUser;
import com.dfans.model.CscMenu;
import com.dfans.model.JoinMeet;
import com.dfans.model.LogModel;
import com.dfans.model.MenuTree;
import com.dfans.model.TCity;
import com.dfans.model.TCountry;
import com.dfans.model.TProvince;
import com.dfans.model.User;
import com.dfans.model.UserMsg;
import com.dfans.model.UserOrder;
import com.dfans.service.UserService;
import com.dfans.utils.IpUtils;
import com.dfans.utils.LogUtil;
import com.dfans.utils.MD5Utils;
import com.dfans.utils.StringUtil;
import com.dfans.utils.Tools;
import com.dfans.utils.TreeUtils;
import com.dfans.utils.UploadImgs;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private SJNoticeMapper notice;
	@Autowired
	private UserMapper userDao;
	@Autowired
	private SjConsumeritemsMapper consumeritemsMapper;
	@Autowired
	private AccountChangeMapper accountChangeDao;

	@Autowired
	private UserRightRoleMapper userRightRoleDao;

	@Autowired
	private RoleRightMapper roleRightDao;

	@Autowired
	private TProvinceMapper tProvinceDao;

	@Autowired
	private TCityMapper tCityDao;

	@Autowired
	private TCountryMapper tCountryDao;

	@Autowired
	private UserService userService;

	@Autowired
	private UserMsgMapper userMsgDao;

	@Autowired
	private UserOrderMapper userOrderDao;

	@Autowired
	private RightTreeMapper rightTreeDao;
	
	@Autowired
	private JoinMeetMapper joinMeetMapper;
	@Autowired
	private SjVirtualcoinMapper virtualcoinMapper;
	
	@Autowired
	private AppUserMapper appUserDao;
	@Autowired
	private CscMenuMapper cscMenuDao;
	
	private static Gson gson = new GsonBuilder().create();

	@RequestMapping("userLogin")
	@ResponseBody
	public String getUserLogin(HttpServletRequest request) throws Exception {
		Map ret = new HashMap();
		ret = userService.getLogin(request);
		return gson.toJson(ret);
	}
	
	
	@RequestMapping("userLogout")
	@ResponseBody
	public String getUserLoginOut(HttpServletRequest request) throws Exception {
		
		if(IpUtils.checkUserLogin(request.getParameter("userId"),request)==false)
		{
			return null;
		}
		UserMemoy.userMap.remove(request.getParameter("userId"));
		return null;
	}
	
	@RequestMapping("getUserLog")
	@ResponseBody
	public String getUserLog(HttpServletRequest request) throws Exception {
		
//		if(IpUtils.checkUserLogin(request.getParameter("userId"),request)==false)
//		{
//			return null;
//		}
		return gson.toJson(UserMemoy.userMap.get(request.getParameter("userId")));
		
	}
	
	
	@RequestMapping("userVersion")
	@ResponseBody
	public String getUserVersion(HttpServletRequest request) throws Exception {

		Map m=new HashMap();
		m.put("type", request.getParameter("type"));
		m.put("version", request.getParameter("version"));
		m.put("url", request.getParameter("url"));
		m.put("flag", request.getParameter("flag"));
		userDao.insertVersion(m);
		return null;
	}
	
	
	@RequestMapping("getLastVersion")
	@ResponseBody
	public String getLastVersion(HttpServletRequest request) throws Exception {
		Map m=userDao.selectVersion(request.getParameter("flag"));
		return gson.toJson(m);
	}
	
	
	@RequestMapping("getMenuTree")
	@ResponseBody
	public String getMenuTree(HttpServletRequest request) throws Exception {
		List<MenuTree> rootMenu=rightTreeDao.selectSjTreeList();
		
		List<MenuTree> menuList = new ArrayList<MenuTree>();
	    // 先找到所有的一级菜单
	    for (int i = 0; i < rootMenu.size(); i++) {
	        // 一级菜单没有parentId
	        if (rootMenu.get(i).getPid()==0) {
	            menuList.add(rootMenu.get(i));
	        }
	    }
	    
	    System.out.println(menuList.toString());
	    
	    
	    // 为一级菜单设置子菜单，getChild是递归调用的
	    for (MenuTree menu : rootMenu) {
	    
	        menu.setChildMenus(TreeUtils.getChild(""+menu.getId(), rootMenu));
	    }

		return gson.toJson(menuList);
	}
	
	
	@RequestMapping("getCscMenuTree")
	@ResponseBody
	public String getCscMenuTree(Integer id,HttpServletRequest request) throws Exception {
		//List<MenuTree> rootMenu=rightTreeDao.selectSjTreeList();
		
		List<CscMenu> menuList=cscMenuDao.selectByUserId(id);
		
		/*List<MenuTree> menuList = new ArrayList<MenuTree>();
	    // 先找到所有的一级菜单
	    for (int i = 0; i < rootMenu.size(); i++) {
	        // 一级菜单没有parentId
	        if (rootMenu.get(i).getPid()==0) {
	            menuList.add(rootMenu.get(i));
	        }
	    }
	    
	    System.out.println(menuList.toString());
	    
	    
	    // 为一级菜单设置子菜单，getChild是递归调用的
	    for (MenuTree menu : rootMenu) {
	    
	        menu.setChildMenus(TreeUtils.getChild(""+menu.getId(), rootMenu));
	    }*/

		return gson.toJson(menuList);
	}
	
	
	
	

	@RequestMapping("userList")
	@ResponseBody
	public String getUserList(HttpServletRequest request) {
		Map m = new HashMap();
		m.put("page", Integer.parseInt(request.getParameter("page")) * 10);

		List<User> list = userDao.selectAll(m);
		String count = userDao.selectCount(m);
		Map ret = new HashMap();
		ret.put("count", count);
		ret.put("content", list);
		return gson.toJson(ret);
	}
	
	
	
	@RequestMapping("cscUserList")
	@ResponseBody
	public String getCscUserList(HttpServletRequest request) {
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("page", Integer.parseInt(request.getParameter("page")) * 10);
		List<Map> list = userDao.selectUnionCscAll(m);
		String count = userDao.selectCount(m);
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("count", count);
		ret.put("content", list);
		return gson.toJson(ret);
	}
	

	@RequestMapping("getprovinceList")
	public void getProvinceList_back(HttpServletResponse response, HttpServletRequest request) {
		List<TProvince> list = tProvinceDao.selectAll();
		if (list != null && list.size() > 0) {
			try {
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().write(gson.toJson(list));
				response.getWriter().flush();
				response.getWriter().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@RequestMapping("getcityList")
	public void getCityList_back(HttpServletResponse response, TCity city, HttpServletRequest request) {
		Map m = new HashMap();
		if (request.getParameter("proid") != null && !request.getParameter("proid").equals("")) {
			m.put("proid", request.getParameter("proid"));
		}
		List<TCity> list = tCityDao.selectByProvince(m);
		if (list != null && list.size() > 0) {
			try {
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().write(gson.toJson(list));
				response.getWriter().flush();
				response.getWriter().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@RequestMapping("provinceList")
	@ResponseBody
	public String getProvinceList(HttpServletRequest request) {

		List<TProvince> list = tProvinceDao.selectAll();
		return gson.toJson(list);
	}

	@RequestMapping("cityList")
	@ResponseBody
	public String getCityList(TCity city, HttpServletRequest request) {
		Map m = new HashMap();
		if (request.getParameter("proid") != null && !request.getParameter("proid").equals("")) {
			m.put("proid", request.getParameter("proid"));
		}
		List<TCity> list = tCityDao.selectByProvince(m);
		return gson.toJson(list);
	}

	@RequestMapping("getList")
	@ResponseBody
	public String getList(HttpServletRequest request, User user) {
		Map m = new HashMap();
		StringBuilder pSql = new StringBuilder(); 
		if (!StringUtils.isEmpty(user.getName())) {
			pSql.append(" and (name like '%" + user.getName() + "%' or realname  like '%" + user.getName()
					+ "%' or phone  like '%" + user.getName() + "%' or company  like '%" + user.getName()
					+ "%' or email  like '%" + user.getName() + "%' )");
		}
		if (!StringUtils.isEmpty(user.getSource() )) {
			pSql.append(" and source =" + user.getSource());
		}
		if (!user.getProvince().equals("0")) {
			pSql.append(" and province =" + user.getProvince());
		}
		if (!StringUtils.isEmpty(user.getCity())) {
			pSql.append(" and city =" + user.getCity());
		}
		if (!user.getProfession().equals("0")) {
			pSql.append(" and profession =" + user.getProfession());
		}
		if (!StringUtils.isEmpty(user.getStartDate())) {
			pSql.append(" and create_date>='" +user.getStartDate()+ "' ");
		}
		if (!StringUtil.isEmpty(user.getEndDate())) {
			pSql.append(" and  create_date <='" + user.getEndDate()+ "' ");
		}  
		String count = userDao.total(pSql.toString());
		pSql.append(" order by a.create_date desc limit "+ Integer.parseInt(request.getParameter("page")) * 10+",10 " );
		List<User> list = userDao.getList(pSql.toString()); 
		Map ret = new HashMap();
		ret.put("count", count);
		ret.put("content", list);
		return gson.toJson(ret);
	}

	@RequestMapping("countryList")
	@ResponseBody
	public String getCountryList(HttpServletRequest request) {
		List<TCountry> list = tCountryDao.selectAll();
		return gson.toJson(list);
	}

	@RequestMapping("retPassword")
	@ResponseBody
	public String retPassword(User user, HttpServletRequest request) throws RemoteException {
		Map ret = new HashMap();
		String phone = user.getPhone();
		Info n = Memory.CodeMap.get(phone);
		if (n == null) {
			ret.put("status", "0");
			ret.put("msg", "请获取验证码！");
			return gson.toJson(ret);
		}
		if (!n.getCode().equals(request.getParameter("code"))) {
			ret.put("status", "0");
			ret.put("msg", "验证码错误！");
			return gson.toJson(ret);
		}
		// String password=user.getPassword();
		int i = 0;
		// user.setPassword(MD5Utils.MD5(password+"_shujin"));
		i = userDao.upPassword(user);
		if (i > 0) {
			// SingletonClient.getClient().sendSMS(new String[] { phone },
			// "【数金】您的新密码为："+password, "",5);
			ret.put("status", "1");
			ret.put("msg", "  重置成功！");
			return gson.toJson(ret);
		}
		ret.put("status", "0");
		ret.put("msg", "该用户不存在！");
		return gson.toJson(ret);
	}

	@RequestMapping("userReg")
	@ResponseBody
	public String userReg(User user, HttpServletRequest request) {
		Map ret = new HashMap();
		User temp = userDao.getUser(user);
		if (temp != null) {
			ret.put("status", "0");
			ret.put("appstatus", "2");
			ret.put("msg", "该手机号已经注册！");
			return gson.toJson(ret);
		}
		Info n = Memory.CodeMap.get(user.getPhone());
		if (n == null) {
			ret.put("status", "0");
			ret.put("appstatus", "3");
			ret.put("msg", "请获取验证码！");
			return gson.toJson(ret);
		}
		if (!n.getCode().equals(request.getParameter("code"))) {
			ret.put("status", "0");
			ret.put("appstatus", "4");
			ret.put("msg", "验证码错误！");
			return gson.toJson(ret);
		}
		int i = 0;
		i = userDao.insertSelective(user);
		if (i > 0) {
			ret.put("status", "1");
			ret.put("appstatus", "1");
			ret.put("msg", "注册成功！");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userid", user.getId());
			map.put("amount","+" +  10);
			map.put("remark", "");
			map.put("type", "注册奖励");
			if(StringUtils.isEmpty(virtualcoinMapper.isExist(user.getId().toString()))){
				int rs0 = virtualcoinMapper.saveCoins(user.getId().toString(), 10);
			} 
			int rs = consumeritemsMapper.saveCoinsItem(map);
			
			
			if(request.getParameter("source")!=null && request.getParameter("source").equals("2"))
			{
				String mac=request.getParameter("mac");
				AppUser appUser=new AppUser();
				appUser.setMac(mac);
				appUser.setStatus(1);
				appUser.setUserId(user.getId());
				int q=appUserDao.updateByMacSelective(appUser);
			}
			
		} else {
			ret.put("status", "0");
			ret.put("appstatus", "0");
			ret.put("msg", "系统繁忙！");
		}
		return gson.toJson(ret);
	}

	@RequestMapping("saveAdminUser")
	@ResponseBody
	public String saveAdminUser(User user, HttpServletRequest request) throws UnsupportedEncodingException {
		int i = 0;
		Map ret = new HashMap();
		if (user.getPhone() != null && !user.getPhone().equals("")) {
			String count = "0";
			count = userDao.selectByPhone(user);
			if (!count.equals("0")) {
				ret.put("ret", "该手机号已被使用！");
				return gson.toJson(ret);
			}
		}
		if (user.getId() == null || user.getId().equals("")) {
			user.setPassword(MD5Utils.MD5(user.getPassword() + "_shujin"));
			i = userDao.insertSelective(user);
		} else {

			i = userDao.updateByPrimaryKeySelective(user);
		}

		if (i > 0) {
			ret.put("ret", "OK");
		}
		return gson.toJson(ret);
	}

	@RequestMapping("saveUser_back")
	public void saveUser_back(HttpServletResponse response, User user, HttpServletRequest request)
			throws UnsupportedEncodingException {
		int res = 0;
		String m="";
		String respStr = "";
		String count = "0";
		if(user.getPassword()!=null)
		 {
		 user.setPassword(MD5Utils.MD5(user.getPassword()+"_shujin"));
		 }
		if (user.getId() == null || user.getId().equals("")) {
			count = userDao.selectByName(user);
			if (!count.equals("0")) {
				respStr = "{\"flag\":\"false\",\"msg\":\"用户名已存在！\"}";
			} else {
				res = userDao.insertSelective(user);
				if (res > 0) {
					respStr = "{\"flag\":\"true\",\"msg\":\"保存成功！\"}";
				}
				m="创建user";
			}
		} else {
			res = userDao.updateByPrimaryKeySelective(user);
			if (res > 0) {
				respStr = "{\"flag\":\"true\",\"msg\":\"修改成功！\"}";
			}
			m="修改user";
		}
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(respStr);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		LogModel record=LogUtil.recordLog(request, Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName(), m); 
		notice.logRecord(record);
	}

	@RequestMapping("saveUser")
	@ResponseBody
	public String saveUser(User user, HttpServletRequest request) throws UnsupportedEncodingException {
		int i = 0;
		Map ret = new HashMap();
		// System.out.println(new
		// String(user.getCompany().getBytes("ISO-8859-1"), "UTF-8"));
//		 if(user.getPassword()!=null)
//		 {
//		 user.setPassword(MD5Utils.MD5(user.getPassword()));
//		 }
		if (user.getPhone() != null && !user.getPhone().equals("")) {
			String count = "0";
			count = userDao.selectByPhone(user);
			if (!count.equals("0")) {
				ret.put("status", "0");
				ret.put("msg", "手机号重复");
				return gson.toJson(ret);
			}
		}
		if (user.getId() == null || user.getId().equals("")) {
			user.setName(user.getPhone());
			
			i = userDao.insertSelective(user);
		} else {

			i = userDao.updateByPrimaryKeySelective(user);
		}

		if (i > 0) {
			ret.put("status", "1");
		}
		return gson.toJson(ret);
	}

	@RequestMapping("saveAccount")
	@ResponseBody
	public String saveAccount(User user, HttpServletRequest request) {
		Map ret = new HashMap();
		if (request.getSession().getAttribute("admin") == null) {
			ret.put("ret", "重新登陆！");
			return gson.toJson(ret);
		}
		int i = userDao.updateAccount(user);
		AccountChange accountChange = new AccountChange();
		accountChange.setAdminId(((Admin) request.getSession().getAttribute("admin")).getId());
		accountChange.setAmount(user.getBalance());
		accountChange.setUserId(user.getId());
		accountChangeDao.insertSelective(accountChange);
		if (i > 0) {
			ret.put("ret", "OK");
		} else {
			ret.put("ret", "余额不能减少至负数！");
		}
		return gson.toJson(ret);
	}

	@RequestMapping("saveUserRight")
	@ResponseBody
	public String saveUserRight(HttpServletRequest request) throws ParseException {
		Map ret = new HashMap();
		if (request.getSession().getAttribute("admin") == null) {
			ret.put("ret", "重新登陆！");
			return gson.toJson(ret);
		}
		ret = userService.save(request);

		return gson.toJson(ret);
	}

	@RequestMapping("userRight")
	@ResponseBody
	public String getUserRight(HttpServletRequest request) {
		String flag = request.getParameter("flag");
		List<Map> ret = null;
		if (flag.equals("false")) {
			ret = roleRightDao.selectAllByRoleId(Integer.parseInt(request.getParameter("roleId")));
		} else {
			ret = userRightRoleDao.selectAll(Integer.parseInt(request.getParameter("userId")));
		}
		return gson.toJson(ret);
	}

	@RequestMapping("deleUser")
	@ResponseBody
	public String deleUser(User user, HttpServletRequest request) {
		int i = userDao.deleteByPrimaryKey(user.getId());
		LogModel record=LogUtil.recordLog(request, Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName(), "删除user"); 
		notice.logRecord(record);
		return gson.toJson(null);
	}

	@RequestMapping("user")
	@ResponseBody
	public String getUser(User user) {
		user = userDao.selectByPrimaryKey(user.getId());
		return gson.toJson(user);
	}
	
	@RequestMapping("userIsExists")
	@ResponseBody
	public String userIsExists(User user) {
		Map ret = new HashMap();
		User temp = userDao.getUser(user);
		if (temp != null) {
			ret.put("status", "0");
			ret.put("msg", "该手机号已经注册！");
		}else{
			ret.put("status", "1");
			ret.put("msg", "该手机号未注册！");
		}
		return gson.toJson(ret);
	}

	@RequestMapping("getHeadPortrait")
	@ResponseBody
	public String getHeadPortrait(User user) {
		user = userDao.getHeadPortrait(user);
		return gson.toJson(user);
	}

	@RequestMapping("userStatus")
	@ResponseBody
	public String adminStatus(User user, HttpServletRequest request) {
		if(user.getPassword()!=null)
		{
			user.setPassword(MD5Utils.MD5(user.getPassword() + "_shujin"));
		}
		int i = userDao.updateByPrimaryKeySelective(user);
		return gson.toJson(null);
	}

	@RequestMapping("saveUserMsg")
	@ResponseBody
	public String saveUserMsg(UserMsg userMsg, HttpServletRequest request) throws Exception {
		
//		if(IpUtils.checkUserLogin(request.getParameter("userId"),request)==false)
//		{
//			return null;
//		}
		
		
		Map ret = new HashMap();
		UserMsg temp = userMsgDao.selectByUser(userMsg);
		int i = 0;
		if (temp != null) {
			userMsg.setId(temp.getId());
			i = userMsgDao.updateByPrimaryKeySelective(userMsg);
		} else {
			i = userMsgDao.insertSelective(userMsg);
		}
		ret.put("status", i);
		return gson.toJson(ret);
	}

	@RequestMapping("getUserMsg")
	@ResponseBody
	public String getUserMsg(UserMsg userMsg, HttpServletRequest request) throws Exception {
		
//		if(IpUtils.checkUserLogin(request.getParameter("userId"),request)==false)
//		{
//			return null;
//		}
		
		Map ret = new HashMap();
		UserMsg temp = userMsgDao.selectByUser(userMsg);
		if (temp == null) {
			ret.put("status", 0);
			ret.put("msg", null);
		} else {
			ret.put("status", 1);
			ret.put("msg", temp.getMsg());
		}
		return gson.toJson(ret);
	}

	@RequestMapping("saveUserOrder")
	@ResponseBody
	public Object saveUserOrder(UserOrder userOrder, HttpServletRequest request) {
		Object ret = saveUserOrderCommon(userOrder, request);
		return ret;
	}
	
	/**
	 * 手机APP和官网公用方法
	 * @param userOrder
	 * @param request
	 * @return
	 */
	@RequestMapping("saveUserOrderCommon")
	@ResponseBody
	public Object saveUserOrderCommon(UserOrder userOrder, HttpServletRequest request) {
		Map ret = new HashMap();
		String imgCode = request.getParameter("imgCode");
		if(request.getSession().getAttribute("validateCode")==null){
			ret.put("status", 0);
			ret.put("msg", "验证码失效，请重新输入验证码！");
			return JSONObject.fromObject(ret);
		}
		if (!imgCode.equalsIgnoreCase(request.getSession().getAttribute("validateCode").toString())) {
			ret.put("status", 0);
			ret.put("msg", "验证码错误！");
			return JSONObject.fromObject(ret);
		}
		userOrder.setId("sj" + SJ_TerminalAction.getOrderCode()
				+ SJ_TerminalAction.getRandomNum(4, SJ_TerminalAction.N36_CHARS));
		int i = userOrderDao.insertSelective(userOrder);
		
		if(StringUtils.isEmpty(userOrder.getTitle())){
			userOrder.setTitle("其它");
		}
		
		String id=	userOrderDao.isExist(userOrder.getTitle());
		
		if(StringUtils.isEmpty(id)){
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("name",  userOrder.getTitle());  
			userOrderDao.insertOrderTimes(map); 
		}else{
			userOrderDao.updateOrderTimes(id);
		}
		userOrderDao.updateOrderTotTimes();
		
		if (i > 0) {
			SJ_TerminalAction.sendSMS(userOrder);
			ret.put("status", 1);
			ret.put("msg", "我们已收到您的需求，会和您及时联系！");
		} else {
			ret.put("status", 0);
			ret.put("msg", "系统错误！");
		}
		return JSONObject.fromObject(ret);
	}

	@RequestMapping("saveUserOrderMobile")
	@ResponseBody
	public Object saveUserOrderMobile(UserOrder userOrder, HttpServletRequest request) {
		userOrder.setFromwhere("数享APP");
		Object ret = saveUserOrderCommon(userOrder, request);
		return ret;
	}

	@RequestMapping("getUserOrder")
	@ResponseBody
	public Object getUserOrder(HttpServletRequest request) {
		List<UserOrder> datas = userOrderDao.getDatasByUserId(StringUtil.getUserId(request));
		return JSONArray.fromObject(datas);
	}
	
	@RequestMapping("getUserOrderById")
	@ResponseBody
	public UserOrder getUserOrderById(String id) {
		UserOrder datas = userOrderDao.getOrderById(id);
		return datas;
	}

	@RequestMapping("chkUserRight")
	@ResponseBody
	public Object chkUserRight(HttpServletRequest request) {
		Map ret = new HashMap();
		Map param = new HashMap();
		param.put("userId", request.getParameter("userId"));
		param.put("code", request.getParameter("code"));
		String total = userRightRoleDao.selectRoleByUser(param);
		if (Integer.parseInt(total) > 0) {
			ret.put("status", 1);
			ret.put("msg", "");
		} else {
			ret.put("status", 0);
			ret.put("msg", "您没有此功能权限或该功能权限已过期！");
			ret.put("pid", rightTreeDao.selectByCode(request.getParameter("code")).get("id"));
		}

		return JSONArray.fromObject(ret);
	}

	// 上传头像
	@RequestMapping(value = "/saveHeadPortrait")
	@ResponseBody
	public String saveHeadPortrait(User user, HttpServletRequest request) {
		Map ret = new HashMap();
		User temp = userDao.getUser(user);
		String phone = user.getPhone();
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		String headPortrait = "";

		String imgPath = phone + "/headPortrait/";
		String imgName = "headPortrait" + user.getId();

		MultipartFile headPortraitFile = multiRequest.getFile("headPortraitImg");

		if (headPortraitFile != null && !headPortraitFile.isEmpty()) {
			try {
				if (!UploadImgs.deleteImg(temp.getHeadPortrait())) {
					// //throw new Exception("删除文件失败！");
					ret.put("status", "0");
					ret.put("msg", "系统繁忙！");
					return gson.toJson(ret);
				}
				headPortrait = UploadImgs.uploadImgs(headPortraitFile, imgPath, imgName);
				user.setHeadPortrait(headPortrait);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (!StringUtil.isEmpty(headPortrait)) {
				int i = userDao.updateHeadPortrait(user);
				if (i > 0) {
					ret.put("status", "1");
					ret.put("msg", "  上传头像成功！");
					return gson.toJson(ret);
				}
			}
		}

		ret.put("status", "0");
		ret.put("msg", "系统繁忙！");
		return gson.toJson(ret);
	}
	
	
	@RequestMapping("checkPhone")
	@ResponseBody
	public String checkPhone(User user, String code) throws RemoteException {
		Map ret = new HashMap();
		String phone = user.getPhone();
		Info n = Memory.CodeMap.get(phone);
		if (n == null) {
			ret.put("status", "0");
			ret.put("msg", "请获取验证码！");
			return gson.toJson(ret);
		}
		if (!n.getCode().equals(code)) {
			ret.put("status", "0");
			ret.put("msg", "验证码错误！");
			return gson.toJson(ret);
		}

		User temp = userDao.getUser(user);
		if(temp==null)
		{
			ret.put("status", "0");
			ret.put("msg", "没有此号码！");
			return gson.toJson(ret);
		}
		ret.put("status", "1");
		ret.put("msg", "  OK！");
		return gson.toJson(ret);
		
	}

	/**
	 * 修改手机号
	 * 
	 * @param user
	 * @param code
	 * @return
	 * @throws RemoteException
	 */
	@RequestMapping("retPhone")
	@ResponseBody
	public String retPhone(User user, String code) throws RemoteException {
		Map ret = new HashMap();
		String phone = user.getPhone();
		Info n = Memory.CodeMap.get(phone);
		if (n == null) {
			ret.put("status", "0");
			ret.put("msg", "请获取验证码！");
			return gson.toJson(ret);
		}
		if (!n.getCode().equals(code)) {
			ret.put("status", "0");
			ret.put("msg", "验证码错误！");
			return gson.toJson(ret);
		}

		int i = 0;
		user.setName(user.getPhone());
		try
		{
			i = userDao.updatePhone(user);
			if (i > 0) {
				ret.put("status", "1");
				ret.put("msg", "  修改手机号成功！");
				return gson.toJson(ret);
			}
		}
		catch(Exception e)
		{
			ret.put("status", "0");
			ret.put("msg", "此手机号已经注册！");
			return gson.toJson(ret);
		}
		ret.put("status", "0");
		ret.put("msg", "系统繁忙！");
		return gson.toJson(ret);
	}

	/**
	 * 修改邮箱
	 * 
	 * @param user
	 * @param code
	 * @return
	 * @throws RemoteException
	 */
	@RequestMapping("retEmail")
	@ResponseBody
	public String retEmail(User user, String code) throws RemoteException {
		Map ret = new HashMap();
		String email = user.getEmail();
		Info n = Memory.CodeMap.get(email);
		if (n == null) {
			ret.put("status", "0");
			ret.put("msg", "请获取验证码！");
			return gson.toJson(ret);
		}
		if (!n.getCode().equals(code)) {
			ret.put("status", "0");
			ret.put("msg", "验证码错误！");
			return gson.toJson(ret);
		}

		int i = 0;
		i = userDao.updateEmail(user);
		if (i > 0) {
			ret.put("status", "1");
			ret.put("msg", "  修改邮箱成功！");
			return gson.toJson(ret);
		}
		ret.put("status", "0");
		ret.put("msg", "系统繁忙！");
		return gson.toJson(ret);
	}

	/**
	 * 用户意见反馈
	 * 
	 * @param user
	 * @param code
	 * @return
	 * @throws RemoteException
	 */
	@RequestMapping(value = "/feedback", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String feedback(Integer userId, String content, HttpServletRequest request) throws RemoteException {
		Map ret = new HashMap();
		
		int i = 0;
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("content", content);
		if(Tools.JudgeIsMoblie(request)){
			map.put("source", "2");
		}else{
			map.put("source", "1");
		}
		i = userDao.insertFeedback(map);
		if (i > 0) {
			ret.put("status", "1");
			ret.put("msg", "  用户意见反馈成功！");
			return gson.toJson(ret);
		}
		ret.put("status", "0");
		ret.put("msg", "系统繁忙！");
		return gson.toJson(ret);
	}
	@RequestMapping({ "/phoneList" })@ResponseBody
	public String phoneList(String phone) throws RemoteException {
		List<String> list=userDao. phoneList(phone);
		return gson.toJson(list);
	}
	
	/**
	 * 搜索历史记录
	 * 
	 * @param userId
	 * @param type
	 * @param content
	 * @return
	 * @throws RemoteException
	 */
	@RequestMapping(value = "/searchHistory", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String searchHistory(HttpServletRequest request,Integer type) throws RemoteException {
		String retStr = "";
		Map ret = new HashMap();
		Map map = new HashMap();
		String userId=StringUtil.getUserId(request);
		if(StringUtils.isEmpty(userId))
		{
			userId="1";
		}
		map.put("userId", userId);
		map.put("type", type);
		ret = userDao.searchHistory(map);
		if(ret!=null && !ret.isEmpty()){
			retStr = gson.toJson(ret);
		}else{
			retStr = gson.toJson("");
		}
		return retStr;
	}
	/**
	 * 增加历史记录
	 * 
	 * @param userId
	 * @param type
	 * @param content
	 * @return
	 * @throws RemoteException
	 */
	@RequestMapping(value = "/insertHistory", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String insertHistory(HttpServletRequest request,Integer type, String content) throws RemoteException {
		Map ret = new HashMap();
		
		int i = 0;
		Map map = new HashMap();
		String userId=StringUtil.getUserId(request);
		if(StringUtils.isEmpty(userId))
		{
			userId="1";
		}
		map.put("userId", userId);
		map.put("type", type);
		map.put("content", content);
		Map retMap = userDao.searchHistory(map);
		if(retMap!=null && !retMap.isEmpty()){
			i = userDao.updateHistory(map);
		}else{
			i = userDao.insertHistory(map);
		}
		
		if (i > 0) {
			ret.put("status", "1");
			ret.put("msg", "成功！");
			return gson.toJson(ret);
		}
		ret.put("status", "0");
		ret.put("msg", "系统繁忙！");
		return gson.toJson(ret);
	}

	/**
	 * 保存入会人员
	 * @param response
	 * @param user
	 * @param request
	 */
	@RequestMapping("saveJoinMeet")
	@ResponseBody
	public String saveJoinMeet(JoinMeet joinMeet, HttpServletRequest request){
		Map m=new HashMap();
		m.put("name",request.getParameter("name"));
		m.put("phone",request.getParameter("phone"));
		m.put("end_date",request.getParameter("end_date"));
		m.put("type",request.getParameter("type"));
		joinMeetMapper.saveJoinMeet(m);
		Map ret=new HashMap();
		ret.put("ret","OK");
		return gson.toJson(ret);
	}
	
	/**
	 * 访问间列表
	 * @param response
	 * @param user
	 * @param request
	 */
	@RequestMapping("visitRoomList")
	@ResponseBody
	public Object visitRoomList(HttpServletRequest request){
		Map m=new HashMap();
		if(request.getParameter("page")!=null)
		{
			m.put("page",Integer.parseInt(request.getParameter("page"))*Integer.parseInt(request.getParameter("count")));
			m.put("count",request.getParameter("count"));
		}
		m.put("id",request.getParameter("id"));
		List<Map<String,String>> ret = joinMeetMapper.visitRoomList(m);
		return gson.toJson(ret);
	}
	
	
	/**
	 * 入会人数
	 * @param response
	 * @param user
	 * @param request
	 */
	@RequestMapping("visitRoomNum")
	@ResponseBody
	public Object visitRoomNum(HttpServletRequest request){
		List<Map<String,String>> ret = joinMeetMapper.visitRoomNum();
		return gson.toJson(ret);
	}
	
	
	public static void main(String[] args) {
//		Random random = new Random();
//      int s = random.nextInt(9)%(9-5+1) + 5;
//		System.out.println(s);
//		getJoinRoomNum();
	}
	
	/**
	 * 定时任务 每日00:00:00执行
	 * @param response
	 * @param user
	 * @param request
	 */
	@RequestMapping("getJoinRoomNum")
	@ResponseBody
	public static void getJoinRoomNum() {  
        Calendar calendar = Calendar.getInstance();  
        calendar.set(Calendar.HOUR_OF_DAY, 0); // 控制时  
        calendar.set(Calendar.MINUTE, 0);       // 控制分  
        calendar.set(Calendar.SECOND, 0);       // 控制秒  
  
        Date time = calendar.getTime();         // 得出执行任务的时间,此处为今天的12：00：00  
  
        Timer timer = new Timer();  
        timer.scheduleAtFixedRate(new TimerTask() {  
        	int startNum = 237;
    		int totleNum = 0 ;
    		String isFrist ="yes";
            public void run() {  
            	Random random = new Random();
                int addNum = random.nextInt(9)%(9-5+1) + 5;
                if(isFrist.equals("yes")){
                	totleNum=totleNum+addNum+237;
                	System.out.println(totleNum);
                	isFrist="no";
                }else{
                	totleNum=totleNum+addNum;
                	
                	System.out.println(totleNum);
                }
            }  
        },  time, 1000 * 60 * 60 * 24);// 这里设定将延时每天固定执行  
    } 
}
