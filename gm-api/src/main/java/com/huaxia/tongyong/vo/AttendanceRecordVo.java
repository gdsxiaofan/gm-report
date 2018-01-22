package com.huaxia.tongyong.vo;

import lombok.Data;

/**
 * TODO: 请添加描述
 *
 * @author gongdaoshun
 * @date 2018/1/22
 * @since 1.0.0
 */
@Data
public class AttendanceRecordVo {
    /****/
    private Integer id;

    /**
     * 工号
     **/
    private String employeeNo;

    /**
     * 用户姓名
     **/
    private String userName;

    /**
     * 上班时间
     **/
    private java.util.Date workStartTime;

    /**
     * 下班时间
     **/
    private java.util.Date workEndTime;

    /**
     * 工作时长
     **/
    private Integer workHours;

    /**
     * 属性一
     **/
    private String propertyOne;

    /**
     * 属性二
     **/
    private String propertyTwo;
}

