package com.huaxia.tongyong.vo;

import lombok.Data;

/**
 * @Description:
 * @author: gongdaoshun
 * @Date: 2017/12/13 22:52
 * @className: ReportFaultVo
 */
@Data
public class ReportFaultVo {
    /****/
    private Long id;

    /**关联的日报信息表id**/
    private Long reportId;

    /**设备名称**/
    private String deviceName;

    private Integer deviceId;

    /**设备故障**/
    private String deviceFault;

    /**处理方法**/
    private String dealMethod;

    /**区域，关联字典表id**/
    private Integer areaId;

    private String areaName;

    /**停线时间**/
    private String stopTime;
    /**停线时长**/
    private String stopTimeSpan;
}
