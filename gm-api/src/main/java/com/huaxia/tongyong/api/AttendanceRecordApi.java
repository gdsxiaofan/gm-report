package com.huaxia.tongyong.api;

import com.github.pagehelper.PageInfo;
import com.huaxia.tongyong.model.AttendanceRecord;
import com.huaxia.tongyong.param.AttendanceRecordParam;
import com.huaxia.tongyong.param.DeviceInfoParam;
import com.huaxia.tongyong.vo.AttendanceRecordVo;
import com.huaxia.tongyong.vo.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * 考勤记录相关的接口
 *
 * @author gongdaoshun
 * @date 2018/1/22
 * @since 1.0.0
 */
@RestController
@RequestMapping("/v1/attendance")
@Slf4j
@Api(value = "DictionaryApi" ,description = "考勤相关接口")
public class AttendanceRecordApi {


    /**
     * 新增考勤记录，需要校验单当前考勤用户是否存在
     * @param attendanceRecordParam
     * @return
     */
    @ApiOperation(value="新增考勤记录",notes = "新增考勤记录数据",httpMethod = "POST",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PostMapping("/add")
    public JsonResult addAttendance(@RequestBody AttendanceRecordParam attendanceRecordParam){

        return new JsonResult();
    }

    /**
     *
     * @param deviceInfoParam
     * @return
     */
    @ApiOperation(value="查询考勤记录",notes = "新增考勤记录数据",httpMethod = "POST",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PostMapping("/get")
    public JsonResult<PageInfo<AttendanceRecordVo>> selectAttendanceList(
            @RequestBody DeviceInfoParam deviceInfoParam
    ){
        return new JsonResult();
    }

    /**
     *
     * @param attendanceRecordParam
     * @return
     */
    @ApiOperation(value="更新考勤记录",notes = "更新考勤记录数据",httpMethod = "POST",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PostMapping("/update")
    public JsonResult updateAttendance(@RequestBody AttendanceRecordParam attendanceRecordParam){
        return new JsonResult();
    }

    /**
     * 查看考勤详情
     * @param id
     * @return
     */
    @ApiOperation(value="获取考勤详情",notes = "获取考勤详情",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/detail")
    public JsonResult<AttendanceRecordVo> getAttendanceDetail(
            @Param("id")int id
    ){
        return new JsonResult<>();
    }
}
