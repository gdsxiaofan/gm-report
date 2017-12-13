package com.huaxia.tongyong.vo;

import com.huaxia.tongyong.enums.ReportRepairStatusEnum;

/**
 * @Description: 日报明细信息
 * @author: gongdaoshun
 * @Date: 2017/12/13 22:27
 * @className: ReportDetailVo
 */
public class ReportDetailVo {

    /**日报信息表id**/
    private Long reportId;

    /**故障记录：设备名称
     * 修复验证：维修保养设备
     * **/
    private String deviceName;

    /**设备故障**/
    private String deviceFault;

    /**处理方法**/
    private String dealMethod;

    /**区域，关联字典表id**/
    private Integer areaId;

    /**停线时间**/
    private String stopTime;

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
     * 状态确认名称
     * @see ReportRepairStatusEnum
     */
    private String repairStatusName;

    /**
     * 维修员工姓名
     */
    private String repairUserName;

    /**修复后首车号**/
    private String repairCarNo;
}
