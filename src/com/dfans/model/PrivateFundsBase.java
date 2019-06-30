package com.dfans.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PrivateFundsBase {
	
	private String gzll;

    private String integrityInfo;

    public String getGzll() {
        return gzll;
    }

    public void setGzll(String gzll) {
        this.gzll = gzll == null ? null : gzll.trim();
    }

    public String getIntegrityInfo() {
        return integrityInfo;
    }

    public void setIntegrityInfo(String integrityInfo) {
        this.integrityInfo = integrityInfo == null ? null : integrityInfo.trim();
    }
    private Integer id;

    private String chName;

    private String enName;

    private String djbh;

    private String zzjgdm;

    private Date ddate;

    private Date cdate;

    private String zcdz;

    private String bgdz;

    private String zczb;

    private String sjzb;

    private Integer dicQyxz;

    private String zcjlbl;

    private Integer dicGljjlb;

    private String qtlwlx;

    private Integer ygrs;

    private String webUrl;

    private String flyjszt;

    private String lsswsmc;

    private String lsName;

    private String bddbr;

    private Integer cyzg;

    private String zghqfs;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChName() {
        return chName;
    }

    public void setChName(String chName) {
        this.chName = chName == null ? null : chName.trim();
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName == null ? null : enName.trim();
    }

    public String getDjbh() {
        return djbh;
    }

    public void setDjbh(String djbh) {
        this.djbh = djbh == null ? null : djbh.trim();
    }

    public String getZzjgdm() {
        return zzjgdm;
    }

    public void setZzjgdm(String zzjgdm) {
        this.zzjgdm = zzjgdm == null ? null : zzjgdm.trim();
    }

    public Date getDdate() {
        return ddate;
    }

    public void setDdate(String ddate) {
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
			this.ddate = simpleDateFormat.parse(ddate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
			this.cdate = simpleDateFormat.parse(cdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public String getZcdz() {
        return zcdz;
    }

    public void setZcdz(String zcdz) {
        this.zcdz = zcdz == null ? null : zcdz.trim();
    }

    public String getBgdz() {
        return bgdz;
    }

    public void setBgdz(String bgdz) {
        this.bgdz = bgdz == null ? null : bgdz.trim();
    }

    public String getZczb() {
        return zczb;
    }

    public void setZczb(String zczb) {
        this.zczb = zczb == null ? null : zczb.trim();
    }

    public String getSjzb() {
        return sjzb;
    }

    public void setSjzb(String sjzb) {
        this.sjzb = sjzb == null ? null : sjzb.trim();
    }

    public Integer getDicQyxz() {
        return dicQyxz;
    }

    public void setDicQyxz(Integer dicQyxz) {
        this.dicQyxz = dicQyxz;
    }

    public String getZcjlbl() {
        return zcjlbl;
    }

    public void setZcjlbl(String zcjlbl) {
        this.zcjlbl = zcjlbl == null ? null : zcjlbl.trim();
    }

    public Integer getDicGljjlb() {
        return dicGljjlb;
    }

    public void setDicGljjlb(Integer dicGljjlb) {
        this.dicGljjlb = dicGljjlb;
    }

    public String getQtlwlx() {
        return qtlwlx;
    }

    public void setQtlwlx(String qtlwlx) {
        this.qtlwlx = qtlwlx == null ? null : qtlwlx.trim();
    }

    public Integer getYgrs() {
        return ygrs;
    }

    public void setYgrs(Integer ygrs) {
        this.ygrs = ygrs;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl == null ? null : webUrl.trim();
    }

    public String getFlyjszt() {
        return flyjszt;
    }

    public void setFlyjszt(String flyjszt) {
        this.flyjszt = flyjszt == null ? null : flyjszt.trim();
    }

    public String getLsswsmc() {
        return lsswsmc;
    }

    public void setLsswsmc(String lsswsmc) {
        this.lsswsmc = lsswsmc == null ? null : lsswsmc.trim();
    }

    public String getLsName() {
        return lsName;
    }

    public void setLsName(String lsName) {
        this.lsName = lsName == null ? null : lsName.trim();
    }

    public String getBddbr() {
        return bddbr;
    }

    public void setBddbr(String bddbr) {
        this.bddbr = bddbr == null ? null : bddbr.trim();
    }

    public Integer getCyzg() {
        return cyzg;
    }

    public void setCyzg(Integer cyzg) {
        this.cyzg = cyzg;
    }

    public String getZghqfs() {
        return zghqfs;
    }

    public void setZghqfs(String zghqfs) {
        this.zghqfs = zghqfs == null ? null : zghqfs.trim();
    }
}