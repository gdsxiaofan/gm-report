package com.huaxia.tongyong.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huaxia.tongyong.model.ReportInfo;
import com.huaxia.tongyong.model.ReportTransferLog;
import com.huaxia.tongyong.model.UserInfo;
import com.huaxia.tongyong.param.ReportQueryParams;
import com.huaxia.tongyong.repository.ReportInfoMapper;
import com.huaxia.tongyong.repository.ReportTransferLogMapper;
import com.huaxia.tongyong.repository.UserInfoMapper;
import com.huaxia.tongyong.service.ReportBiz;
import com.huaxia.tongyong.util.date.DateUtil;
import com.huaxia.tongyong.util.json.JSONHelper;
import com.huaxia.tongyong.vo.ReportInfoVo;
import io.jsonwebtoken.lang.Assert;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Description: 日报信息相关业务接口
 * @author: gongdaoshun
 * @Date: 2017/12/15 22:07
 * @className: ReportBizImpl
 */
@Service
@Slf4j
public class ReportBizImpl implements ReportBiz {

    @Autowired
    private ReportInfoMapper reportInfoMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private ReportTransferLogMapper reportTransferLogMapper;

    /**
     * 查询对应的日报数据
     * @param reportQueryParams
     * @return
     */
    @Override
    public PageInfo<ReportInfoVo> selectReportInfoVoList(ReportQueryParams reportQueryParams) {
        //1.设置分页
        PageHelper.startPage(reportQueryParams.getPageNum(),reportQueryParams.getPageSize());
        PageHelper.orderBy("id desc");
        UserInfo userInfo = JSONHelper.jsonToObject(MDC.get("user"),UserInfo.class);
        List<ReportInfoVo> reportInfoVoList = reportInfoMapper.getReportInfoVoList(reportQueryParams.getReportStatus(),userInfo.getId().intValue());

        //2.当前的查询数据为空，则直接返回null
//        if(CollectionUtils.isEmpty(reportInfoVoList)){
//            return null;
//        }
        PageInfo<ReportInfoVo> pageInfo = new PageInfo<>(reportInfoVoList);
        return pageInfo;
    }

    /**
     * 交接日报信息
     * @param userId
     * @param idStr
     * @return
     */
    @Override
    public boolean transferReport(Long userId, String idStr) {
         //1.校验参数
        Assert.notNull(userId,"请选中对应的交接人");
        UserInfo userIno = userInfoMapper.selectByPrimaryKey(userId);

        //2.查询出所有被移交数据
        List<String> idList = Arrays.asList(idStr.split(","));
        List<ReportInfo> reportInfos = reportInfoMapper.selectReportInfoList(idList);

        // 3.记录所有交接日志
        reportInfos.forEach(reportInfo -> {
            ReportTransferLog reportTransferLog = new ReportTransferLog();
            reportTransferLog.setBeforeUserId(reportInfo.getChargeUserId());
            reportTransferLog.setBeforeUserName(reportInfo.getChargeUserName());
            reportTransferLog.setAfterUserId(userIno.getId());
            reportTransferLog.setAfterUserName(userIno.getName());
            reportTransferLog.setReportId(reportInfo.getId());
            reportTransferLog.setCreateTime(new Date());
            reportTransferLogMapper.insertSelective(reportTransferLog);


            //更新的当前日报的负责人
            reportInfo.setChargeUserId(userIno.getId());
            reportInfo.setChargeUserName(userIno.getName());
            reportInfo.setReadStatus(0);
            reportInfoMapper.updateByPrimaryKeySelective(reportInfo);
        });

        return true;
    }

    /**
     * 导出移交记录excel报表
     * @param response
     * @param request
     * @param startTime
     * @param endTime
     */
    @Override
    public void exportExcelForTransfer(HttpServletResponse response, HttpServletRequest request, String startTime, String endTime) {
        //1.根据参数查询对应的移交记录
        List<ReportTransferLog> reportTransferLogs = reportTransferLogMapper.selectReportTransferLogByDate(startTime,endTime);

        //2.开始导出对应的报表数据
        Workbook wb = new HSSFWorkbook();

        //设置合并的单元格
        CellRangeAddress tableNameRange = new CellRangeAddress(0,0,0,3);
        CellRangeAddress dateRange = new CellRangeAddress(1,1,0,3);
        Sheet sheet =wb.createSheet("日报移交报表");
        sheet.addMergedRegion(tableNameRange);
        sheet.addMergedRegion(dateRange);

        //创建表格的表名
        Row tableNameRow =sheet.createRow(0);
        Cell tableNameCell = tableNameRow.createCell(0);
        tableNameCell.setCellValue(new HSSFRichTextString("总车间维修工段交接报表"));
        //创建表格的时间行
        Row dateRow = sheet.createRow(1);
        Cell dateCell = dateRow.createCell(0);
        String dateNow = DateUtil.dateToString(new Date(),DateUtil.LONGFMT1);
        dateCell.setCellValue(new HSSFRichTextString(dateNow));
        //创建表格的抬头
        Row titleRow = sheet.createRow(2);
        Cell transferName = titleRow.createCell(0);
        transferName.setCellValue("交接内容");
        Cell fromUser = titleRow.createCell(1);
        fromUser.setCellValue("交班人");
        Cell toUser = titleRow.createCell(2);
        toUser.setCellValue("接班人");
        Cell transferTime = titleRow.createCell(3);
        transferTime.setCellValue("交接具体时间");

        //根据报表内容导出报表数据
        int rowIndex =3;
        for(ReportTransferLog reportTransferLog :reportTransferLogs){
            Row row = sheet.createRow(rowIndex);
            ReportInfo reportInfo = reportInfoMapper.selectByPrimaryKey(reportTransferLog.getReportId());
            row.createCell(0).setCellValue(reportInfo.getReportName());
            row.createCell(1).setCellValue(reportTransferLog.getBeforeUserName());
            row.createCell(2).setCellValue(reportTransferLog.getAfterUserName());
            row.createCell(3).setCellValue(DateUtil.dateToString(reportTransferLog.getCreateTime(),DateUtil.LONGFMT1));
            rowIndex++;
        }
        try{
            //下载excel
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();

        }catch (Exception e){
            log.error("导出交接报表失败：",e);
        }

    }

    @Override
    public Integer getNoReadCount(Long userId) {
        return reportInfoMapper.selectCountForNoRead(userId);
    }


}
