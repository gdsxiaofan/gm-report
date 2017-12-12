package com.huaxia.tongyong.service;

import com.huaxia.tongyong.param.FaultParam;

/**
 * @Description: 故障日报相关servied的接口
 * @author: gongdaoshun
 * @Date: 2017/12/12 21:05
 * @className: FaultRecordService
 */
public interface FaultRecordService {

    /**
     * 新增故障日报记录
     * @param faultParam
     */
    public void addFaultRecord(FaultParam faultParam);
}