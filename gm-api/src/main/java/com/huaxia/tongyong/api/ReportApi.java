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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

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
            @RequestParam("startTime")String endTime,
            HttpServletResponse response,
            HttpServletRequest request
    ){

        try {
            String userAgent = request.getHeader("User-Agent");

            String filename = "工单设备上传模板.xlsx";

            //针对IE或者以IE为内核的浏览器：
            if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
                filename = URLEncoder.encode(filename, "UTF-8");

            } else {
                //非IE浏览器的处理：
                filename = new String(filename.getBytes("UTF-8"), "ISO-8859-1");
            }
            InputStream is = this.getClass().getResourceAsStream("/template/工单设备上传模板.xlsx");

            // 设置response内容的类型
            response.setContentType("application/octet-stream;charset=utf-8");
            response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", filename));
            response.setCharacterEncoding("UTF-8");
            /**
             * 将要下载的文件内容通过输出流写回到浏览器
             */
            OutputStream os = response.getOutputStream();
            int len = -1;
            byte[] b = new byte[1024 * 100];
            while ((len = is.read(b)) != -1) {
                os.write(b, 0, len);
                os.flush();
            }
            os.close();
            is.close();
        } catch (Exception e) {
            log.error("获取设备上传模板失败", e);
        }
    }

}
