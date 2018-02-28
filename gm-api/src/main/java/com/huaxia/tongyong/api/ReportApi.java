package com.huaxia.tongyong.api;

import com.github.pagehelper.PageInfo;
import com.huaxia.tongyong.handler.JwtHandler;
import com.huaxia.tongyong.param.ReportQueryParams;
import com.huaxia.tongyong.service.ReportBiz;
import com.huaxia.tongyong.util.date.DateUtil;
import com.huaxia.tongyong.util.jwt.JwtUtil;
import com.huaxia.tongyong.vo.JsonResult;
import com.huaxia.tongyong.vo.ReportInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.Date;

/**
 * @Description: 日报相关接口定义
 * @author: gongdaoshun
 * @Date: 2017/12/13 22:14
 * @className: ReportController
 */
@Data
@RequestMapping("/v1/report")
@Api(value="ReportController",description = "日报相关接口定义")
@Slf4j
@Controller
public class ReportApi {

    @Autowired
    private ReportBiz reportBiz;
    /**
     * 获取日报列表信息
     * @param reportQueryParams
     * @return
     */
    @ApiOperation("日报列表接口")
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<PageInfo<ReportInfoVo>> getReportList(@RequestBody ReportQueryParams reportQueryParams){
        //1.查询日报信息
        PageInfo<ReportInfoVo> reportInfoVoPageInfo = reportBiz.selectReportInfoVoList(reportQueryParams);
        JsonResult<PageInfo<ReportInfoVo>> jsonResult = new JsonResult<>();
        jsonResult.setData(reportInfoVoPageInfo);
        return jsonResult;
    }

    /**
     *
     * @param userId
     * @param reportId
     * @return
     */
    @ResponseBody
    @ApiOperation("移交日报信息")
    @RequestMapping(value = "/transfer",method = RequestMethod.POST)
    public JsonResult transferReport(
            @RequestParam("userId")Long userId,
            @ApiParam("reportIds，多条移交时，id以逗号隔开")@RequestParam("reportIds")String reportId
    ){
        //1.查询日报信息
        boolean flag = reportBiz.transferReport(userId,reportId);

        return new JsonResult();
    }

    @ApiOperation("下载日报移交记录")
    @RequestMapping(value = "/download",method = RequestMethod.GET)
    public void downloadTransferLog(
            @RequestParam("startTime")String startTime,
            @RequestParam("endTime")String endTime,
            HttpServletResponse response,
            HttpServletRequest request
    ){

        try {
            String userAgent = request.getHeader("User-Agent");

            String fileName= "总车间维修工段交接报表"+ DateUtil.dateToString(new Date(),DateUtil.SHORTFMT1);


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
            reportBiz.exportExcelForTransfer(response,request,startTime,endTime);

        } catch (Exception e) {
            log.error("获取设备上传模板失败", e);
        }
    }

    /**
     * 获取当前用户所有未读的信息
     * @return
     */
    @ApiOperation("获取当前用户未读消息的数量")
    @RequestMapping(value = "/unread/count",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult<Integer> getNoReadCount(
            HttpServletRequest request
    ){
        String jwt = JwtHandler.getCookieJWT(request);
        Integer count = reportBiz.getNoReadCount(Long.valueOf(JwtUtil.parseJwt2Id(jwt)));
        return new JsonResult<>(1,"success",count);
    }

}
