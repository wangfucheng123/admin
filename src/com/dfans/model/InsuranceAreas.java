package com.dfans.model;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InsuranceAreas {
    private Integer id;

    private Date statisticalTime;

    private String areaName;

    private BigDecimal total;
    
    public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	private BigDecimal propInsuInco;

    private BigDecimal lifeInsuInco;

    private BigDecimal acciInsuInco;

    private BigDecimal healInsuInco;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStatisticalTime() {
        return statisticalTime;
    }

    
    public void setStatisticalTime(String statisticalTime) {
        
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        try {
			this.statisticalTime = simpleDateFormat.parse(statisticalTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    }

    
    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    public BigDecimal getPropInsuInco() {
        return propInsuInco;
    }

    public void setPropInsuInco(BigDecimal propInsuInco) {
        this.propInsuInco = propInsuInco;
    }

    public BigDecimal getLifeInsuInco() {
        return lifeInsuInco;
    }

    public void setLifeInsuInco(BigDecimal lifeInsuInco) {
        this.lifeInsuInco = lifeInsuInco;
    }

    public BigDecimal getAcciInsuInco() {
        return acciInsuInco;
    }

    public void setAcciInsuInco(BigDecimal acciInsuInco) {
        this.acciInsuInco = acciInsuInco;
    }

    public BigDecimal getHealInsuInco() {
        return healInsuInco;
    }

    public void setHealInsuInco(BigDecimal healInsuInco) {
        this.healInsuInco = healInsuInco;
    }
}