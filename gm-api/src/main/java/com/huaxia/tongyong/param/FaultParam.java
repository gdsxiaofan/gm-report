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
     * 设备名称
     */
    String deviceName;
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
    String area;
    /**
     * 停线时间
     */
    String stopTime;
    /**
     * 处理人
     */
    String dealPersonId;
}