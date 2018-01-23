package com.huaxia.tongyong.repository;

import com.huaxia.tongyong.model.AttendanceRecord;
import com.huaxia.tongyong.vo.AttendanceRecordVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 
 * AttendanceRecordMapper数据库操作接口类
 * 
 **/

public interface AttendanceRecordMapper{

    /**
     * 插入对应的考勤信息
     * @param attendanceRecord
     * @return
     */
    public int insertSelective(AttendanceRecord attendanceRecord);

    /**
     * 更新考勤信息
     * @param attendanceRecord
     * @return
     */
    public int updateByPrimaryKeySelective(AttendanceRecord attendanceRecord);

    /**
     * 查询对应的考勤信息
     * @param id
     * @return
     */
    public AttendanceRecordVo selectByPrimaryKey(Integer id);


    /**
     * 获取考勤列表信息
     * @param employeeNo
     * @param userName
     * @param startTime
     * @param endTime
     * @return
     */
    public List<AttendanceRecordVo> getAttendanceRecordList(
            @Param("employeeNo")String employeeNo,
            @Param("userName")String userName,
            @Param("startTime")String startTime,
            @Param("endTime") String endTime);
}