package com.dfans.dao;

import java.util.List;
import java.util.Map;


public interface SearchMapper {

	List<Map> selectStockMsg();
	
	
	List<Map> selectFundMsg();
	
	
	List<Map> selectApplyMsg();


	List<Map> selectJbzl(Map param);


	List<Map> selectBzt1(Map param);
	
	
	List<Map> selectBzt2(Map param);


	List<Map> selectZxt1(Map param);


	List<Map> selectZxt2(Map param);


	List<Map> selectZxt3(Map param);


	List<Map> selectZxt4(Map param);


	List<Map> selectZxt5(Map param);


	List<Map> selectZxt6(Map param);


	List<Map> selectZxt7(Map param);


	List<Map> selectZxt8(Map param);


	List<Map> selectZxt9(Map param);


	List<Map> selectZxt10(Map param);


	List<Map> selectZxt11(Map param);


	List<Map> selectZxt12(Map param);
	
	
	List<Map> selectZxt13(Map param);
	
	
	List<Map> selectZxt14(Map param);
	
	
	List<Map> selectZxt15(Map param);
	
	
	List<Map> selectZxt16(Map param);
	
	
	List<Map> selectZxt17(Map param);
	
	
	List<Map> selectZxt18(Map param);
	
	
	List<Map> selectZxt19(Map param);


	List<Map> selectZzt1(Map param);
	
	
	List<Map> selectZzt2(Map param);


	List<Map> selectXwzx1(Map param);


	List<Map> selectYb1(Map param);


	List<Map> selectXwgg1(Map param);


	List<Map> selectHghy(Map param);


	List<Map> selectXwgg2(Map param);


	List<Map> selectYb2(Map param);


	List<Map> selectXwzz2(Map param);


	List<Map> selectF10(Map param);


	List<Map> selectHghyXQ(Map param);
	
	List<Map> selectHghyXQ0(Map param);
	
	List<Map> selectHghyXQ1(Map param);
	
	List<Map> selectHghyXQ2(Map param);
	
	List<Map> selectHghyXQ3(Map param);
	
	List<Map> selectHghyXQ4(Map param);
	
	List<Map> selectTpzx(Map param);
	List<Map> selectTpzxById(Map param);
	/**
	 * 新股申购或新股配售
	 * @param param
	 * @return
	 */
	List<Map> selectXGSG(Map param);
	
	/**
	 * 新股过会或新股上会、新股审议
	 * @param param
	 * @return
	 */
	List<Map> selectXGGH(Map param);
	
	/**
	 * 新股上市或新股发行
	 * @param param
	 * @return
	 */
	List<Map> selectXGSS(Map param);
	
	/**
	 * 新发或新发基金
	 * @param param
	 * @return
	 */
	List<Map> selectXFJJ(Map param);
	
	/**
	 * 开放式或开放式基金
	 * @param param
	 * @return
	 */
	List<Map> selectKFSJJ(Map param);
	
	/**
	 * 货币型或货币型基金
	 * @param param
	 * @return
	 */
	List<Map> selectHBXJJ(Map param);
	
	/**
	 * 分级或分级基金
	 * @param param
	 * @return
	 */
	List<Map> selectFJJJ(Map param);
	
	/**
	 * 封闭式或封闭式基金
	 * @param param
	 * @return
	 */
	List<Map> selectFBSJJ(Map param);
	
	/**
	 * 行情或基金行情
	 * @param param
	 * @return
	 */
	List<Map> selectJJHQ(Map param);
	
	/**
	 * 基金公司
	 * @param param
	 * @return
	 */
	List<Map> selectJJGS(Map param);
	
	/**
	 * 基金经理或经理（如：王帅基金经理）
	 * @param param
	 * @return
	 */
	List<Map> selectJJJL(Map param);
	
	/**
	 * 资讯-财经新闻
	 * @param param
	 * @return
	 */
	List<Map> myCagetorys(Map param);
	
	/**
	 * 资讯-产业新闻标题
	 * @param param
	 * @return
	 */
	List<Map> selectMyIndustrysMsg(Map param);
	
	/**
	 * 资讯-产业新闻
	 * @param param
	 * @return
	 */
	List<Map> myIndustrys(Map param);
	
	/**
	 * 资讯-个股新闻标题
	 * @param param
	 * @return
	 */
	List<Map> selectMyStocksMsg(Map param);
	
	/**
	 * 资讯-个股新闻
	 * @param param
	 * @return
	 */
	List<Map> myStocks(Map param);
	
	/**
	 * 个股情绪分析
	 * @param param
	 * @return
	 */
	List<Map> stockAnalysis(Map param);
	
	/**
	 * 单一研报分析
	 * @param param
	 * @return
	 */
	List<Map> researchOneAnalysis(Map param);
	
	/**
	 * 行业情绪分析
	 * @param param
	 * @return
	 */
	List<Map> industryAnalysis(Map param);
	
	/**
	 * 机构研究成果分析
	 * @param param
	 * @return
	 */
	List<Map> researchAgencyAnalysis(Map param);
	
	/**
	 * 分析师研究成果分析
	 * @param param
	 * @return
	 */
	List<Map> researchAnalysis(Map param);
	
	/**
	 * 总股本
	 * @param param
	 * @return
	 */
	String totalShareCapital(String code);
	
}
