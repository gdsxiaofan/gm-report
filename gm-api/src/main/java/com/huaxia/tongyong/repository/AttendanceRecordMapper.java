package com.huaxia.tongyong.repository;

import com.huaxia.tongyong.model.AttendanceRecord;


/**
 * 
 * AttendanceRecordMapper数据库操作接口类
 * 
 **/

public interface AttendanceRecordMapper{

    public int insertSelective(AttendanceRecord attendanceRecord);
}