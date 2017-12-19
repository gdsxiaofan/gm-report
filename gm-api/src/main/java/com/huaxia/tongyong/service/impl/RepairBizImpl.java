package com.huaxia.tongyong.service.impl;

import com.huaxia.tongyong.enums.ReportTypeEnum;
import com.huaxia.tongyong.model.ReportInfo;
import com.huaxia.tongyong.model.ReportRepair;
import com.huaxia.tongyong.model.UserInfo;
import com.huaxia.tongyong.param.RepairParam;
import com.huaxia.tongyong.repository.ReportInfoMapper;
import com.huaxia.tongyong.repository.ReportRepairMapper;
import com.huaxia.tongyong.service.RepairBiz;
import com.huaxia.tongyong.util.json.JSONHelper;
import com.huaxia.tongyong.vo.ReportRepairVo;
import io.jsonwebtoken.lang.Assert;
import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerBeanMapper;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Autowired
    private ReportInfoMapper reportInfoMapper;

    @Autowired
    private DozerBeanMapper dozerBeanMapper;

    /**
     * 根据日报id获取故障日报信息
     *
     * @param reportId
     * @return
     */
    @Override
    public ReportRepairVo getReportRepairVo(Long reportId) {
        //1.校验参数
        Assert.notNull(reportId, "reportId不能为空");
        return reportRepairMapper.selectReportRepairVoByReportIdOrId(reportId, null);
    }

    /**
     * 保存故障日报信息
     *
     * @param repairParam
     */
    @Override
    public void saveReportRepair(RepairParam repairParam) {
        //1.组装日报主体信息
        ReportInfo reportInfo = transferToReportInfo(repairParam);
        reportInfoMapper.insertSelective(reportInfo);
        //2.组装故障日报信息
        ReportRepair reportRepair = dozerBeanMapper.map(repairParam,ReportRepair.class);
        reportRepair.setReportId(reportInfo.getId());
        reportRepairMapper.insertSelective(reportRepair);
    }

    /**
     * 更新故障日报信息
     *
     * @param repairParam
     */
    @Override
    public void updateReportRepair(RepairParam repairParam) {
        //1.组装日报主体信息
        ReportInfo reportInfo = transferToReportInfo(repairParam);
        reportInfoMapper.updateByPrimaryKeySelective(reportInfo);
        //2.组装故障日报信息
        ReportRepair reportRepair = dozerBeanMapper.map(repairParam,ReportRepair.class);
        reportRepair.setReportId(reportInfo.getId());
        reportRepairMapper.updateByPrimaryKeySelective(reportRepair);
    }

    /**
     * 根据参数获取故障日报信息
     *
     * @param reportStatus
     * @param deviceId
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public List<ReportRepairVo> selectReportRepairVoList(Integer reportStatus, Integer deviceId, String startTime, String endTime) {
        return reportRepairMapper.selectReportRepairVoForExcel(reportStatus,deviceId,startTime,endTime);
    }

    /**
     * 转换故障日报信息
     *
     * @param repairParam
     * @return
     */
    private ReportInfo transferToReportInfo(RepairParam repairParam) {
        ReportInfo reportInfo = new ReportInfo();
        reportInfo.setId(repairParam.getReportId());
        reportInfo.setReportType(ReportTypeEnum.FAULT.getCode());
        reportInfo.setCreateTime(new Date());
        reportInfo.setReportStatus(repairParam.getReportStatus());


        String userInfoStr = MDC.get("user");
        if(StringUtils.isBlank(userInfoStr)){
            throw new RuntimeException("当前用户未登录，请登录后在操作");
        }
        UserInfo userInfo = JSONHelper.jsonToObject(userInfoStr, UserInfo.class);
        reportInfo.setCreateUserId(userInfo.getId());
        reportInfo.setCreateUserName(userInfo.getName());
        reportInfo.setChargeUserId(userInfo.getId());
        reportInfo.setCreateUserName(userInfo.getName());
        return reportInfo;
    }

}
