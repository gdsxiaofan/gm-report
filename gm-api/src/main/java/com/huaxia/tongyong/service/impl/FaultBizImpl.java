package com.huaxia.tongyong.service.impl;

import com.huaxia.tongyong.enums.ReportTypeEnum;
import com.huaxia.tongyong.model.ReportFault;
import com.huaxia.tongyong.model.ReportInfo;
import com.huaxia.tongyong.model.UserInfo;
import com.huaxia.tongyong.param.FaultParam;
import com.huaxia.tongyong.repository.ReportFaultMapper;
import com.huaxia.tongyong.repository.ReportInfoMapper;
import com.huaxia.tongyong.repository.ReportRepairMapper;
import com.huaxia.tongyong.service.FaultBiz;
import com.huaxia.tongyong.util.date.DateUtil;
import com.huaxia.tongyong.util.json.JSONHelper;
import com.huaxia.tongyong.vo.ReportFaultVo;
import org.dozer.DozerBeanMapper;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @author: gongdaoshun
 * @Date: 2017/12/19 22:25
 * @className: FaultBizImpl
 */
@Service
public class FaultBizImpl implements FaultBiz{

    @Autowired
    private ReportFaultMapper reportFaultMapper;

    @Autowired
    private DozerBeanMapper dozerBeanMapper;

    @Autowired
    private ReportInfoMapper reportInfoMapper;

    @Override
    public ReportFaultVo getReportFaultVo(Long reportId, Long id) {
        return reportFaultMapper.selectReportFaultVo(reportId,id);
    }

    @Override
    public Integer saveReportFault(FaultParam faultParam) {
        //1.获取当前登录用户信息
        UserInfo userInfo = JSONHelper.jsonToObject(MDC.get("user"),UserInfo.class);
        //2.保存日报主体信息
        ReportInfo reportInfo = transferReportInfo(faultParam);
        reportInfo.setChargeUserId(userInfo.getId());
        reportInfo.setChargeUserName(userInfo.getName());
        reportInfo.setCreateUserId(userInfo.getId());
        reportInfo.setCreateUserName(userInfo.getName());
        reportInfo.setCreateTime(new Date());
        reportInfoMapper.insertSelective(reportInfo);

        ReportFault reportFault = transferFault(faultParam);
        reportFault.setReportId(reportInfo.getId());
        reportFault.setCreateTime(new Date());
        return reportFaultMapper.insertSelective(reportFault);
    }

    @Override
    public Integer updateReportFault(FaultParam faultParam) {
        //1.转换对应的日报信息,并更新日报信息
        ReportInfo reportInfo = transferReportInfo(faultParam);
        reportInfoMapper.updateByPrimaryKeySelective(reportInfo);
        //2.更新故障日报信息
        ReportFault reportFault = transferFault(faultParam);
        return reportFaultMapper.updateByPrimaryKeySelective(reportFault);
    }

    @Override
    public Integer deleteReportFault(Long reportId) {
        //1.删除对应的日报主体信息
        Integer count =reportInfoMapper.updateForDelete(reportId);
        if(count!=1){
            throw new RuntimeException("当前日报信息已删除");
        }
        count = reportFaultMapper.updateForDelete(reportId);
        if(count!=1){
            throw new RuntimeException("当前日报信息已删除");
        }
        return count;
    }

    @Override
    public List<ReportFaultVo> getReportFaultVoList(Integer reportStatus, Long areaId, String startTime, String endTime) {
        return null;
    }

    /**
     * 转换参数数据
     * @param faultParam
     * @return
     */
    private ReportFault transferFault(FaultParam faultParam){
        ReportFault reportFault = dozerBeanMapper.map(faultParam,ReportFault.class);
        return reportFault;
    }

    private ReportInfo transferReportInfo(FaultParam faultParam){
        ReportInfo reportInfo = new ReportInfo();
        reportInfo.setReportStatus(faultParam.getReportStatus());
        reportInfo.setReportType(ReportTypeEnum.FAULT.getCode());
        reportInfo.setReportName(faultParam.getDeviceName());
        reportInfo.setId(faultParam.getReportId());
        return reportInfo;
    }

}
