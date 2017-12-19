package com.huaxia.tongyong.param;

import lombok.Data;

/**
 * @Description: 修复验证日报信息录入、更新参数
 * @author: gongdaoshun
 * @Date: 2017/12/13 21:40
 * @className: RepairParam
 */
@Data
public class RepairParam {
    /****/
    private Long id;

    /**日报信息表id**/
    private Long reportId;

    /**维修保养设备**/
    private String deviceName;

    /**
     * 设备id
     */
    private Integer deviceId;

    /**维修保养设备**/
    private String repairComment;

    /**更换部件**/
    private String replaceParts;

    /**验证措施**/
    private String validateMethod;

    /**标定体积**/
    private String calibratedVolume;

    /**状态确认：1，未修复，2，已修复**/
    private Integer repairStatus;

    /**修复后首车号**/
    private String repairCarNo;

    /**
     * @see com.huaxia.tongyong.enums.ReportStatusEnum
     * 日报的状态
     */
    int reportStatus;
}
