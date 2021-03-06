package com.huaxia.tongyong.api;

import com.huaxia.tongyong.enums.ReportStatusEnum;
import com.huaxia.tongyong.model.ReportFault;
import com.huaxia.tongyong.param.FaultParam;
import com.huaxia.tongyong.service.FaultBiz;
import com.huaxia.tongyong.util.date.DateUtil;
import com.huaxia.tongyong.vo.JsonResult;
import com.huaxia.tongyong.vo.ReportFaultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.Date;

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

    @Autowired
    private FaultBiz faultBiz;
    /**
     * 新增日报记录
     * @param faultParam
     * @return
     */
    @ApiOperation(value="新增故障日报记录")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public JsonResult addFault(@ApiParam @RequestBody FaultParam faultParam){

        Integer count =faultBiz.saveReportFault(faultParam);

        if(count!=1){
            return  new JsonResult(0,"保存故障日报信息失败",null);
        }
        return new JsonResult();
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
        JsonResult<ReportFaultVo> jsonResult = new JsonResult<>();
        ReportFaultVo reportFaultVo = faultBiz.getReportFaultVo(reportId,null);
        jsonResult.setData(reportFaultVo);
        if(reportFaultVo==null){
            jsonResult.setCode(0);
            jsonResult.setMessage("未查询到故障日报信息");
        }
        return jsonResult;
    }

    /**
     * 更新故障日报信息
     * @param faultParam
     * @return
     */
    @ApiOperation(value="更新故障日报的信息")
    @RequestMapping(value ="/update",method = RequestMethod.POST)
    public JsonResult updateFault(@ApiParam @RequestBody FaultParam faultParam){

        Integer count =faultBiz.updateReportFault(faultParam);

        if(count!=1){
            return  new JsonResult(0,"更新故障日报信息失败",null);
        }
        return new JsonResult();
    }

    /**
     * 删除故障日报信息
     * @param reportId
     * @return
     */
    @ApiOperation(value="删除故障日报的信息")
    @RequestMapping(value="/delete",method = RequestMethod.DELETE)
    public JsonResult deleteFault(
            @ApiParam("reportId,故障日报对应的日报主体id") @RequestParam("reportId") Long reportId
    ){
        Integer count =faultBiz.deleteReportFault(reportId);

        if(count!=1){
            return  new JsonResult(0,"删除故障日报信息失败",null);
        }
        return new JsonResult();
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
            @RequestParam("startTime")String startTime,
            @RequestParam("endTime")String endTime,
            HttpServletResponse response,
            HttpServletRequest request
    ){
        try {
            String userAgent = request.getHeader("User-Agent");

            String fileName= "设备历史故障记录表"+ DateUtil.dateToString(new Date(),DateUtil.SHORTFMT1)+".xls";


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
            faultBiz.getReportFaultVoList(ReportStatusEnum.SUBMIT.getCode(),startTime,endTime,request,response);

        } catch (Exception e) {
            log.error("下载设备历史故障记录表失败", e);
        }
    }
}
