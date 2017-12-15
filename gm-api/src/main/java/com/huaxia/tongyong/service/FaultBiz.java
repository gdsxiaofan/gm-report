package com.huaxia.tongyong.service;

import com.huaxia.tongyong.param.FaultParam;
import com.huaxia.tongyong.vo.ReportFaultVo;
import jdk.javadoc.internal.doclets.toolkit.util.JavaScriptScanner;

import java.util.List;

/**
 * @Description: 故障日报相关业务逻辑接口
 * @author: gongdaoshun
 * @Date: 2017/12/14 23:11
 * @className: FaultBiz
 */
public interface FaultBiz {

    /**
     * 根据日报id或者故障id获取对应的故障日报信息
     * @param reportId
     * @param id
     * @return
     */
    public ReportFaultVo getReportFaultVo(
            Long reportId,
            Long id
    );

    /**
     * 保存对应的故障日报信息
     * @param faultParam
     * @return
     */
    public Integer saveReportFault(FaultParam faultParam);

    /**
     * 更新对应的日报信息
     * @param faultParam
     * @return
     */
    public Integer updateReportFault(FaultParam faultParam);

    /**
     * 删除对应的日报信息
     * @param reportId
     * @return
     */
    public Integer deleteReportFault(
            Long reportId
    );

    /**
     * 根据区域、开始时间、结束时间查询对应的数据，并以excel的形式导出
     * @param areaId
     * @param areaId
     * @param startTime
     * @param endTime
     * @return
     */
    public List<ReportFaultVo> getReportFaultVoList(
            Integer reportStatus,
            Long areaId,
            String startTime,
            String endTime
    );
}