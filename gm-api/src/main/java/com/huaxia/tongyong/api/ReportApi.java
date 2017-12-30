package com.huaxia.tongyong.api;

import com.github.pagehelper.PageInfo;
import com.huaxia.tongyong.param.ReportQueryParams;
import com.huaxia.tongyong.service.ReportBiz;
import com.huaxia.tongyong.vo.JsonResult;
import com.huaxia.tongyong.vo.ReportInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 日报相关接口定义
 * @author: gongdaoshun
 * @Date: 2017/12/13 22:14
 * @className: ReportController
 */
@Data
@RestController
@RequestMapping("/v1/report")
@Api(value="ReportController",description = "日报相关接口定义")
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
            HttpServletResponse response
    ){

    }

}
