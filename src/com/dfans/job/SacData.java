package com.dfans.job;

import org.springframework.beans.factory.annotation.Autowired;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.dfans.dao.DataCompanyMapper;
import com.dfans.model.DataCompany;
import com.dfans.utils.GetDataUtils;

public class SacData {

	private static String areaListUrl="http://jg.sac.net.cn/pages/publicity/resource!search.action";
	private static String companyListUrl="http://jg.sac.net.cn/pages/publicity/resource!search.action?filter_EQS_O%23otc_id=01&filter_EQS_O%23sac_id=&filter_LIKES_aoi_name=&sqlkey=publicity&sqlval=ORG_BY_TYPE_INFO";
	private static String baseMsgUrl="http://jg.sac.net.cn/pages/publicity/resource!search.action?filter_EQS_aoi_id={AOI_ID}&sqlkey=publicity&sqlval=SELECT_ZQ_REG_INFO";
	private static String baseZigeMsgUrl="http://jg.sac.net.cn/pages/publicity/resource!search.action?filter_EQS_aoi_id={AOI_ID}&sqlkey=publicity&sqlval=SEARCH_ZQGS_QUALIFATION";
	private static String fgsUrl="http://jg.sac.net.cn/pages/publicity/resource!list.action?filter_LIKES_mboi_branch_full_name=&filter_LIKES_mboi_off_address=&filter_EQS_aoi_id={AOI_ID}&page.searchFileName=publicity&page.sqlKey=PAG_BRANCH_ORG&page.sqlCKey=SIZE_BRANCH_ORG&_search=false&nd=1467618912977&page.pageSize=15&page.pageNo=1&page.orderBy=MATO_UPDATE_DATE&page.order=desc";
	private static String yybUrl="http://jg.sac.net.cn/pages/publicity/resource!list.action?filter_LIKES_msdi_name=&filter_LIKES_msdi_reg_address=&filter_EQS_aoi_id={AOI_ID}&page.searchFileName=publicity&page.sqlKey=PAG_SALES_DEPT&page.sqlCKey=SIZE_SALES_DEPT&_search=false&nd=1467619993612&page.pageSize=15&page.pageNo=1&page.orderBy=MATO_UPDATE_DATE&page.order=desc";
	private static String ggxxUrl="http://jg.sac.net.cn/pages/publicity/resource!search.action?filter_EQS_aoi_id={AOI_ID}&sqlkey=publicity&sqlval=EXECUTIVE_LIST";
	
	@Autowired
	private DataCompanyMapper dataCompanyDao;
	
	public void getSacData()
	{
//		DataSourceHolder.setDataSourceType("data");
//		String companyJson=GetDataUtils.sendGet(companyListUrl);
//		JSONArray json = JSONArray.fromObject(companyJson);
//		for(int i=0;i<json.size();i++)
//		{
//			JSONObject temp=JSONObject.fromObject(json.get(i));
//			DataCompany company=new DataCompany();
//			company.setName(temp.getString("AOI_NAME"));
//			dataCompanyDao.insertSelective(company);
//			String msg=GetDataUtils.sendGet(baseMsgUrl.replace("{AOI_ID}", temp.get("AOI_ID").toString()));
//			String zigeMsg=GetDataUtils.sendGet(baseZigeMsgUrl.replace("{AOI_ID}", temp.get("AOI_ID").toString()));
//			String fgsMsg=GetDataUtils.sendGet(fgsUrl.replace("{AOI_ID}", temp.get("AOI_ID").toString()));
//			String yybMsg=GetDataUtils.sendGet(yybUrl.replace("{AOI_ID}", temp.get("AOI_ID").toString()));
//			String ggxxMsg=GetDataUtils.sendGet(ggxxUrl.replace("{AOI_ID}", temp.get("AOI_ID").toString()));
//			
//			
//			
//			
//		}
	}
	
	
	
	
	
	
	public static void main(String args[])
	{
		SacData ff=new SacData();
		ff.getSacData();
	}
}
