package com.huaxia.tongyong.param;

import lombok.Data;

/**
 * @Description: 故障记录录入参数
 * @author: gongdaoshun
 * @Date: 2017/12/12 20:58
 * @className: FaultParam
 */
@Data
public class FaultParam {

    /**
     * 关联的日报id
     */
    Long reportId;

    /**
     * 主键id
     */
    Long id;
    /**
     * 设备名称
     */
    String deviceName;

    Integer deviceId;
    /**
     * 设备故障
     */
    String deviceFault;
    /**
     * 设备方法
     */
    String dealMethod;
    /**
     * 区域
     */
    String areaName;

    Integer areaId;
    /**
     * 停线时间
     */
    String stopTime;
    /**
     * 停线时长
     */
    String stopTimeSpan;
    /**
     * 处理人
     */
    String dealPersonId;

    /**
     * @see com.huaxia.tongyong.enums.ReportStatusEnum
     * 日报的状态
     */
    int reportStatus;
}
