package com.huaxia.tongyong.api;

import com.huaxia.tongyong.param.RepairParam;
import com.huaxia.tongyong.service.RepairBiz;
import com.huaxia.tongyong.util.date.DateUtil;
import com.huaxia.tongyong.vo.JsonResult;
import com.huaxia.tongyong.vo.ReportRepairVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.Date;

/**
 * @Description:
 * @author: gongdaoshun
 * @Date: 2017/12/13 23:21
 * @className: RepairApi
 */
@Api(value = "RepairApi" ,description = "修复验证的接口定义")
@RestController
@RequestMapping("/v1/repair")
@Slf4j
public class RepairApi {

    @Autowired
    private RepairBiz repairBiz;

    @ApiOperation("新增修复验证日报信息")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public JsonResult addRepair(@RequestBody RepairParam repairParam){
        repairBiz.saveReportRepair(repairParam);

        return new JsonResult();
    }


    @ApiOperation("获取修复验证的日报信息")
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public JsonResult<ReportRepairVo> getRepair(
            @RequestParam("reportId")Long reportId
    ){
        JsonResult<ReportRepairVo> jsonResult = new JsonResult<>();
        ReportRepairVo reportFaultVo = repairBiz.getReportRepairVo(reportId);
        jsonResult.setData(reportFaultVo);
        if(reportFaultVo==null){
            jsonResult.setCode(0);
            jsonResult.setMessage("未查询到修复验证日报信息");
        }
        return jsonResult;
    }

    @ApiOperation("更新修复验证日报信息")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public JsonResult updateRepair(@RequestBody RepairParam repairParam){

        repairBiz.updateReportRepair(repairParam);

        return new JsonResult();
    }

    @ApiOperation("删除修复验证日报信息")
    @RequestMapping(value="/delete",method = RequestMethod.DELETE)
    public JsonResult deleteRepair(
            @RequestParam("reportId")Long reportId
    ){
        repairBiz.deleteRepair(reportId);
        return new JsonResult();
    }

    @ApiOperation("根据条件下载对应的日报数据")
    @RequestMapping(value = "/download",method = RequestMethod.GET)
    public void downloadRepairReport(
            @RequestParam("deviceId")Integer deviceId,
            @RequestParam("startTime")String startTime,
            @RequestParam("endTime")String endTime,
            HttpServletResponse response,
            HttpServletRequest request
    ){
        try {
            String userAgent = request.getHeader("User-Agent");

            String fileName= "总装维修设备历史故障记录表"+ DateUtil.dateToString(new Date(),DateUtil.SHORTFMT1)+".xls";


            //针对IE或者以IE为内核的浏览器：
            if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
                fileName = URLEncoder.encode(fileName, "UTF-8");

            } else {
                //非IE浏览器的处理：
                fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
            }

            // 设置response内容的类型
            response.setContentType("application/octet-stream;charset=utf-8");
            response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", fileName));
            response.setCharacterEncoding("UTF-8");
            repairBiz.exportExcel(deviceId,startTime,endTime,response,request);

        } catch (Exception e) {
            log.error("下载总装维修设备历史故障记录表失败", e);
        }
    }
}
