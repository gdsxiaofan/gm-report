package com.huaxia.tongyong.repository;

import com.huaxia.tongyong.model.ReportTransferLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


/**
 * 
 * ReportTransferLogMapper数据库操作接口类
 * 
 **/
@Mapper
public interface ReportTransferLogMapper{

    /**
     * 记录日报交接日志
     * @param reportTransferLog
     * @return
     */
    public Integer insertSelective(ReportTransferLog reportTransferLog);
}