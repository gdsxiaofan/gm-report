package com.huaxia.tongyong.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

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
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date workDate;


}

