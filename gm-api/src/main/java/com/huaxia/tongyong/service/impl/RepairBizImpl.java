package com.huaxia.tongyong.service.impl;

import com.huaxia.tongyong.model.ReportRepair;
import com.huaxia.tongyong.param.RepairParam;
import com.huaxia.tongyong.repository.ReportRepairMapper;
import com.huaxia.tongyong.service.RepairBiz;
import com.huaxia.tongyong.vo.ReportRepairVo;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 故障日报信息相关业务接口
 * @author: gongdaoshun
 * @Date: 2017/12/16 22:08
 * @className: RepairBizImpl
 */
@Service
public class RepairBizImpl implements RepairBiz {

    @Autowired
    private ReportRepairMapper reportRepairMapper;

    /**
     * 根据日报id获取故障日报信息
     * @param reportId
     * @return
     */
    @Override
    public ReportRepairVo getReportRepairVo(Long reportId) {
        //1.校验参数
        Assert.notNull(reportId,"reportId不能为空");
        return reportRepairMapper.selectReportRepairVoByReportIdOrId(reportId,null);
    }

    /**
     * 保存故障日报信息
     * @param repairParam
     */
    @Override
    public void saveReportRepair(RepairParam repairParam) {

    }

    /**
     * 更新故障日报信息
     * @param repairParam
     */
    @Override
    public void updateReportRepair(RepairParam repairParam) {

    }

    /**
     * 根据参数获取故障日报信息
     * @param reportStatus
     * @param areaId
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public List<ReportRepairVo> selectReportRepairVoList(Integer reportStatus, Long areaId, String startTime, String endTime) {
        return null;
    }
}
