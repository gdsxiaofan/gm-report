package com.huaxia.tongyong.service;

import com.huaxia.tongyong.model.AttendanceRecord;
import com.huaxia.tongyong.param.AttendanceRecordParam;
import com.huaxia.tongyong.vo.AttendanceRecordVo;

import java.util.List;

/**
 * 考勤记录的业务层接口
 *
 * @author gongdaoshun
 * @date 2018/1/23
 * @since 1.0.0
 */
public interface AttendanceRecordBiz {

    /**
     * 新增考勤记录信息
     * @param attendanceRecordParam
     */
    public void addAttendance(AttendanceRecordParam attendanceRecordParam);

    /**
     * 根据工号，用户名称，月份查询考勤信息
     * @param employeeNo
     * @param userName
     * @param month
     * @return
     */
    public List<AttendanceRecordVo> getAttendanceRecordVoList(String employeeNo,String userName,String month);

    /**
     *
     * @param attendanceRecordParam
     */
    public void updateAttendance(AttendanceRecordParam attendanceRecordParam);

    /**
     * 查询对应的考勤记录
     * @param id
     */
    public AttendanceRecordVo getAttendanceRecordVo(int id);
}
