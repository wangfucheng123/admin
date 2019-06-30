package com.dfans.dao;

import org.apache.ibatis.annotations.Param;

import com.dfans.model.FreeTrial;

public interface FreeTrialMapper {   
    FreeTrial selectByUidPid(@Param("uid")Integer uid,@Param("treeid")Integer treeid);  
    int saveTrailRecord(FreeTrial t);
}