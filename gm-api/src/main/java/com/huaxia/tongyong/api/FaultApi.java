package com.huaxia.tongyong.api;

import com.huaxia.tongyong.param.FaultParam;
import com.huaxia.tongyong.vo.JsonResult;
import com.huaxia.tongyong.vo.ReportFaultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 故障日报相关接口
 * @author: gongdaoshun
 * @Date: 2017/12/10 22:35
 * @className: FaultController
 */
@RestController
@RequestMapping("/v1/fault")
@Slf4j
@Api(value = "FaultController", description = "故障日报相关的api")
public class FaultApi {

    /**
     * 新增日报记录
     * @param faultParam
     * @return
     */
    @ApiOperation(value="新增故障日报记录")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public JsonResult addFault(@ApiParam @RequestBody FaultParam faultParam){
        return null;
    }

    /**
     * 获取故障日报信息
     * @param reportId
     * @return
     */
    @ApiOperation(value="根据日报列表的日报id获取故障日报的信息")
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public JsonResult<ReportFaultVo> getFault(
            @RequestParam("reportId")Long reportId
    ){
        return null;
    }

    /**
     * 更新故障日报信息
     * @param faultParam
     * @return
     */
    @ApiOperation(value="更新故障日报的信息")
    @RequestMapping(value ="/update",method = RequestMethod.POST)
    public JsonResult updateFault(@ApiParam @RequestBody FaultParam faultParam){
        return null;
    }

    /**
     * 删除故障日报信息
     * @param id
     * @return
     */
    @ApiOperation(value="删除故障日报的信息")
    @RequestMapping(value="/delete",method = RequestMethod.DELETE)
    public JsonResult deleteFault(
            @RequestParam("id") Long id
    ){
        return null;
    }


    /**
     * 根据区域、开始时间、结束时间下载区间时间内该区域的故障信息
     * @param areaId
     * @param startTime
     * @param endTime
     * @param response
     */
    @ApiOperation("下载对应的故障日报信息")
    @RequestMapping(value = "/download",method = RequestMethod.GET)
    public void downloadFaultReport(
            @RequestParam("areaId")Long areaId,
            @RequestParam("startTime")String startTime,
            @RequestParam("endTime")String endTime,
            HttpServletResponse response
    ){

    }
}
