package com.huaxia.tongyong.service.impl;

import com.huaxia.tongyong.model.ReportFault;
import com.huaxia.tongyong.param.FaultParam;
import com.huaxia.tongyong.repository.ReportFaultMapper;
import com.huaxia.tongyong.service.FaultBiz;
import com.huaxia.tongyong.vo.ReportFaultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public ReportFaultVo getReportFaultVo(Long reportId, Long id) {
        return reportFaultMapper.selectReportFaultVo(reportId,id);
    }

    @Override
    public Integer saveReportFault(FaultParam faultParam) {
        return null;
    }

    @Override
    public Integer updateReportFault(FaultParam faultParam) {
        return null;
    }

    @Override
    public Integer deleteReportFault(Long reportId) {
        return null;
    }

    @Override
    public List<ReportFaultVo> getReportFaultVoList(Integer reportStatus, Long areaId, String startTime, String endTime) {
        return null;
    }

    private ReportFault transferFault(){
        return null;
    }
}
