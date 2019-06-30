package com.dfans.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchDataFotmat {
	
	
	/**
	 * 新股申购或新股配售
	 * @param list
	 * @return
	 */
	public static List<Map> XGSGData(List<Map> list) {
		if(list == null || list.size()==0){
			return list;
		}
		Map returnMap = new HashMap();
		List<Map> returnList=new ArrayList<Map>();
		returnMap.put("title", "新股申购");
		List retTemp=new ArrayList();
		for (Map map : list) {
			List<Map> temp=new ArrayList<Map>();
			Map maptemp = new HashMap();
			maptemp.put("name", "证券简称");
			maptemp.put("value", nullFormat(map.get("ob_secname_0007")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "证券代码");
			maptemp.put("value", nullFormat(map.get("ob_seccode_0007")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "申购代码");
			maptemp.put("value", nullFormat(map.get("f038v_0089")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "申购日期");
			maptemp.put("value", nullFormat(map.get("F035D_0089")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "发行价");
			maptemp.put("value", nullFormat(map.get("f008n_0089")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "网上申购上限(万股)");
			maptemp.put("value", nullFormat(map.get("f042n_0089")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "网下配售数量(万股)");
			maptemp.put("value", nullFormat(map.get("f045n_0089")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "中签号公告日");
			maptemp.put("value", nullFormat(map.get("F109D_0089")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "网上有效申购资金");
			maptemp.put("value", nullFormat(map.get("f056n_0089")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "预计募集资金(亿元)");
			maptemp.put("value", nullFormat(map.get("f026n_0089")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "承销方式");
			maptemp.put("value", nullFormat(map.get("f023v_0089")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "发行前每股净资产");
			maptemp.put("value", nullFormat(map.get("f014n_0089")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "发行后每股净资产");
			maptemp.put("value", nullFormat(map.get("f015n_0089")));
			temp.add(maptemp);
			retTemp.add(temp);
		}
		returnMap.put("data", retTemp);
		returnList.add(returnMap);
		return returnList;
	}
	
	/**
	 * 新股过会或新股上会、新股审议
	 * @param list
	 * @return
	 */
	public static List<Map> XGGHData(List<Map> list) {
		if(list == null || list.size()==0){
			return list;
		}
		Map returnMap = new HashMap();
		List<Map> returnList=new ArrayList<Map>();
		returnMap.put("title", "新股过会");
		List retTemp=new ArrayList();
		for (Map map : list) {
			List<Map> temp=new ArrayList<Map>();
			Map maptemp = new HashMap();
			maptemp.put("name", "公司名称");
			maptemp.put("value", nullFormat(map.get("ob_orgname_0809")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "证券代码");
			maptemp.put("value", nullFormat(map.get("ob_seccode_0007")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "上会日期");
			maptemp.put("value", nullFormat(map.get("f003d_0809")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "审议内容");
			maptemp.put("value", nullFormat(map.get("f004v_0809")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "审核结果");
			maptemp.put("value", nullFormat(map.get("f005v_0809")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "审核公告日");
			maptemp.put("value", nullFormat(map.get("f006d_0809")));
			temp.add(maptemp);
			retTemp.add(temp);
		}
		returnMap.put("data", retTemp);
		returnList.add(returnMap);
		return returnList;
	}
	
	/**
	 *新股上市或新股发行
	 * @param list
	 * @return
	 */
	public static List<Map> selectXGSS(List<Map> list) {
		if(list == null || list.size()==0){
			return list;
		}
		Map returnMap = new HashMap();
		List<Map> returnList=new ArrayList<Map>();
		returnMap.put("title", "新股上市");
		List retTemp=new ArrayList();
		for (Map map : list) {
			List<Map> temp=new ArrayList<Map>();
			Map maptemp = new HashMap();
			maptemp.put("name", "证券简称");
			maptemp.put("value", nullFormat(map.get("ob_secname_0007")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "证券代码");
			maptemp.put("value", nullFormat(map.get("ob_seccode_0007")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "上市日期");
			maptemp.put("value", nullFormat(map.get("ob_tradedate_0160")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "发行价格");
			maptemp.put("value", nullFormat(map.get("f008n_0089")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "首日开盘价");
			maptemp.put("value", nullFormat(map.get("f003n_0160")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "首日开盘溢价");
			maptemp.put("value", nullFormat(map.get("firstDayYiJia")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "首日成交金额");
			maptemp.put("value", nullFormat(map.get("f025n_0160")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "首日成交数量");
			maptemp.put("value", nullFormat(map.get("f025n_0160")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "首日涨幅");
			maptemp.put("value", nullFormat(map.get("f015n_0160")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "首日收盘价");
			maptemp.put("value", nullFormat(map.get("f002n_0160")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "至今涨幅");
			maptemp.put("value", nullFormat(map.get("zhiJinZhangFu")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "历史最高价");
			maptemp.put("value", nullFormat(map.get("f003n_0160")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "历史最低价");
			maptemp.put("value", nullFormat(map.get("f003n_0160")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "首日换手率");
			maptemp.put("value", nullFormat(map.get("f017n_0160")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "净资产收益率(%)");
			maptemp.put("value", nullFormat(map.get("f031n_0137")));
			temp.add(maptemp);
//			maptemp = new HashMap();
//			maptemp.put("name", "每股净资产(元)");
//			maptemp.put("value", nullFormat(map.get("f021n_0160")));
//			temp.add(maptemp);
//			maptemp = new HashMap();
//			maptemp.put("name", "每股收益(元)");
//			maptemp.put("value", nullFormat(map.get("f019n_0137")));
//			temp.add(maptemp);
//			maptemp = new HashMap();
//			maptemp.put("name", "每股经营活动产生的现金流量净额(元)");
//			maptemp.put("value", nullFormat(map.get("f037n_0137")));
//			temp.add(maptemp);
//			maptemp = new HashMap();
//			maptemp.put("name", "营业收入");
//			maptemp.put("value", nullFormat(map.get("f080n_0293")));
//			temp.add(maptemp);
			retTemp.add(temp);
		}
		returnMap.put("data", retTemp);
		returnList.add(returnMap);
		return returnList;
	}
	
	/**
	 * 新发或新发基金
	 * @param list
	 * @return
	 */
	public static List<Map> selectXFJJ(List<Map> list) {
		if(list == null || list.size()==0){
			return list;
		}
		Map returnMap = new HashMap();
		List<Map> returnList=new ArrayList<Map>();
		returnMap.put("title", "新发基金");
		List retTemp=new ArrayList();
		for (Map map : list) {
			List<Map> temp=new ArrayList<Map>();
			Map maptemp = new HashMap();
			maptemp.put("name", "基金简称");
			maptemp.put("value", nullFormat(map.get("secu_sht")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "基金代码");
			maptemp.put("value", nullFormat(map.get("trd_code")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "募集期");
			maptemp.put("value", nullFormat(map.get("ISS_BGN_DT"))+"至"+nullFormat(map.get("ISS_END_DT")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "上市日期");
			maptemp.put("value", nullFormat(map.get("LST_DT")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "发行价格(元/份)");
			maptemp.put("value", nullFormat(map.get("ISS_PRC")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "面值（元）");
			maptemp.put("value", nullFormat(map.get("PAR_VAL")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "单位发行费用 ");
			maptemp.put("value", nullFormat(map.get("PAR_FEE")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "最低认购金额(元/笔)");
			maptemp.put("value", nullFormat(map.get("MIN_SUB")));
			temp.add(maptemp);
			retTemp.add(temp);
		}
		returnMap.put("data", retTemp);
		returnList.add(returnMap);
		return returnList;
	}
	
	/**
	 * 开放式或开放式基金
	 * @param list
	 * @return
	 */
	public static List<Map> selectKFSJJ(List<Map> list,String date) {
		if(list == null || list.size()==0){
			return list;
		}
		Map returnMap = new HashMap();
		List<Map> returnList=new ArrayList<Map>();
		returnMap.put("title", "开放式基金");
		returnMap.put("date", date);
		List retTemp=new ArrayList();
		for (Map map : list) {
			List<Map> temp=new ArrayList<Map>();
			Map maptemp = new HashMap();
			maptemp.put("name", "基金简称");
			maptemp.put("value", nullFormat(map.get("secu_sht")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "基金代码");
			maptemp.put("value", nullFormat(map.get("TRD_CODE")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "份额净值");
			maptemp.put("value", nullFormat(map.get("UNIT_NAV")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "日收益率");
			maptemp.put("value", nullFormat(map.get("CHG_RAT_1D")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "月增长率");
			maptemp.put("value", nullFormat(map.get("monthzzl")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "季度增长率");
			maptemp.put("value", nullFormat(map.get("jdzzl")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "基金规模");
			maptemp.put("value", nullFormat(map.get("ISS_VOL")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "本年收益率");
			maptemp.put("value", nullFormat(map.get("chg_rat_ty")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "周收益率");
			maptemp.put("value", nullFormat(map.get("chg_rat_1w")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "月收益率");
			maptemp.put("value", nullFormat(map.get("chg_rat_1m")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "季度收益率");
			maptemp.put("value", nullFormat(map.get("chg_rat_3m")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "近一年收益率");
			maptemp.put("value", nullFormat(map.get("chg_rat_1y")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "累计收益率");
			maptemp.put("value", nullFormat(map.get("chg_rat_bgn")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "基金公司");
			maptemp.put("value", nullFormat(map.get("fmc_com_name")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "基金经理");
			maptemp.put("value", nullFormat(map.get("mng_psn_name")));
			temp.add(maptemp);
//			maptemp = new HashMap();
//			maptemp.put("name", "投资风格");
//			maptemp.put("value", nullFormat(map.get("INV_TYP_DESC")));
//			temp.add(maptemp);
//			maptemp = new HashMap();
//			maptemp.put("name", "风险评级");
//			maptemp.put("value", nullFormat(map.get("STAR_DESC")));
//			temp.add(maptemp);
//			maptemp = new HashMap();
//			maptemp.put("name", "申购状态");
//			maptemp.put("value", nullFormat(map.get("pur_st")));
//			temp.add(maptemp);
//			maptemp = new HashMap();
//			maptemp.put("name", "赎回状态");
//			maptemp.put("value", nullFormat(map.get("redem_st")));
//			temp.add(maptemp);
//			maptemp = new HashMap();
//			maptemp.put("name", "日期");
//			maptemp.put("value", nullFormat(map.get("TRD_DT")));
//			temp.add(maptemp);
			retTemp.add(temp);
		}
		returnMap.put("data", retTemp);
		returnList.add(returnMap);
		return returnList;
	}
	
	/**
	 * 货币型或货币型基金
	 * @param list
	 * @return
	 */
	public static List<Map> selectHBXJJ(List<Map> list,String date) {
		if(list == null || list.size()==0){
			return list;
		}
		Map returnMap = new HashMap();
		List<Map> returnList=new ArrayList<Map>();
		returnMap.put("title", "货币型基金");
		returnMap.put("date", date);
		List retTemp=new ArrayList();
		for (Map map : list) {
			List<Map> temp=new ArrayList<Map>();
			Map maptemp = new HashMap();
			maptemp.put("name", "基金简称");
			maptemp.put("value", nullFormat(map.get("secu_sht")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "基金代码");
			maptemp.put("value", nullFormat(map.get("TRD_CODE")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "份额净值");
			maptemp.put("value", nullFormat(map.get("UNIT_NAV")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "份额累计净值");
			maptemp.put("value", nullFormat(map.get("ACCU_UNIT_NAV")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "万分收益(元)");
			maptemp.put("value", nullFormat(map.get("UNIT_YLD")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "基金规模");
			maptemp.put("value", nullFormat(map.get("ISS_VOL")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "7日年化收益(%)");
			maptemp.put("value", nullFormat(map.get("ANN_YLD_RAT")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "日涨跌");
			maptemp.put("value", nullFormat(map.get("DAY_CHG")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "日涨跌幅(%)");
			maptemp.put("value", nullFormat(map.get("DAY_CHG_RAT")));
			temp.add(maptemp);
//			maptemp = new HashMap();
//			maptemp.put("name", "后复权单位净值");
//			maptemp.put("value", nullFormat(map.get("ADJ_UNIT_NAV")));
//			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "基金公司");
			maptemp.put("value", nullFormat(map.get("fmc_com_name")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "基金经理");
			maptemp.put("value", nullFormat(map.get("mng_psn_name")));
			temp.add(maptemp);
//			maptemp = new HashMap();
//			maptemp.put("name", "日期");
//			maptemp.put("value", nullFormat(map.get("TRD_DT")));
//			temp.add(maptemp);
			retTemp.add(temp);
		}
		returnMap.put("data", retTemp);
		returnList.add(returnMap);
		return returnList;
	}
	
	/**
	 * 分级或分级基金
	 * @param list
	 * @return
	 */
	public static List<Map> selectFJJJ(List<Map> list,String date) {
		if(list == null || list.size()==0){
			return list;
		}
		Map returnMap = new HashMap();
		List<Map> returnList=new ArrayList<Map>();
		returnMap.put("title", "分级基金");
		returnMap.put("date", date);
		List retTemp=new ArrayList();
		for (Map map : list) {
			List<Map> temp=new ArrayList<Map>();
			Map maptemp = new HashMap();
			maptemp.put("name", "基金简称");
			maptemp.put("value", nullFormat(map.get("secu_sht")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "基金代码");
			maptemp.put("value", nullFormat(map.get("TRD_CODE")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "日涨跌幅");
			maptemp.put("value", nullFormat(map.get("day_chg_rat")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "基金规模");
			maptemp.put("value", nullFormat(map.get("ISS_VOL")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "本年收益率");
			maptemp.put("value", nullFormat(map.get("chg_rat_ty")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "周收益率");
			maptemp.put("value", nullFormat(map.get("chg_rat_1w")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "月收益率");
			maptemp.put("value", nullFormat(map.get("chg_rat_1m")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "季度收益率");
			maptemp.put("value", nullFormat(map.get("chg_rat_3m")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "近一年收益率");
			maptemp.put("value", nullFormat(map.get("chg_rat_1y")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "累计收益率");
			maptemp.put("value", nullFormat(map.get("chg_rat_bgn")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "基金公司");
			maptemp.put("value", nullFormat(map.get("fmc_com_name")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "基金经理");
			maptemp.put("value", nullFormat(map.get("mng_psn_name")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "投资风格");
			maptemp.put("value", nullFormat(map.get("INV_TYP_DESC")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "申购状态");
			maptemp.put("value", nullFormat(map.get("pur_st")));
			temp.add(maptemp);
//			maptemp = new HashMap();
//			maptemp.put("name", "赎回状态");
//			maptemp.put("value", nullFormat(map.get("redem_st")));
//			temp.add(maptemp);
//			maptemp = new HashMap();
//			maptemp.put("name", "日期");
//			maptemp.put("value", nullFormat(map.get("TRD_DT")));
//			temp.add(maptemp);
			retTemp.add(temp);
		}
		returnMap.put("data", retTemp);
		returnList.add(returnMap);
		return returnList;
	}
	
	/**
	 * 封闭式或封闭式基金
	 * @param list
	 * @return
	 */
	public static List<Map> selectFBSJJ(List<Map> list,String date) {
		if(list == null || list.size()==0){
			return list;
		}
		Map returnMap = new HashMap();
		List<Map> returnList=new ArrayList<Map>();
		returnMap.put("title", "封闭式基金");
		returnMap.put("date", date);
		List retTemp=new ArrayList();
		for (Map map : list) {
			List<Map> temp=new ArrayList<Map>();
			Map maptemp = new HashMap();
			maptemp.put("name", "基金简称");
			maptemp.put("value", nullFormat(map.get("secu_sht")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "基金代码");
			maptemp.put("value", nullFormat(map.get("TRD_CODE")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "单位净值");
			maptemp.put("value", nullFormat(map.get("UNIT_NAV")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "累计净值");
			maptemp.put("value", nullFormat(map.get("ACCU_UNIT_NAV")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "日涨跌幅");
			maptemp.put("value", nullFormat(map.get("DAY_CHG_RAT")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "基金规模");
			maptemp.put("value", nullFormat(map.get("ISS_VOL")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "基金公司");
			maptemp.put("value", nullFormat(map.get("fmc_com_name")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "基金经理");
			maptemp.put("value", nullFormat(map.get("mng_psn_name")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "投资风格");
			maptemp.put("value", nullFormat(map.get("INV_TYP_DESC")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "申购状态");
			maptemp.put("value", nullFormat(map.get("pur_st")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "赎回状态");
			maptemp.put("value", nullFormat(map.get("redem_st")));
			temp.add(maptemp);
//			maptemp = new HashMap();
//			maptemp.put("name", "日期");
//			maptemp.put("value", nullFormat(map.get("TRD_DT")));
//			temp.add(maptemp);
			retTemp.add(temp);
		}
		returnMap.put("data", retTemp);
		returnList.add(returnMap);
		return returnList;
	}
	
	/**
	 * 行情或基金行情
	 * @param list
	 * @return
	 */
	public static List<Map> selectJJHQ(List<Map> list,String date) {
		if(list == null || list.size()==0){
			return list;
		}
		Map returnMap = new HashMap();
		List<Map> returnList=new ArrayList<Map>();
		returnMap.put("title", "基金行情");
		returnMap.put("date", date);
		List retTemp=new ArrayList();
		for (Map map : list) {
			List<Map> temp=new ArrayList<Map>();
			Map maptemp = new HashMap();
			maptemp.put("name", "基金简称");
			maptemp.put("value", nullFormat(map.get("secu_sht")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "基金代码");
			maptemp.put("value", nullFormat(map.get("TRD_CODE")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "昨收盘");
			maptemp.put("value", nullFormat(map.get("prev_cls_prc")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "今开盘");
			maptemp.put("value", nullFormat(map.get("open_prc")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "最高价");
			maptemp.put("value", nullFormat(map.get("high_prc")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "最低价");
			maptemp.put("value", nullFormat(map.get("low_prc")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "今收盘");
			maptemp.put("value", nullFormat(map.get("cls_prc")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "成交量");
			maptemp.put("value", nullFormat(map.get("tnv_vol")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "成交额");
			maptemp.put("value", nullFormat(map.get("tnv_val")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "日涨跌");
			maptemp.put("value", nullFormat(map.get("day_chg")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "日涨跌幅");
			maptemp.put("value", nullFormat(map.get("day_chg_rat")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "振幅");
			maptemp.put("value", nullFormat(map.get("day_amp")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "份额净值");
			maptemp.put("value", nullFormat(map.get("UNIT_NAV")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "升贴水");
			maptemp.put("value", nullFormat(map.get("PREM_AGIO")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "升贴水率");
			maptemp.put("value", nullFormat(map.get("PREM_AGIO_RAT")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "日换手率");
			maptemp.put("value", nullFormat(map.get("TNV_RAT")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "可交易份额");
			maptemp.put("value", nullFormat(map.get("EXCH_SHR")));
			temp.add(maptemp);
//			maptemp = new HashMap();
//			maptemp.put("name", "后复权收盘价");
//			maptemp.put("value", nullFormat(map.get("ADJ_CLS_PRC")));
//			temp.add(maptemp);
//			maptemp = new HashMap();
//			maptemp.put("name", "日期");
//			maptemp.put("value", nullFormat(map.get("trd_dt")));
//			temp.add(maptemp);
			retTemp.add(temp);
		}
		returnMap.put("data", retTemp);
		returnList.add(returnMap);
		return returnList;
	}
	
	/**
	 * 个股情绪分析
	 * @param list
	 * @return
	 */
	public static List<Map> stockAnalysis(List<Map> list,String bz_sign) {
		if(list == null || list.size()==0){
			return list;
		}
		Map returnMap = new HashMap();
		List<Map> returnList=new ArrayList<Map>();
		returnMap.put("title", "个股情绪分析");
//		String bz_sign = "";
//		
//		String tempWord[]=StockInit.keyword[29].split(",");
//		for(int i=0;i<tempWord.length;i++)
//		{
//			for (int j = 0; j < keywordText.size(); j++) {
//				if(tempWord[i].equals(keywordText.get(j).toString())){
//					bz_sign = tempWord[i];
//					break;
//				}
//			}
//		}
		returnMap.put("bz_sign", bz_sign);
		
		List retTemp=new ArrayList();
		for (Map map : list) {
			List<Map> temp=new ArrayList<Map>();
			Map maptemp = new HashMap();
			maptemp.put("name", "股票名称");
			maptemp.put("value", nullFormat(map.get("name")));
			temp.add(maptemp);
			if("研报数量".equals(bz_sign)){
				maptemp = new HashMap();
				maptemp.put("name", "研报数量");
				maptemp.put("value", nullFormat(map.get("count")));
				temp.add(maptemp);
			}else if("所属行业".equals(bz_sign)){
				maptemp = new HashMap();
				maptemp.put("name", "所属行业");
				maptemp.put("value", nullFormat(map.get("industry")));
				temp.add(maptemp);
			}else if("平均目标价".equals(bz_sign)){
				maptemp = new HashMap();
				maptemp.put("name", "平均目标价");
				maptemp.put("value", nullFormat(map.get("targ_prc")));
				temp.add(maptemp);
			}else if("收盘价".equals(bz_sign)){
				maptemp = new HashMap();
				maptemp.put("name", "收盘价");
				maptemp.put("value", nullFormat(map.get("cls_prc")));
				temp.add(maptemp);
			}else if("实际涨幅".equals(bz_sign)){
				maptemp = new HashMap();
				maptemp.put("name", "实际涨幅");
				maptemp.put("value", nullFormat(map.get("rpt_prc")));
				temp.add(maptemp);
			}else if("平均收益预测".equals(bz_sign)){
				maptemp = new HashMap();
				maptemp.put("name", "平均收益预测");
				maptemp.put("value", nullFormat(map.get("avg_eps")));
				temp.add(maptemp);
			}else if("买入评级数量".equals(bz_sign)){
				maptemp = new HashMap();
				maptemp.put("name", "买入评级数量");
				maptemp.put("value", nullFormat(map.get("pur_count")));
				temp.add(maptemp);
			}else if("增持评级数量".equals(bz_sign)){
				maptemp = new HashMap();
				maptemp.put("name", "增持评级数量");
				maptemp.put("value", nullFormat(map.get("hold_count")));
				temp.add(maptemp);
			}else if("中性评级数量".equals(bz_sign)){
				maptemp = new HashMap();
				maptemp.put("name", "中性评级数量");
				maptemp.put("value", nullFormat(map.get("neutral_count")));
				temp.add(maptemp);
			}else if("减持评级数量".equals(bz_sign)){
				maptemp = new HashMap();
				maptemp.put("name", "减持评级数量");
				maptemp.put("value", nullFormat(map.get("reduc_count")));
				temp.add(maptemp);
			}else if("卖出评级数量".equals(bz_sign)){
				maptemp = new HashMap();
				maptemp.put("name", "卖出评级数量");
				maptemp.put("value", nullFormat(map.get("avoid_count")));
				temp.add(maptemp);
			}
			
			retTemp.add(temp);
		}
		returnMap.put("data", retTemp);
		returnList.add(returnMap);
		return returnList;
	}
	
	/**
	 * 单一研报分析
	 * @param list
	 * @return
	 */
	public static List<Map> researchOneAnalysis(List<Map> list,String bz_sign) {
		if(list == null || list.size()==0){
			return list;
		}
		Map returnMap = new HashMap();
		List<Map> returnList=new ArrayList<Map>();
		returnMap.put("title", "单一研报分析");
//		String bz_sign = "";
//		
//		String tempWord[]=StockInit.keyword[30].split(",");
//		for(int i=0;i<tempWord.length;i++)
//		{
//			for (int j = 0; j < keywordText.size(); j++) {
//				if(tempWord[i].equals(keywordText.get(j).toString())){
//					bz_sign = tempWord[i];
//					break;
//				}
//			}
//		}
		returnMap.put("bz_sign", bz_sign);
		
		List retTemp=new ArrayList();
		for (Map map : list) {
			List<Map> temp=new ArrayList<Map>();
			Map maptemp = new HashMap();
			maptemp.put("name", "标题");
			maptemp.put("value", nullFormat(map.get("tit")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "股票名称");
			maptemp.put("value", nullFormat(map.get("name")));
			temp.add(maptemp);
			
			if("本次评级".equals(bz_sign)){
				maptemp = new HashMap();
				maptemp.put("name", "本次评级");
				maptemp.put("value", nullFormat(map.get("rat")));
				temp.add(maptemp);
			}else if("上次评级".equals(bz_sign)){
				maptemp = new HashMap();
				maptemp.put("name", "上次评级");
				maptemp.put("value", nullFormat(map.get("rat_lst")));
				temp.add(maptemp);
			}else if("目标价".equals(bz_sign)){
				maptemp = new HashMap();
				maptemp.put("name", "目标价");
				maptemp.put("value", nullFormat(map.get("targ_prc")));
				temp.add(maptemp);
			}else if("收盘价".equals(bz_sign)){
				maptemp = new HashMap();
				maptemp.put("name", "收盘价");
				maptemp.put("value", nullFormat(map.get("cls_prc")));
				temp.add(maptemp);
			}else if("目标涨跌幅".equals(bz_sign)){
				maptemp = new HashMap();
				maptemp.put("name", "目标涨跌幅");
				maptemp.put("value", nullFormat(map.get("targ_rpt")));
				temp.add(maptemp);
			}else if("EPS/2017E".equals(bz_sign)){
				maptemp = new HashMap();
				maptemp.put("name", "EPS/2017E");
				maptemp.put("value", nullFormat(map.get("eps")));
				temp.add(maptemp);
			}else if("PE/2017E".equals(bz_sign)){
				maptemp = new HashMap();
				maptemp.put("name", "PE/2017E");
				maptemp.put("value", nullFormat(map.get("pe")));
				temp.add(maptemp);
			}else if("机构名称".equals(bz_sign)){
				maptemp = new HashMap();
				maptemp.put("name", "机构名称");
				maptemp.put("value", nullFormat(map.get("com_name")));
				temp.add(maptemp);
			}else if("分析师".equals(bz_sign)){
				maptemp = new HashMap();
				maptemp.put("name", "分析师");
				maptemp.put("value", nullFormat(map.get("aut")));
				temp.add(maptemp);
			}else if("所属行业".equals(bz_sign)){
				maptemp = new HashMap();
				maptemp.put("name", "所属行业");
				maptemp.put("value", nullFormat(map.get("lst_sect_name")));
				temp.add(maptemp);
			}else if("发布日期".equals(bz_sign)){
				maptemp = new HashMap();
				maptemp.put("name", "发布日期");
				maptemp.put("value", nullFormat(map.get("pub_dt")));
				temp.add(maptemp);
			}
			retTemp.add(temp);
		}
		returnMap.put("data", retTemp);
		returnList.add(returnMap);
		return returnList;
	}
	
	/**
	 * 行业情绪分析
	 * @param list
	 * @return
	 */
	public static List<Map> industryAnalysis(List<Map> list,String bz_sign) {
		if(list == null || list.size()==0){
			return list;
		}
		Map returnMap = new HashMap();
		List<Map> returnList=new ArrayList<Map>();
		returnMap.put("title", "行业情绪分析");
//		String bz_sign = "";
//		
//		String tempWord[]=StockInit.keyword[31].split(",");
//		for(int i=0;i<tempWord.length;i++)
//		{
//			for (int j = 0; j < keywordText.size(); j++) {
//				if(tempWord[i].equals(keywordText.get(j).toString())){
//					bz_sign = tempWord[i];
//					break;
//				}
//			}
//		}
		returnMap.put("bz_sign", bz_sign);
		
		List retTemp=new ArrayList();
		for (Map map : list) {
			List<Map> temp=new ArrayList<Map>();
			Map maptemp = new HashMap();
			maptemp.put("name", "行业名称");
			maptemp.put("value", nullFormat(map.get("name")));
			temp.add(maptemp);
			if("研报数量".equals(bz_sign)){
				maptemp = new HashMap();
				maptemp.put("name", "研报数量");
				maptemp.put("value", nullFormat(map.get("count")));
				temp.add(maptemp);
			}else if("推荐评级家数".equals(bz_sign)){
				maptemp = new HashMap();
				maptemp.put("name", "推荐评级家数");
				maptemp.put("value", nullFormat(map.get("incr_count")));
				temp.add(maptemp);
			}else if("中性评级家数".equals(bz_sign)){
				maptemp = new HashMap();
				maptemp.put("name", "中性评级家数");
				maptemp.put("value", nullFormat(map.get("neut_count")));
				temp.add(maptemp);
			}else if("回避评级家数".equals(bz_sign)){
				maptemp = new HashMap();
				maptemp.put("name", "回避评级家数");
				maptemp.put("value", nullFormat(map.get("redu_count")));
				temp.add(maptemp);
			}
			retTemp.add(temp);
		}
		returnMap.put("data", retTemp);
		returnList.add(returnMap);
		return returnList;
	}
	
	/**
	 * 机构研究成果分析
	 * @param list
	 * @return
	 */
	public static List<Map> researchAgencyAnalysis(List<Map> list,String bz_sign) {
		if(list == null || list.size()==0){
			return list;
		}
		Map returnMap = new HashMap();
		List<Map> returnList=new ArrayList<Map>();
		returnMap.put("title", "机构研究成果分析");
//		String bz_sign = "";
//		
//		String tempWord[]=StockInit.keyword[32].split(",");
//		for(int i=0;i<tempWord.length;i++)
//		{
//			for (int j = 0; j < keywordText.size(); j++) {
//				if(tempWord[i].equals(keywordText.get(j).toString())){
//					bz_sign = tempWord[i];
//					break;
//				}
//			}
//		}
		returnMap.put("bz_sign", bz_sign);
		
		List retTemp=new ArrayList();
		for (Map map : list) {
			List<Map> temp=new ArrayList<Map>();
			Map maptemp = new HashMap();
			maptemp.put("name", "机构名称");
			maptemp.put("value", nullFormat(map.get("name")));
			temp.add(maptemp);
			if("预测正确率".equals(bz_sign)){
				maptemp = new HashMap();
				maptemp.put("name", "预测正确率");
				maptemp.put("value", nullFormat(map.get("right_rat")));
				temp.add(maptemp);
			}else if("研报阅读数".equals(bz_sign)){
				maptemp = new HashMap();
				maptemp.put("name", "研报阅读数");
				maptemp.put("value", nullFormat(map.get("read_count")));
				temp.add(maptemp);
			}else if("研报下载数".equals(bz_sign)){
				maptemp = new HashMap();
				maptemp.put("name", "研报下载数");
				maptemp.put("value", nullFormat(map.get("download_count")));
				temp.add(maptemp);
			}else if("研报数量".equals(bz_sign)){
				maptemp = new HashMap();
				maptemp.put("name", "研报数量");
				maptemp.put("value", nullFormat(map.get("count")));
				temp.add(maptemp);
			}else if("推荐股票数量".equals(bz_sign)){
				maptemp = new HashMap();
				maptemp.put("name", "推荐股票数量");
				maptemp.put("value", nullFormat(map.get("positive_count")));
				temp.add(maptemp);
			}else if("上涨股票数量".equals(bz_sign)){
				maptemp = new HashMap();
				maptemp.put("name", "上涨股票数量");
				maptemp.put("value", nullFormat(map.get("up_count")));
				temp.add(maptemp);
			}else if("平均上涨幅度".equals(bz_sign)){
				maptemp = new HashMap();
				maptemp.put("name", "平均上涨幅度");
				maptemp.put("value", nullFormat(map.get("up_range_avg")));
				temp.add(maptemp);
			}else if("涨幅最大个股票".equals(bz_sign)){
				maptemp = new HashMap();
				maptemp.put("name", "涨幅最大个股票");
				maptemp.put("value", nullFormat(map.get("up_range_max")));
				temp.add(maptemp);
			}
			retTemp.add(temp);
		}
		returnMap.put("data", retTemp);
		returnList.add(returnMap);
		return returnList;
	}
	
	/**
	 * 分析师研究成果分析
	 * @param list
	 * @return
	 */
	public static List<Map> researchAnalysis(List<Map> list,String bz_sign) {
		if(list == null || list.size()==0){
			return list;
		}
		Map returnMap = new HashMap();
		List<Map> returnList=new ArrayList<Map>();
		returnMap.put("title", "分析师研究成果分析");
//		String bz_sign = "";
//		
//		String tempWord[]=StockInit.keyword[33].split(",");
//		for(int i=0;i<tempWord.length;i++)
//		{
//			for (int j = 0; j < keywordText.size(); j++) {
//				if(tempWord[i].equals(keywordText.get(j).toString())){
//					bz_sign = tempWord[i];
//					break;
//				}
//			}
//		}
		returnMap.put("bz_sign", bz_sign);
		
		List retTemp=new ArrayList();
		for (Map map : list) {
			List<Map> temp=new ArrayList<Map>();
			Map maptemp = new HashMap();
			maptemp.put("name", "分析师");
			maptemp.put("value", nullFormat(map.get("name")));
			temp.add(maptemp);
			if("预测正确率".equals(bz_sign)){
				maptemp = new HashMap();
				maptemp.put("name", "预测正确率");
				maptemp.put("value", nullFormat(map.get("right_rat")));
				temp.add(maptemp);
			}else if("所属机构".equals(bz_sign)){
				maptemp = new HashMap();
				maptemp.put("name", "所属机构");
				maptemp.put("value", nullFormat(map.get("com_name")));
				temp.add(maptemp);
			}else if("研报阅读数".equals(bz_sign)){
				maptemp = new HashMap();
				maptemp.put("name", "研报阅读数");
				maptemp.put("value", nullFormat(map.get("read_count")));
				temp.add(maptemp);
			}else if("研报下载数".equals(bz_sign)){
				maptemp = new HashMap();
				maptemp.put("name", "研报下载数");
				maptemp.put("value", nullFormat(map.get("download_count")));
				temp.add(maptemp);
			}else if("研报数量".equals(bz_sign)){
				maptemp = new HashMap();
				maptemp.put("name", "研报数量");
				maptemp.put("value", nullFormat(map.get("count")));
				temp.add(maptemp);
			}else if("推荐股票数量".equals(bz_sign)){
				maptemp = new HashMap();
				maptemp.put("name", "推荐股票数量");
				maptemp.put("value", nullFormat(map.get("positive_count")));
				temp.add(maptemp);
			}else if("上涨股票数量".equals(bz_sign)){
				maptemp = new HashMap();
				maptemp.put("name", "上涨股票数量");
				maptemp.put("value", nullFormat(map.get("up_count")));
				temp.add(maptemp);
			}else if("平均上涨幅度".equals(bz_sign)){
				maptemp = new HashMap();
				maptemp.put("name", "平均上涨幅度");
				maptemp.put("value", nullFormat(map.get("up_range_avg")));
				temp.add(maptemp);
			}else if("涨幅最大股票".equals(bz_sign)){
				maptemp = new HashMap();
				maptemp.put("name", "涨幅最大股票");
				maptemp.put("value", nullFormat(map.get("up_range_max")));
				temp.add(maptemp);
			}
			retTemp.add(temp);
		}
		returnMap.put("data", retTemp);
		returnList.add(returnMap);
		return returnList;
	}
	
	/**
	 * 
	 * @param list
	 * @return
	 */
	public static List<Map> select(List<Map> list) {
		if(list == null || list.size()==0){
			return list;
		}
		Map returnMap = new HashMap();
		List<Map> returnList=new ArrayList<Map>();
		returnMap.put("title", "");
		List retTemp=new ArrayList();
		for (Map map : list) {
			List<Map> temp=new ArrayList<Map>();
			Map maptemp = new HashMap();
			maptemp.put("name", "基金简称");
			maptemp.put("value", nullFormat(map.get("")));
			temp.add(maptemp);
			maptemp = new HashMap();
			maptemp.put("name", "基金代码");
			maptemp.put("value", nullFormat(map.get("")));
			temp.add(maptemp);
			retTemp.add(temp);
		}
		returnMap.put("data", retTemp);
		returnList.add(returnMap);
		return returnList;
	}
	
	/**
	 * 将null变为""
	 * @param string
	 * @return
	 */
	public static Object nullFormat(Object obj){
		boolean flag= true;
		if(null==obj){
			obj="";
		}
		return obj;
	}
}
