package com.huaxia.tongyong.repository;

import com.huaxia.tongyong.model.ReportTransferLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


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

    public List<ReportTransferLog> selectReportTransferLogByDate(
            @Param("startTime")String startTime,
            @Param("endTime")String endTime
    );
}