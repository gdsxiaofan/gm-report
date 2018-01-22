package com.huaxia.tongyong.api;

import com.github.pagehelper.PageInfo;
import com.huaxia.tongyong.param.DeviceInfoParam;
import com.huaxia.tongyong.vo.DeviceInfoVo;
import com.huaxia.tongyong.vo.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * TODO: 请添加描述
 *
 * @author gongdaoshun
 * @date 2018/1/22
 * @since 1.0.0
 */
@RestController
@RequestMapping("/v1/device")
@Slf4j
@Api(value = "DeviceInfoApi" ,description = "设备信息相关接口")
public class DeviceInfoApi {


    /**
     * 新增设备信息
     * @param deviceInfoParam
     * @return
     */
    @ApiOperation(value="新增设备信息",notes = "新增设备信息",httpMethod = "POST",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PostMapping(value="/add")
    public JsonResult addDeviceInfo(
            @RequestBody DeviceInfoParam deviceInfoParam
            ){
        return new JsonResult();
    }

    @ApiOperation(value="查询设备信息",notes = "查询设备信息",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping(value="/get")
    public JsonResult<PageInfo<DeviceInfoVo>> getDeviceList(
            DeviceInfoParam deviceInfoParam
    ){
        return new JsonResult();
    }

    @ApiOperation(value="更新设备信息",notes = "更新设备信息",httpMethod = "POST",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PostMapping(value="/update")
    public JsonResult updateDevieInfo(
            @RequestBody DeviceInfoParam deviceInfoParam
    ){
        return new JsonResult();
    }

    @ApiOperation(value="查询设备详细信息",notes = "查询设备详细信息",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PostMapping(value="/detail")
    public JsonResult getDeviceDetialInfo(
            @RequestParam("id")int id
    ){
        return new JsonResult();
    }
}
