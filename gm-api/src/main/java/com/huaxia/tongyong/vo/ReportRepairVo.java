package com.huaxia.tongyong.vo;

import lombok.Data;

/**
 * @Description: 故障日报的信息
 * @author: gongdaoshun
 * @Date: 2017/12/13 22:42
 * @className: ReportRepairVo
 */
@Data
public class ReportRepairVo {
    /****/
    private Long id;

    /**日报信息表id**/
    private Long reportId;

    /**维修保养设备**/
    private String deviceName;

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

    /**
     * 状态名称
     * @see com.huaxia.tongyong.enums.ReportRepairStatusEnum
     */
    private String repairStatusName;

    /**修复后首车号**/
    private String repairCarNo;
}
