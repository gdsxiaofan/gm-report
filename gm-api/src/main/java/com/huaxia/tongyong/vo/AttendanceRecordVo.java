package com.huaxia.tongyong.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Calendar;
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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date workDate;

    /**
     * 周日
     */
    private String week;

    public void setWorkDate(Date workDate) {
        this.workDate = workDate;
        //获取当前星期
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(workDate);
        int week =calendar.get(Calendar.DAY_OF_WEEK);
        this.week="星期"+getChinese(week);

    }

    private String getChinese(int week) {
        switch (week){
            case 1:
                return "日";
            case 2:
                return "一";
            case 3:
                return "二";
            case 4:
                return "三";
            case 5:
                return "四";
            case 6:
                return "五";
            case 7:
                return "六";
            default:
                return "";
        }
    }
}

