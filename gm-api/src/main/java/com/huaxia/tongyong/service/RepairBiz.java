package com.huaxia.tongyong.service;

import com.huaxia.tongyong.vo.ReportRepairVo;

/**
 * @Description:
 * @author: gongdaoshun
 * @Date: 2017/12/14 23:19
 * @className: RepairBiz
 */
public interface RepairBiz {

    /**
     * 根据日报id获取当前的故障日报信息
     * @param reportId
     * @return
     */
    public ReportRepairVo getReportRepairVo(
            Long reportId
    );
}