package com.huaxia.tongyong.api;

import com.huaxia.tongyong.param.RepairParam;
import com.huaxia.tongyong.vo.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @Description:
 * @author: gongdaoshun
 * @Date: 2017/12/13 23:21
 * @className: RepairController
 */
@Api(value = "RepairController" ,description = "修复验证的接口定义")
@RestController("/v1/repair")
@Slf4j
public class RepairController {

    @ApiOperation("新增修复验证日报信息")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public JsonResult addRepair(@RequestBody RepairParam repairParam){
        return null;
    }


    @ApiOperation("获取修复验证的日报信息")
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public JsonResult getRepair(
            @RequestParam("reportId")Long reportId
    ){
        return null;
    }

    @ApiOperation("更新修复验证日报信息")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public JsonResult updateRepair(@RequestBody RepairParam repairParam){
        return null;
    }

    @ApiOperation("删除修复验证日报信息")
    @RequestMapping(value="/delete",method = RequestMethod.DELETE)
    public JsonResult deleteRepair(
            @RequestParam("reportId")Long reportId
    ){
        return null;
    }

    @ApiOperation("根据条件下载对应的日报数据")
    @RequestMapping(value = "/download",method = RequestMethod.GET)
    public void downloadRepairReport(
            @RequestParam("deviceName")String deviceName,
            @RequestParam("startTime")String startTime,
            @RequestParam("endTime")String endTime,
            HttpServletResponse response
    ){

    }
}
