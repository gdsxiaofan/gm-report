package com.huaxia.tongyong.api;

import com.github.pagehelper.PageInfo;
import com.huaxia.tongyong.model.AttendanceRecord;
import com.huaxia.tongyong.param.AttendanceRecordParam;
import com.huaxia.tongyong.param.DeviceInfoParam;
import com.huaxia.tongyong.service.AttendanceRecordBiz;
import com.huaxia.tongyong.vo.AttendanceRecordVo;
import com.huaxia.tongyong.vo.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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


    @Resource
    AttendanceRecordBiz attendanceRecordBiz;
    /**
     * 新增考勤记录，需要校验单当前考勤用户是否存在
     * @param attendanceRecordParam
     * @return
     */
    @ApiOperation(value="新增考勤记录",notes = "新增考勤记录数据",httpMethod = "POST",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PostMapping("/add")
    public JsonResult addAttendance(@RequestBody AttendanceRecordParam attendanceRecordParam){

        JsonResult jsonResult = new JsonResult();
        try{
            attendanceRecordBiz.addAttendance(attendanceRecordParam);

        }catch(RuntimeException re){
            log.error("录入考勤记录失败：{}", re.getStackTrace());
            jsonResult.setCode(0);
            jsonResult.setMessage(re.getMessage());
        }
        catch(Exception e){
            log.error("录入考勤记录失败：{}", e.getStackTrace());
            jsonResult.setCode(0);
            jsonResult.setMessage("录入考勤记录失败");
        }
        return jsonResult;
    }

    /**
     *
     * @param deviceInfoParam
     * @return
     */
    @ApiOperation(value="查询考勤记录",notes = "新增考勤记录数据",httpMethod = "POST",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PostMapping("/get/web")
    public JsonResult<PageInfo<AttendanceRecordVo>> selectAttendanceListForWeb(
            @RequestBody DeviceInfoParam deviceInfoParam
    ){
        JsonResult jsonResult = new JsonResult();
        try{

        }catch(RuntimeException re){

        }
        catch(Exception e){

        }
        return jsonResult;
    }

    /**
     *
     * @param deviceInfoParam
     * @return
     */
    @ApiOperation(value="查询考勤记录",notes = "新增考勤记录数据",httpMethod = "POST",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PostMapping("/get/app")
    public JsonResult<PageInfo<AttendanceRecordVo>> selectAttendanceListForApp(
            @RequestBody DeviceInfoParam deviceInfoParam
    ){
        JsonResult jsonResult = new JsonResult();
        try{

        }catch(RuntimeException re){

        }
        catch(Exception e){

        }
        return jsonResult;
    }

    /**
     *
     * @param attendanceRecordParam
     * @return
     */
    @ApiOperation(value="更新考勤记录",notes = "更新考勤记录数据",httpMethod = "POST",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PostMapping("/update")
    public JsonResult updateAttendance(@RequestBody AttendanceRecordParam attendanceRecordParam){
        JsonResult jsonResult = new JsonResult();
        try{
            attendanceRecordBiz.updateAttendance(attendanceRecordParam);

        }catch(RuntimeException re){
            log.error("更新考勤记录失败：{}", re.getStackTrace());
            jsonResult.setCode(0);
            jsonResult.setMessage(re.getMessage());
        }
        catch(Exception e){
            log.error("更新考勤记录失败：{}", e.getStackTrace());
            jsonResult.setCode(0);
            jsonResult.setMessage("更新考勤记录失败");
        }
        return jsonResult;
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
        JsonResult jsonResult = new JsonResult();
        try{
            AttendanceRecordVo attendanceRecordVo =attendanceRecordBiz.getAttendanceRecordVo(id);
            jsonResult.setData(attendanceRecordVo);
        }catch(RuntimeException re){
            log.error("获取考勤详情失败：{}", re.getStackTrace());
            jsonResult.setCode(0);
            jsonResult.setMessage(re.getMessage());
        }
        catch(Exception e){
            log.error("更获取考勤详情失败：{}", e.getStackTrace());
            jsonResult.setCode(0);
            jsonResult.setMessage("获取考勤详情失败");
        }
        return jsonResult;
    }
}
