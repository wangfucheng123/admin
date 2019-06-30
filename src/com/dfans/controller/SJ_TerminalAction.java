package com.dfans.controller;

import java.io.IOException;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dfans.cache.Memory;
import com.dfans.dao.AliOrderMapper;
import com.dfans.dao.FreeTrialMapper;
import com.dfans.dao.RightTreeMapper;
import com.dfans.dao.SJChannelPriceMapper;
import com.dfans.dao.SJPagetotalMapper;
import com.dfans.dao.SjVirtualcoinMapper;
import com.dfans.dao.UserMapper;
import com.dfans.dao.UserOrderMapper;
import com.dfans.dao.UserRightRoleMapper;
import com.dfans.model.AliOrder;
import com.dfans.model.FreeTrial;
import com.dfans.model.RightTree;
import com.dfans.model.SJChannelPrice;
import com.dfans.model.SJConsumerData;
import com.dfans.model.User;
import com.dfans.model.UserOederData;
import com.dfans.model.UserOrder;
import com.dfans.model.UserRightRole;
import com.dfans.service.PayService;
import com.dfans.utils.JSonUtil;
import com.dfans.utils.QRCodeUtil;
import com.dfans.utils.StringUtil;
import com.dfans.utils.shortmsgutils.SingletonClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping(value = "/terminal")
public class SJ_TerminalAction {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RightTreeMapper rightTreeMapper;
	@Autowired
	private UserOrderMapper userOrderMapper;
	@Autowired
	private UserRightRoleMapper userRightRoleMapper;
	@Autowired
	private SJChannelPriceMapper channelPriceMapper;
	@Autowired
	private FreeTrialMapper freeTrialMapper;
	@Autowired
	private SJPagetotalMapper pagetotalMapper;
	private static Gson gson = new GsonBuilder().create();
	@Autowired
	private SjVirtualcoinMapper virtualcoinMapper;
	@Autowired
	private AliOrderMapper aliOrderMapper;

	@Resource(name = "payService")
	private PayService payService;

	@RequestMapping({ "/openAuthority" })
	public String openAuthority(HttpServletRequest req, Model model, String tId) {
		User user = userMapper.selectByPrimaryKey(Integer.parseInt(StringUtil.getUserId(req)));
		model.addAttribute("phone", user.getName());
		model.addAttribute("userid", user.getId());
		RightTree rightTree = rightTreeMapper.selectByPrimaryKey(Integer.parseInt(tId));
		List<SJChannelPrice> prices = channelPriceMapper.selectByChannelId(tId);
		rightTree.setPrices(prices);
		model.addAttribute("rightTree", rightTree);
		return "ktsjqx";
	}

	/**
	 * 产品列表页
	 * 
	 * @param req
	 * @param pid
	 * @param model
	 * @return
	 */
	@RequestMapping({ "/rightTrees" })
	public String rightTrees(HttpServletRequest req, String pid, Model model) {
		List<RightTree> rightTrees = rightTreeMapper.sj_rightTrees(StringUtil.getUserId(req));
		if (rightTrees != null && rightTrees.size() > 0) {
			SimpleDateFormat sdf = new SimpleDateFormat(NYR_SFM);
			for (RightTree tree : rightTrees) {
				if (tree.getContent() != null) {
					tree.setContent(tree.getContent().replaceAll("<p>", "").replaceAll("</p>", ""));
				}
				if (tree.getEdate() != null) {
					Date edate;
					try {
						edate = sdf.parse(tree.getEdate());
						if (edate.getTime() > (new Date()).getTime()) {
							tree.setIsTrail("0");// 试用中
						} else {

						}
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					tree.setIsTrail("2");
				}
			}
		}
		model.addAttribute("pid", pid);
		model.addAttribute("rightTrees", rightTrees);
		model.addAttribute("userid", StringUtil.getUserId(req));
		return "sj_store";
	}

	@RequestMapping({ "/customizationChannels" })
	public String customizationChannels(HttpServletRequest req, Model model) {
		List<UserOederData> channels = userOrderMapper.selectorderchannel(StringUtil.getUserId(req));
		User user = userMapper.selectByPrimaryKey(Integer.parseInt(StringUtil.getUserId(req)));
		Date date = new Date();
		if (channels != null && channels.size() > 0) {
			for (UserOederData data : channels) {
				if (date.getTime() > data.getEndDate().getTime()) {
					data.setFlag("0");
				}
			}
		}
		model.addAttribute("channels", channels);
		model.addAttribute("userid", user.getId());
		return "customization_channel";
	}

	/**
	 * 
	 * @param req
	 * @param price
	 *            价格id
	 * @param pid
	 * @param model
	 * @param flag
	 * @return
	 */
	@RequestMapping({ "/topaypage" })
	public String topaypage(HttpServletRequest req, String price, String pid, Model model, String flag) {
		String orderId;
		SJChannelPrice channelPrice = channelPriceMapper.selectByPrimaryKey(Integer.parseInt(price));
		if (channelPrice == null) {
			return "";
		}
		// 用户订单
		if (channelPrice.getChannelId().toLowerCase().contains("sj")) {
			orderId = channelPrice.getChannelId();
		} else {
			// 频道开通支付
			orderId = payService.getOrderIdChannelOrder(channelPrice, req, "WX");
		}
		model.addAttribute("orderId", orderId);
		model.addAttribute("pid", pid);
		model.addAttribute("price", price);
		if (StringUtils.isNotEmpty(orderId)) {
			return "paypage";
		} else {
			return "";
		}
	}

	/**
	 * 变更后
	 * 
	 * @param req
	 * @param price
	 *            价格id
	 * @param pid
	 * @param model
	 * @param flag
	 * @return
	 */
	@RequestMapping({ "/topaypagenew" })
	@ResponseBody
	public String topaypagenew(HttpServletRequest req, String price, String pid, Model model, String flag) {
		String orderId;
		SJChannelPrice channelPrice = channelPriceMapper.selectByPrimaryKey(Integer.parseInt(price));
		// 频道开通支付
		orderId = payService.getOrderIdChannelOrder(channelPrice, req, "WX");
		if (!StringUtils.isEmpty(orderId)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("qrcode", "../pay/getQrcode.do?channelPriceId=" + price + "&orderId=" + orderId);
			map.put("orderId", orderId);
			return gson.toJson(map);
		} else {
			return "";
		}
	}

	@RequestMapping("getOrderStatus")
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

	/**
	 * 数据定制 列表页
	 * 
	 * @param req
	 * @param model
	 * @param userid
	 * @return
	 */
	@RequestMapping({ "/customization_sj" })
	public String customization_sj(HttpServletRequest req, Model model, String userid) {
		List<UserOrder> datas = userOrderMapper.getDatasByUserId(StringUtil.getUserId(req));
		List<SJConsumerData> pageList = null;
		List<SJConsumerData> dataList = null;
		if (null != datas && datas.size() > 0) {
			for (UserOrder order : datas) {
				pageList = pagetotalMapper.getListByType(order.getId(), "page");
				dataList = pagetotalMapper.getListByType(order.getId(), "attc");
				order.setPageList(pageList);
				order.setDataList(dataList);
				List<SJChannelPrice> list = channelPriceMapper.selectByChannelId(order.getId());
				if (list.size() > 0) {
					order.setPriceid(list.get(0).getId());
				}
			}
		}
		model.addAttribute("datas", datas);
		model.addAttribute("userid", StringUtil.getUserId(req));
		return "customization_sj";
	}

	/**
	 * 产品页
	 * 
	 * @param req
	 * @param pid
	 * @param f
	 * @param userid
	 * @param model
	 * @return
	 */
	@RequestMapping({ "/product2" })
	public String product2(HttpServletRequest req, String pid, String f, String userid, Model model) {
		RightTree tree = rightTreeMapper.sj_selectByPrimaryKey(Integer.parseInt(pid), StringUtil.getUserId(req));
		List<SJChannelPrice> prices = channelPriceMapper.selectByChannelId(pid);
		tree.setPrices(prices);
		SimpleDateFormat sdf = new SimpleDateFormat(NYR_SFM);
		if (tree.getEdate() != null) {
			Date edate, startDate;
			try {
				edate = sdf.parse(tree.getEdate());
				startDate = sdf.parse(tree.getStartDate());
				Date date = new Date();
				if (edate.getTime() > date(triNum, Calendar.DATE, startDate).getTime()) {
					model.addAttribute("ft", "xf");
				}
				if (edate.getTime() > date(triNum, Calendar.DATE, startDate).getTime()
						&& edate.getTime() > date.getTime()) {
					model.addAttribute("ed", "ed");
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			model.addAttribute("ft", "ft");
		}
		if (tree.getContent() != null) {
			tree.setContent(tree.getContent().replaceAll("<p>", "").replaceAll("</p>", ""));
		}
		int amount = virtualcoinMapper.findCoins(StringUtil.getUserId(req));
		model.addAttribute("rightTree", tree);
		model.addAttribute("amount", amount);
		model.addAttribute("userid", StringUtil.getUserId(req));
		return "pro_buy2";
	}

	@RequestMapping({ "/channelpage" })
	public String channelpage(HttpServletRequest req) {
		return "channelpage";
	}

	@RequestMapping({ "/submit_customization" })
	public String submit_customization(HttpServletResponse response, HttpServletRequest req, Model mode) {
		return "submit_customization_success";
	}

	@RequestMapping({ "/atquestion" })
	public String ua(Model mode, String pid) {
		mode.addAttribute("pid", pid);
		return "ua";
	}

	/**
	 * 数据定制 此处放弃
	 * 
	 * @param response
	 * @param req
	 * @param model
	 * @param imgCode
	 * @param mode
	 */
	@RequestMapping({ "/submit_customization_data" })
	public void submit_customization_data(HttpServletResponse response, HttpServletRequest req, UserOrder model,
			String title, String imgCode, Model mode) {
		int res = 0;
		model.setUserId(Integer.parseInt(StringUtil.getUserId(req)));
		model.setId("sj" + getOrderCode() + getRandomNum(4, N36_CHARS));
		response.setContentType("text/html;charset=utf-8");
		String respStr = "";
		if (((String) req.getSession().getAttribute("validateCode")).equalsIgnoreCase(imgCode)) {
			if (null != model && model.getName() != null) {
				model.setFromwhere("数金一体机");
				res = userOrderMapper.insert(model);
				String id = userOrderMapper.isExist(title);
				if (StringUtils.isEmpty(id)) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("name", title);
					userOrderMapper.insertOrderTimes(map);
				} else {
					userOrderMapper.updateOrderTimes(id);
					userOrderMapper.updateOrderTotTimes();
				}
			}
			if (res > 0) {
				respStr = "{\"flag\":\"true\"}";
				sendSMS(model);
			}
		} else {
			respStr = "{\"flag\":\"false\"}";
		}
		try {
			response.getWriter().write(respStr);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping({ "/getTimes" })
	@ResponseBody
	public void getTimes(String title, HttpServletResponse response) {
		List<Map<String, String>> map = userOrderMapper.getTimes(title);
		JSonUtil.retJosn(response, map);
	}

	@RequestMapping({ "/getNo" })
	@ResponseBody
	public void getNo(HttpServletResponse response) {
		List<Map<String, String>> map = userOrderMapper.getNo();
		JSonUtil.retJosn(response, map);
	}

	/**
	 * 组件判断是否试用 及是否过期
	 * 
	 * @param req
	 * @param pid
	 * @return
	 */
	@RequestMapping({ "/feature" })
	public String feature(HttpServletRequest req, String pid, Model mode) {
		List<RightTree> rightTrees = rightTreeMapper.sj_rightTrees(StringUtil.getUserId(req));
		if (rightTrees != null && rightTrees.size() > 0) {
			SimpleDateFormat sdf = new SimpleDateFormat(NYR_SFM);
			for (RightTree tree : rightTrees) {
				if (tree.getEdate() != null) {
					Date edate, startDate;
					try {
						edate = sdf.parse(tree.getEdate());
						startDate = sdf.parse(tree.getStartDate());
						Date date = new Date();
						if (edate.getTime() > date(triNum, Calendar.DATE, startDate).getTime()) {
							tree.setIsTrail("1");// 开通、提示续费
							String c = "";
							if (date.getTime() > edate.getTime()) {
								c = OUT_OF_DATE;
							} else {
								c = "(已开通)";
							}
							tree.setContent(DateFormatUtils.format(edate, "yyyy-MM-dd") + c);
						} else {
							String c = "";
							if (date.getTime() > edate.getTime()) {
								c = OUT_OF_DATE;
							} else {
								c = "(试用)";
							}
							tree.setIsTrail("0");// 提示开通
							tree.setContent(DateFormatUtils.format(edate, "yyyy-MM-dd") + c);
						}
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					tree.setIsTrail("2");// 未使用、提示试用
					tree.setContent("-");
				}
			}
		}
		mode.addAttribute("rightTree", rightTrees);
		return "feature";
	}

	/**
	 * shiyong
	 * 
	 * @param pid
	 * @param f
	 * @param mode
	 * @return
	 */
	@RequestMapping({ "/trial" })
	public String trial(String pid, String f, Model mode) {
		RightTree rt = rightTreeMapper.selectByPrimaryKey(Integer.parseInt(pid));
		mode.addAttribute("pid", pid);
		mode.addAttribute("f", f);
		mode.addAttribute("name", rt.getName());
		mode.addAttribute("triNum", triNum);
		return "trial";
	}

	/**
	 * shiyong记录
	 * 
	 * @param req
	 * @param response
	 * @param pid
	 * @param mode
	 * @return
	 */
	@RequestMapping({ "/saveTrailRecord" })
	public String saveTrailRecord(HttpServletRequest req, HttpServletResponse response, String pid, Model mode) {
		FreeTrial trail = freeTrialMapper.selectByUidPid(Integer.parseInt(StringUtil.getUserId(req)),
				Integer.parseInt(pid));
		if (trail == null) {
			trail = new FreeTrial();
			Date date = new Date();
			trail.setEdate(date(triNum, Calendar.DATE, date));
			trail.setSdate(date);
			trail.setTreeid(Integer.parseInt(pid));
			trail.setUserid(Integer.parseInt(StringUtil.getUserId(req)));
			int n = freeTrialMapper.saveTrailRecord(trail);
			/**
			 * 也可不这么做，在中端判断时+查 free_trial表里的
			 */
			UserRightRole record = new UserRightRole();
			record.setOrderId("FREE");
			record.setRightId(pid);
			record.setUserId(Integer.parseInt(StringUtil.getUserId(req)));
			record.setStartDate(date);
			record.setEndDate(date(triNum, Calendar.DATE, date));
			record.setUpdateTime(date);
			int k = userRightRoleMapper.insert(record);
			RightTree rightTree = rightTreeMapper.selectByPrimaryKey(Integer.parseInt(pid));
			mode.addAttribute("tree", rightTree);
			mode.addAttribute("triNum", triNum);
		}
		return "t_suc";
	}

	@RequestMapping({ "/submit_customization_data_redirect" })
	public String submit_customization_data_redirect() {
		return "redirect:/terminal/submit_customization.do#form";
	}

	@RequestMapping({ "/b_success" })
	public synchronized String b_success(HttpServletRequest req, String orderId, Model model) {
		if (orderId != null) {
			AliOrder aliOrder = aliOrderMapper.selectByPrimaryKey(orderId);
			if (aliOrder != null) {
				if (aliOrder.getStatus() == 1) {
					RightTree rightTree = rightTreeMapper.selectByPrimaryKey(Integer.parseInt(aliOrder.getTreeId()));
					model.addAttribute("tree", rightTree);
					User user = userMapper.selectByPrimaryKey(aliOrder.getUserId());
					model.addAttribute("user", user);

				}
			} else {
				// UserOrder userOrder =
				// userOrderMapper.selectByPrimaryKey(orderId);
			}
		}
		String userAgent = req.getHeader("user-agent");
		if (isMobileDevice(userAgent)) {
			return "b_success2";
		}
		return "b_success";
	}

	@RequestMapping(value = "/getQrcode")
	public void getImage(HttpServletRequest request, HttpServletResponse response, String orderId) throws IOException {
		// 禁止图像缓存。
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		try {
			// 将图像输出到Servlet输出流中。
			ServletOutputStream sos = response.getOutputStream();
			ImageIO.write(QRCodeUtil.createImage(orderId), "jpeg", sos);
			sos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Cookie getCookieByName(HttpServletRequest req, String name) {
		Map<String, Cookie> cMap = getCookieMap(req);
		if (cMap.containsKey(name)) {
			Cookie cookie = cMap.get(name);
			return cookie;
		} else {
			return null;
		}
	}

	public static Map<String, Cookie> getCookieMap(HttpServletRequest req) {
		Map<String, Cookie> cMap = new HashMap<String, Cookie>();
		Cookie[] cookies = req.getCookies();
		if (null != cookies) {
			for (Cookie c : cookies) {
				cMap.put(c.getName(), c);
			}
		}
		return cMap;
	}

	public static Date datePlus(int limit, Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, limit);
		return cal.getTime();
	}

	public static Date date(int limit, int type, Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(type, limit);
		return cal.getTime();
	}

	public static String Date2String(String pattern, Date date) {
		DateFormat df = new SimpleDateFormat(pattern);
		return df.format(date);
	}

	public static String date2String(Date date, String pattern) {
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		String reDate = df.format(date);
		return reDate;
	}

	public static Date string2Date(String date, String pattern) {
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		Date reDate = null;
		try {
			reDate = df.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return reDate;
	}

	public static String getRandomNum(int count, char[] chars) {
		return RandomStringUtils.random(count, chars);
	}

	public static final char[] N36_CHARS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
	public static final String OUT_OF_DATE = "(已过期)";
	public static final String ON_TRIAL = "(试用)";
	public static final String NYR = "yyyy-MM-dd";
	public static final int triNum = 30;
	public static final String NYR_SFM = "yyyy-MM-dd HH:mm:ss";
	public static final String NYRSFM = "yyyyMMddHHmmss";

	public static void sendSMS(UserOrder model) {
		try {
			SingletonClient.getClient().sendSMS(new String[] { "15101051469" }, "【数金】"
					+ Date2String(NYR_SFM, new Date()) + "收到" + model.getName() + "，" + model.getPhone() + "提交的在线订单",
					"", 5);
			SingletonClient.getClient().sendSMS(new String[] { model.getPhone() },
					"【数金】" + "我们已经收到您提交的订单，稍后会有人和您联系，您也可以登录数金终端或数享APP查看订单状态", "", 5);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public static String getOrderCode() {
		SimpleDateFormat df = new SimpleDateFormat(NYRSFM);
		return df.format(new Date());
	}

	/**
	 * 是否来自手机端 android ios
	 * 
	 * @param userAgent
	 * @return
	 */
	public static boolean isMobileDevice(String userAgent) {
		String[] deviceArray = { "Android", "iPhone" };
		for (String str : deviceArray) {
			if (userAgent.contains(str)) {
				return true;
			}
		}
		return false;
	}

	@RequestMapping({ "/downData" })
	public String downData(HttpServletResponse response, HttpServletRequest req, Model mode) {
		return "submit_customization_success";
	}

	@RequestMapping({ "/loadPage" })
	public String loadPage(String id) {
		SJConsumerData model = pagetotalMapper.getById(id);
		return model.getUrl();
	}

	public static void main(String[] args) {

		System.out.println(DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
	}
}
