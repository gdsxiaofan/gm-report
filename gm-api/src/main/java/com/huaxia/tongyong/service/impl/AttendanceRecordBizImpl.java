package com.huaxia.tongyong.service.impl;

import com.huaxia.tongyong.model.AttendanceRecord;
import com.huaxia.tongyong.param.AttendanceRecordParam;
import com.huaxia.tongyong.repository.AttendanceRecordMapper;
import com.huaxia.tongyong.service.AttendanceRecordBiz;
import com.huaxia.tongyong.util.date.DateUtil;
import com.huaxia.tongyong.vo.AttendanceRecordVo;
import io.jsonwebtoken.lang.Assert;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * TODO: 请添加描述
 *
 * @author gongdaoshun
 * @date 2018/1/23
 * @since 1.0.0
 */
@Service
@Slf4j
public class AttendanceRecordBizImpl implements AttendanceRecordBiz{

    @Resource
    private AttendanceRecordMapper attendanceRecordMapper;

    @Override
    public void addAttendance(AttendanceRecordParam attendanceRecordParam) {
        // 1.转换对应的考勤信息
        AttendanceRecord attendanceRecord = transferAttendanceRecordParam(attendanceRecordParam);
        attendanceRecord.setCreateTime(new Date());
        //2.保存考勤信息
        Integer count = attendanceRecordMapper.insertSelective(attendanceRecord);
        if(count.intValue()==0){
            throw new RuntimeException("保存考勤数据失败！");
        }
    }

    /**
     * 根据参数查询对应的考勤信息
     * @param employeeNo
     * @param userName
     * @param month
     * @return
     */
    @Override
    public List<AttendanceRecordVo> getAttendanceRecordVoList(String employeeNo,String userName,String month) {
        //1.转换对应的参数信息
        String startTime ="";
        String endTime="";
        if(StringUtils.isNotBlank(month)){
            startTime=month+"-01 00:00:00";
            String[] dateArray= month.split("-");
            String lastDay = DateUtil.getLastDayByMonth(Integer.valueOf(dateArray[0]),Integer.valueOf(dateArray[1]));
            endTime = month+"-"+lastDay+" 23:59:59";
        }

        return attendanceRecordMapper.getAttendanceRecordList(employeeNo,userName,startTime,endTime);
    }

    @Override
    public void updateAttendance(AttendanceRecordParam attendanceRecordParam) {
        Assert.isTrue(attendanceRecordParam.getId()!=null,"考勤id不能空");
        // 1.转换对应考勤信息
        AttendanceRecord attendanceRecord = transferAttendanceRecordParam(attendanceRecordParam);
        attendanceRecord.setId(attendanceRecordParam.getId());
        // 2.保存考勤信息
        Integer count = attendanceRecordMapper.updateByPrimaryKeySelective(attendanceRecord);
    }

    @Override
    public AttendanceRecordVo getAttendanceRecordVo(int id) {
        AttendanceRecordVo attendanceRecordVo = attendanceRecordMapper.selectByPrimaryKey(id);

        if(attendanceRecordVo!=null){
            //获取当前星期
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(attendanceRecordVo.getWorkStartTime());
            int week =calendar.get(Calendar.DAY_OF_WEEK);
            String weekStr = "星期"+getChinese(week);
            attendanceRecordVo.setWeek(weekStr);
        }
        return attendanceRecordVo;
    }

    private String getChinese(int week) {
        switch (week){
            case 1:
                return "一";
            case 2:
                return "二";
            case 3:
                return "三";
            case 4:
                return "四";
            case 5:
                return "五";
            case 6:
                return "六";
            case 7:
                return "日";
            default:
                return "";
        }
    }

    private AttendanceRecord transferAttendanceRecordParam(AttendanceRecordParam attendanceRecordParam){

        /**
         * 转换考勤数据
         */
        AttendanceRecord attendanceRecord = new AttendanceRecord();
        attendanceRecord.setEmployeeNo(attendanceRecordParam.getEmployeeNo());
        attendanceRecord.setPropertyOne(attendanceRecordParam.getPropertyOne());
        attendanceRecord.setPropertyTwo(attendanceRecordParam.getPropertyTwo());
        attendanceRecord.setWorkHours(attendanceRecordParam.getWorkHours());
        attendanceRecord.setUserName(attendanceRecordParam.getUserName());

        Date startWork = null;
        Date endWork = null;
        if(StringUtils.isNotBlank(attendanceRecordParam.getWorkStartTime())){
                startWork =DateUtil.stringToDate(attendanceRecordParam.getWorkStartTime(),DateUtil.SHORTFMT1);
            }if(StringUtils.isNotBlank(attendanceRecordParam.getWorkEndTime())){
                endWork = DateUtil.stringToDate(attendanceRecordParam.getWorkEndTime(),DateUtil.SHORTFMT1);
            }
        attendanceRecord.setWorkStartTime(startWork);
        attendanceRecord.setWorkEndTime(endWork);

        return attendanceRecord;
    }
}
