package com.dfans.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
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

import com.dfans.dao.AliOrderMapper;
import com.dfans.dao.SJConsumerDataMapper;
import com.dfans.dao.SJPagetotalMapper;
import com.dfans.dao.UserOrderMapper;
import com.dfans.model.AliOrder;
import com.dfans.model.SJPagetotal;
import com.dfans.model.User;
import com.dfans.model.UserOederData;
import com.dfans.utils.JSonUtil;
import com.dfans.utils.ParseNginxLog;

@Controller
@RequestMapping(value = "/statistics")
public class SJ_Statistics {
	@Autowired
	private AliOrderMapper aliOrderMapper;
	@Autowired
	private UserOrderMapper userOrderMapper;
	@Autowired
	private SJConsumerDataMapper userMapper;
	@Autowired
	private SJPagetotalMapper pagetotalMapper;

	@RequestMapping({ "/getDatas" })
	public void getDatas(HttpServletResponse response, HttpServletRequest req, String type, String treeId) {
		String sql = "";
		if ("n".equals(type)) {
			sql = "'%Y'";
		} else if ("y".equals(type)) {
			sql = "'%Y-%m'";
		} else if ("r".equals(type)) {
			sql = "'%Y-%m-%d'";
		}
		if ("all".equals(treeId)) {
			treeId = "";
		} else {
			treeId = " WHERE tree_id= " + treeId;
		}
		List<AliOrder> list = aliOrderMapper.getData(treeId, sql);
		StringBuilder prices = new StringBuilder("[");
		StringBuilder dates = new StringBuilder("[");
		StringBuilder percents = new StringBuilder("[");
		int num = list.size();
		for (int i = 0; i < num; i++) {
			if (i != 0) {
				prices.append("," + list.get(i).getPrice());
				dates.append("," + list.get(i).getDate());
				if (num >= 2 && i <= (num - 2)) {
					if (!list.get(i).getPrice().equals("0")) {
						percents.append("," + formatDouble((Double.parseDouble(list.get(i + 1).getPrice())
								- Double.parseDouble(list.get(i).getPrice()))
								/ Double.parseDouble(list.get(i).getPrice())));
					} else {
						percents.append("," + formatDouble((Double.parseDouble(list.get(i + 1).getPrice()))));
					}
				}
			} else {
				prices.append(list.get(i).getPrice());
				dates.append(list.get(i).getDate());
				if (num >= 2) {
					if (num >= 2 && i <= (num - 2)) {
					percents.append("0," + formatDouble((Double.parseDouble(list.get(i + 1).getPrice())
							- Double.parseDouble(list.get(i).getPrice()))
							/ Double.parseDouble(list.get(i).getPrice())));
					}else {
						percents.append("," + formatDouble((Double.parseDouble(list.get(i + 1).getPrice()))));
					}
				}
			}
		}
		prices.append("]");
		dates.append("]");
		percents.append("]");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("prices", prices.toString());
		map.put("dates", dates.toString());
		map.put("percents", percents);
		JSonUtil.retJosn(response, map);
	}

	@RequestMapping({ "/getPieData" })
	public void getPieData(HttpServletResponse response, HttpServletRequest req, String type) {
		String sql = "";
		if (StringUtils.isEmpty(type)) {
			type = DateUtil.formatDate(new Date(), "yyyy-MM-dd");
		}
		sql = " created_at LIKE '" + type + "%'";
		List<AliOrder> list = aliOrderMapper.getPieData(sql);
		StringBuilder datas = new StringBuilder("[");
		for (int i = 0; i < list.size(); i++) {
			if (i != 0) {
				datas.append(",{\"value\":\"" + list.get(i).getPrice() + "\",\"id\":\"" + list.get(i).getTreeId()
						+ "\",\"name\":\"" + list.get(i).getDate() + "\"}");
			} else {
				datas.append("{\"value\":\"" + list.get(i).getPrice() + "\",\"id\":\"" + list.get(i).getTreeId()
						+ "\",\"name\":\"" + list.get(i).getDate() + "\"}");
			}
		}
		datas.append("]");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("datas", datas);
		JSonUtil.retJosn(response, datas.toString());
	}

	@RequestMapping({ "/order_datas" })
	public void order_datas(HttpServletResponse response, HttpServletRequest req, String type) {
		String format = "";
		String tableName = "";
		String columnName = "";
		StringBuilder percents = new StringBuilder("[");
		if ("n".equals(type)) {
			format = "'%Y'";
			tableName = " year_dic ";
			columnName = "yearlist";
		} else if ("y".equals(type)) {
			format = "'%Y-%m'";
			tableName = " month_dic ";
			columnName = "monthlist";
		} else if ("r".equals(type)) {
			format = "'%Y-%m-%d'";
			tableName = " calendar ";
			columnName = "datelist";
		}
		List<UserOederData> weblist = userOrderMapper.order_datas(format, tableName, " WHERE fromwhere = '官网'",
				columnName);
		List<UserOederData> applist = userOrderMapper.order_datas(format, tableName, " WHERE fromwhere = '数享APP'",
				columnName);
		List<UserOederData> machinelist = userOrderMapper.order_datas(format, tableName, " WHERE fromwhere = '数金一体机' ",
				columnName);
		List<UserOederData> list = userOrderMapper.order_datas(format, tableName, " ", columnName);
		int num = list.size();
		for (int i = 0; i < num; i++) {
			if (i != 0) {
				if (num >= 2 && i <= (num - 2)) {
					if (!list.get(i).getFlag().equals("0")) {
						percents.append("," + formatDouble((Double.parseDouble(list.get(i + 1).getFlag())
								- Double.parseDouble(list.get(i).getFlag()))
								/ Double.parseDouble(list.get(i).getFlag())));
					} else {
						percents.append("," + formatDouble((Double.parseDouble(list.get(i + 1).getFlag()))));
					}
				}
			} else {
				if (num >= 2) {
					if (!list.get(i).getFlag().equals("0")) {
						percents.append("," + formatDouble((Double.parseDouble(list.get(i + 1).getFlag())
								- Double.parseDouble(list.get(i).getFlag()))
								/ Double.parseDouble(list.get(i).getFlag())));
					} else {
						percents.append("," + formatDouble((Double.parseDouble(list.get(i + 1).getFlag()))));
					}
				}
			}
		}
		percents.append("]");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("weblist", weblist);
		map.put("applist", applist);
		map.put("machinelist", machinelist);
		map.put("percents", percents);
		JSonUtil.retJosn(response, map);
	}

	@RequestMapping({ "/user_statist" })
	public void user_statist(HttpServletResponse response, HttpServletRequest req, String type) {
		String format = "";
		String tableName = "";
		String columnName = "";
		StringBuilder percents = new StringBuilder("[");
		if ("n".equals(type)) {
			format = "'%Y'";
			tableName = " year_dic ";
			columnName = "yearlist";
		} else if ("y".equals(type)) {
			format = "'%Y-%m'";
			tableName = " month_dic ";
			columnName = "monthlist";
		} else if ("r".equals(type)) {
			format = "'%Y-%m-%d'";
			tableName = " calendar ";
			columnName = "datelist";
		}
		List<User> weblist = userMapper.user_statist(format, tableName,
				" WHERE source = '0' AND DATE_FORMAT('2017-04-27', " + format + ")<=DATE_FORMAT(create_date, " + format
						+ ")",
				columnName);
		List<User> applist = userMapper.user_statist(format, tableName, " WHERE source = '2'", columnName);
		List<User> machinelist = userMapper.user_statist(format, tableName, " WHERE source = '1' ", columnName);
		List<User> list = userMapper.user_statist(format, tableName,
				" WHERE DATE_FORMAT('2017-04-27', " + format + ")<=DATE_FORMAT(create_date, " + format + ")",
				columnName);
		int num = list.size();
		for (int i = 0; i < num; i++) {
			if (i != 0) {
				if (num >= 2 && i <= (num - 2)) {
					if (!list.get(i).getTitle().equals("0")) {
						percents.append("," + formatDouble((Double.parseDouble(list.get(i + 1).getTitle())
								- Double.parseDouble(list.get(i).getTitle()))
								/ Double.parseDouble(list.get(i).getTitle())));
					} else {
						percents.append("," + formatDouble(Double.parseDouble(list.get(i + 1).getTitle())));
					}
				}
			} else {
				if (num >= 2) {
					if (!list.get(i).getTitle().equals("0")) {
						percents.append("," + formatDouble((Double.parseDouble(list.get(i + 1).getTitle())
								- Double.parseDouble(list.get(i).getTitle()))
								/ Double.parseDouble(list.get(i).getTitle())));
					} else {
						percents.append("," + formatDouble(Double.parseDouble(list.get(i + 1).getTitle())));
					}
				}
			}
		}
		percents.append("]");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("weblist", weblist);
		map.put("applist", applist);
		map.put("machinelist", machinelist);
		map.put("percents", percents);
		JSonUtil.retJosn(response, map);
	}

	@RequestMapping({ "/record" })
	public void savePage(HttpServletResponse response, HttpServletRequest req, SJPagetotal page) {
		pagetotalMapper.save(page);
	}

	@RequestMapping({ "/page_statist" })
	public void page_statist(HttpServletResponse response, HttpServletRequest req, String type) {
		String format = "";
		if ("n".equals(type)) {
			format = "'%Y'";
		} else if ("y".equals(type)) {
			format = "'%Y-%m'";
		} else if ("r".equals(type)) {
			format = "'%Y-%m-%d'";
		}
		List<SJPagetotal> totalList = pagetotalMapper.totalBydate(format);
		List<SJPagetotal> list = pagetotalMapper.avgBydate(format);
		List<SJPagetotal> avgList = new ArrayList<SJPagetotal>();
		StringBuilder totals = new StringBuilder("[");
		int num = totalList.size();
		for (int i = 0; i < num; i++) {
			if (i != 0) {
				totalList.get(i).setCount(String.valueOf(Integer.parseInt(totalList.get(i).getCount())
						+ Integer.parseInt(totalList.get(i - 1).getCount())));
				if (num >= 2 && i <= (num - 2)) {
					if (!totalList.get(i).getCount().equals("0")) {
						totals.append("," + formatDouble((Double.parseDouble(totalList.get(i + 1).getCount()))
								/ Double.parseDouble(totalList.get(i).getCount())));
					} else {
						totals.append("," + formatDouble(Double.parseDouble(totalList.get(i + 1).getCount())));
					}
				}

			} else {
				if (num >= 2) {
					if (!totalList.get(i).getCount().equals("0")) {
						totals.append("," + formatDouble((Double.parseDouble(totalList.get(i + 1).getCount()))
								/ Double.parseDouble(totalList.get(i).getCount())));
					} else {
						totals.append("," + formatDouble(Double.parseDouble(totalList.get(i + 1).getCount())));
					}
				}
			}
			SJPagetotal page = new SJPagetotal();
			page.setCount(formatDouble(
					Double.parseDouble(totalList.get(i).getCount()) / Double.parseDouble(list.get(0).getCount()))
							.toString());
			avgList.add(page);
		}

		totals.append("]");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("totalList", totalList);
		map.put("avgList", avgList);
		map.put("totals", totals);
		JSonUtil.retJosn(response, map);
	}
	/**
	 * 产品下载
	 * @param response
	 * @param req
	 * @param type
	 */
	@RequestMapping({ "/downlaod_statist" })
	public void downlaod_statist(HttpServletResponse response, HttpServletRequest req, String type) {
		String format = "";
		String tableName = "";
		String columnName = "";
		if ("n".equals(type)) {
			format = "'%Y'";
			tableName = " year_dic ";
			columnName = "yearlist";
		} else if ("y".equals(type)) {
			format = "'%Y-%m'";
			tableName = " month_dic ";
			columnName = "monthlist";
		} else if ("r".equals(type)) {
			format = "'%Y-%m-%d'";
			tableName = " calendar ";
			columnName = "datelist";
		}
		List<SJPagetotal> pclist = pagetotalMapper.downlaod_list(format, "pc", tableName, columnName);
		List<SJPagetotal> applist = pagetotalMapper.downlaod_list(format, "app", tableName, columnName);
		StringBuilder pcpercents = new StringBuilder("[");
		StringBuilder apppercents = new StringBuilder("[");
		int num = pclist.size();
		for (int i = 0; i < num; i++) {
			if (i != 0) {
				if (num >= 2 && i <= (num - 2)) {
					if (!pclist.get(i).getCount().equals("0")) {
						pcpercents.append("," + formatDouble((Double.parseDouble(pclist.get(i + 1).getCount())
								- Double.parseDouble(pclist.get(i).getCount()))
								/ Double.parseDouble(pclist.get(i).getCount())));
					} else {
						pcpercents.append("," + formatDouble(Double.parseDouble(pclist.get(i + 1).getCount())));
					}
				}
			} else {
				if (num >= 2) {
					if (!pclist.get(i).getCount().equals("0")) {
						pcpercents.append("," + formatDouble((Double.parseDouble(pclist.get(i + 1).getCount())
								- Double.parseDouble(pclist.get(i).getCount()))
								/ Double.parseDouble(pclist.get(i).getCount())));
					} else {
						pcpercents.append("," + formatDouble(Double.parseDouble(pclist.get(i + 1).getCount())));
					}
				}
			}
		}
		for (int i = 0; i < num; i++) {
			if (i != 0) {
				if (num >= 2 && i <= (num - 2)) {
					if (!applist.get(i).getCount().equals("0")) {
						apppercents.append("," + formatDouble((Double.parseDouble(applist.get(i + 1).getCount())
								- Double.parseDouble(applist.get(i).getCount()))
								/ Double.parseDouble(applist.get(i).getCount())));
					} else {
						apppercents.append("," + formatDouble(Double.parseDouble(applist.get(i + 1).getCount())));
					}
				}
			} else {
				if (num >= 2) {
					if (!applist.get(i).getCount().equals("0")) {
						apppercents.append("," + formatDouble((Double.parseDouble(applist.get(i + 1).getCount())
								- Double.parseDouble(applist.get(i).getCount()))
								/ Double.parseDouble(applist.get(i).getCount())));
					} else {
						apppercents.append("," + formatDouble(Double.parseDouble(applist.get(i + 1).getCount())));
					}
				}
			}
		}
		pcpercents.append("]");
		apppercents.append("]");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pclist", pclist);
		map.put("applist", applist);
		map.put("pcpercents", pcpercents);
		map.put("apppercents", apppercents);
		JSonUtil.retJosn(response, map);
	}

	@RequestMapping({ "/data_downlaod_statist" })
	public void data_downlaod_statist(HttpServletResponse response, HttpServletRequest req, String type, String pid) {
		String format = "";
		String tableName = "";
		String columnName = "";
		if ("n".equals(type)) {
			format = "'%Y'";
			tableName = " year_dic ";
			columnName = "yearlist";
		} else if ("y".equals(type)) {
			format = "'%Y-%m'";
			tableName = " month_dic ";
			columnName = "monthlist";
		} else if ("r".equals(type)) {
			format = "'%Y-%m-%d'";
			tableName = " calendar ";
			columnName = "datelist";
		}
		List<SJPagetotal> pclist = pagetotalMapper.data_downlaod_statist(format, pid, tableName, columnName);
		StringBuilder pcpercents = new StringBuilder("[");
		int num = pclist.size();
		for (int i = 0; i < num; i++) {
			if (i != 0) {
				if (num >= 2 && i <= (num - 2)) {
					if (!pclist.get(i).getCount().equals("0")) {
						pcpercents.append("," + formatDouble((Double.parseDouble(pclist.get(i + 1).getCount())
								- Double.parseDouble(pclist.get(i).getCount()))
								/ Double.parseDouble(pclist.get(i).getCount())));
					} else {
						pcpercents.append("," + formatDouble(Double.parseDouble(pclist.get(i + 1).getCount())));
					}
				}
			} else {
				if (num >= 2) {
					if (!pclist.get(i).getCount().equals("0")) {
						pcpercents.append("," + formatDouble((Double.parseDouble(pclist.get(i + 1).getCount())
								- Double.parseDouble(pclist.get(i).getCount()))
								/ Double.parseDouble(pclist.get(i).getCount())));
					} else {
						pcpercents.append("," + formatDouble(Double.parseDouble(pclist.get(i + 1).getCount())));
					}
				}
			}
		}
		pcpercents.append("]");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pclist", pclist);
		map.put("pcpercents", pcpercents);
		JSonUtil.retJosn(response, map);
	}

	@RequestMapping({ "/pageRecord" })
	public void pageRecord(String userid) {
		SJPagetotal pagetotal = new SJPagetotal();
		pagetotal.setUserid(userid);
		pagetotal.setSource("page");
		pagetotalMapper.save(pagetotal);
	}

	public static Double formatDouble(Double doub) { 
		BigDecimal bg = new BigDecimal(doub).setScale(2, RoundingMode.UP);
		return bg.doubleValue();
	}

}
