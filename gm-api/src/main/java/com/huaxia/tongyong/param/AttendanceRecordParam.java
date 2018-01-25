package com.huaxia.tongyong.param;

import lombok.Data;

/**
 * TODO: 请添加描述
 *
 * @author gongdaoshun
 * @date 2018/1/22
 * @since 1.0.0
 */
@Data
public class AttendanceRecordParam  extends BaseQueryParams{
    /****/
    private Integer id;

    /**工号**/
    private String employeeNo;

    /**用户姓名**/
    private String userName;

    /**上班时间**/
    private String workStartTime;

    /**下班时间**/
    private String workEndTime;

    /**工作时长**/
    private String workHours;

    /**属性一**/
    private String propertyOne;

    /**属性二**/
    private String propertyTwo;

    /**创建时间**/
    private String createTime;
}
