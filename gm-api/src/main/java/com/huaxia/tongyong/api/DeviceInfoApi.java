package com.huaxia.tongyong.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huaxia.tongyong.param.DeviceInfoParam;
import com.huaxia.tongyong.service.DeviceInfoBiz;
import com.huaxia.tongyong.vo.DeviceInfoVo;
import com.huaxia.tongyong.vo.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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


    @Resource
    DeviceInfoBiz deviceInfoBiz;
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
        JsonResult jsonResult = new JsonResult();
        try{
            deviceInfoBiz.addDeviceInfo(deviceInfoParam);

        }catch(RuntimeException re){
            log.error("新增设备信息失败：{}", re.getStackTrace());
            jsonResult.setCode(0);
            jsonResult.setMessage(re.getMessage());
        }
        catch(Exception e){
            log.error("新增设备信息失败：{}", e.getStackTrace());
            jsonResult.setCode(0);
            jsonResult.setMessage("新增设备信息失败");
        }
        return jsonResult;
    }

    @ApiOperation(value="查询设备信息",notes = "查询设备信息",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping(value="/get")
    public JsonResult<PageInfo<DeviceInfoVo>> getDeviceList(
            @RequestParam("pageNum")Integer pageNum,
            @RequestParam("pageSize")Integer pageSize,
            @RequestParam("deviceName")String deviceName
    ){

        JsonResult jsonResult = new JsonResult();
        try{
            if(pageNum==null){
                pageNum=1;
            }
            if(pageSize==null){
                pageSize=10;
            }
            PageHelper.startPage(pageNum,pageSize);
            List<DeviceInfoVo> deviceInfoVoList = deviceInfoBiz.getDeviceList(deviceName);
            if(CollectionUtils.isNotEmpty(deviceInfoVoList)){
                PageInfo<DeviceInfoVo> deviceInfoVoPageInfo = new PageInfo<>(deviceInfoVoList);
                jsonResult.setData(deviceInfoVoPageInfo);
            }
            else{
                jsonResult.setCode(0);
                jsonResult.setData(new PageInfo<>(new ArrayList<>()));
                jsonResult.setMessage("未查询到设备信息");
            }

        }catch(RuntimeException re){
            log.error("查询设备信息失败：{}", re.getStackTrace());
            jsonResult.setCode(0);
            jsonResult.setMessage(re.getMessage());
        }
        catch(Exception e){
            log.error("查询设备信息失败：{}", e.getStackTrace());
            jsonResult.setCode(0);
            jsonResult.setMessage("查询设备信息失败");
        }
        return jsonResult;
    }

    @ApiOperation(value="更新设备信息",notes = "更新设备信息",httpMethod = "POST",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PutMapping(value="/update")
    public JsonResult updateDeviceInfo(
            @RequestBody DeviceInfoParam deviceInfoParam
    ){
        JsonResult jsonResult = new JsonResult();
        try{
            deviceInfoBiz.updateDeviceInfo(deviceInfoParam);

        }catch(RuntimeException re){
            log.error("更新设备信息失败：{}", re.getStackTrace());
            jsonResult.setCode(0);
            jsonResult.setMessage(re.getMessage());
        }
        catch(Exception e){
            log.error("更新设备信息失败：{}", e.getStackTrace());
            jsonResult.setCode(0);
            jsonResult.setMessage("更新设备信息失败");
        }
        return jsonResult;
    }
    @ApiOperation(value="删除设备信息",notes = "删除设备信息",httpMethod = "POST",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @DeleteMapping(value="/del")
    public JsonResult delDeviceInfo(
            Integer id
    ){
        JsonResult jsonResult = new JsonResult();
        try{

            deviceInfoBiz.delDeviceInfo(id);

        }catch(RuntimeException re){
            log.error("更新设备信息失败：{}", re.getStackTrace());
            jsonResult.setCode(0);
            jsonResult.setMessage(re.getMessage());
        }
        catch(Exception e){
            log.error("更新设备信息失败：{}", e.getStackTrace());
            jsonResult.setCode(0);
            jsonResult.setMessage("更新设备信息失败");
        }
        return jsonResult;
    }

    @ApiOperation(value="获取设备详细信息",notes = "查询设备详细信息",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping(value="/detail")
    public JsonResult<DeviceInfoVo> getDeviceDetialInfo(
            @RequestParam("id")int id
    ){
        JsonResult jsonResult = new JsonResult();
        try{
            DeviceInfoVo deviceInfoVo =deviceInfoBiz.getDeviceDetail(id);
            jsonResult.setData(deviceInfoVo);
            if(deviceInfoVo==null){
                jsonResult.setCode(0);
                jsonResult.setMessage("未查询到该设备详情信息");
            }
        }catch(RuntimeException re){
            log.error("获取设备信息详情失败：{}", re.getStackTrace());
            jsonResult.setCode(0);
            jsonResult.setMessage(re.getMessage());
        }
        catch(Exception e){
            log.error("获取设备信息详情失败：{}", e.getStackTrace());
            jsonResult.setCode(0);
            jsonResult.setMessage("获取设备信息详情失败");
        }
        return jsonResult;
    }
}
