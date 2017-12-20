package com.huaxia.tongyong.service;

import com.huaxia.tongyong.param.RepairParam;
import com.huaxia.tongyong.vo.ReportRepairVo;

import java.util.List;

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

    /**
     * 保存故障日报信息
     * @param repairParam
     */
    public void saveReportRepair(RepairParam repairParam);

    /**
     * 更新故障的日报信息
     * @param repairParam
     */
    public void updateReportRepair(RepairParam repairParam);

    /**
     * 获取故障日报信息里列表
     * @param reportStatus
     * @param deviceId
     * @param startTime
     * @param endTime
     * @return
     */
    public List<ReportRepairVo> selectReportRepairVoList(
            Integer reportStatus,
            Integer deviceId,
            String startTime,
            String endTime
    );

    /**
     * 删除对应的日报信息
     * @param reportId
     */
    public void deleteRepair(
            Long reportId
    );
}