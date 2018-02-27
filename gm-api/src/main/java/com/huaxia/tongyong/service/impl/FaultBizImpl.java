package com.huaxia.tongyong.service.impl;

import com.huaxia.tongyong.enums.ReportTypeEnum;
import com.huaxia.tongyong.model.ReportFault;
import com.huaxia.tongyong.model.ReportInfo;
import com.huaxia.tongyong.model.UserInfo;
import com.huaxia.tongyong.param.FaultParam;
import com.huaxia.tongyong.repository.ReportFaultMapper;
import com.huaxia.tongyong.repository.ReportInfoMapper;
import com.huaxia.tongyong.repository.ReportRepairMapper;
import com.huaxia.tongyong.service.FaultBiz;
import com.huaxia.tongyong.util.date.DateUtil;
import com.huaxia.tongyong.util.json.JSONHelper;
import com.huaxia.tongyong.vo.ReportFaultVo;
import com.huaxia.tongyong.vo.ReportInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.dozer.DozerBeanMapper;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @author: gongdaoshun
 * @Date: 2017/12/19 22:25
 * @className: FaultBizImpl
 */
@Service
@Slf4j
public class FaultBizImpl implements FaultBiz{

    @Autowired
    private ReportFaultMapper reportFaultMapper;

    @Autowired
    private DozerBeanMapper dozerBeanMapper;

    @Autowired
    private ReportInfoMapper reportInfoMapper;

    @Override
    public ReportFaultVo getReportFaultVo(Long reportId, Long id) {
        //1.更新为已读
        reportInfoMapper.updateForRead(reportId);
        return reportFaultMapper.selectReportFaultVo(reportId,id);
    }

    @Override
    public Integer saveReportFault(FaultParam faultParam) {
        //1.获取当前登录用户信息
        UserInfo userInfo = JSONHelper.jsonToObject(MDC.get("user"),UserInfo.class);
        //2.保存日报主体信息
        ReportInfo reportInfo = transferReportInfo(faultParam);
        reportInfo.setChargeUserId(userInfo.getId());
        reportInfo.setChargeUserName(userInfo.getName());
        reportInfo.setCreateUserId(userInfo.getId());
        reportInfo.setCreateUserName(userInfo.getName());
        reportInfo.setCreateTime(new Date());
        reportInfoMapper.insertSelective(reportInfo);

        ReportFault reportFault = transferFault(faultParam);
        reportFault.setReportId(reportInfo.getId());
        reportFault.setCreateTime(new Date());
        return reportFaultMapper.insertSelective(reportFault);
    }

    @Override
    public Integer updateReportFault(FaultParam faultParam) {
        //1.转换对应的日报信息,并更新日报信息
        ReportInfo reportInfo = transferReportInfo(faultParam);
        reportInfoMapper.updateByPrimaryKeySelective(reportInfo);
        //2.更新故障日报信息
        ReportFault reportFault = transferFault(faultParam);
        return reportFaultMapper.updateByPrimaryKeySelective(reportFault);
    }

    @Override
    public Integer deleteReportFault(Long reportId) {
        //1.删除对应的日报主体信息
        Integer count =reportInfoMapper.updateForDelete(reportId);
        if(count!=1){
            throw new RuntimeException("当前日报信息已删除");
        }
        count = reportFaultMapper.updateForDelete(reportId);
        if(count!=1){
            throw new RuntimeException("当前日报信息已删除");
        }
        return count;
    }

    /**
     * 下载故障日报表
     * @param reportStatus
     * @param startTime
     * @param endTime
     * @param request
     * @param response
     */
    @Override
    public void getReportFaultVoList(Integer reportStatus, String startTime, String endTime, HttpServletRequest request, HttpServletResponse response) {
        //1.查询对应故障日报数据
        List<ReportFaultVo> reportFaultVos = reportFaultMapper.selectReportFaultVoListForExcel(reportStatus,startTime,endTime);
        //2.组装报表表头字段
        Workbook wb = new HSSFWorkbook();

        //设置合并的单元格
        CellRangeAddress tableNameRange = new CellRangeAddress(0,0,0,5);
        Sheet sheet =wb.createSheet("设备历史故障记录表");
        sheet.addMergedRegion(tableNameRange);

        //创建表格的表名
        Row tableNameRow =sheet.createRow(0);
        Cell tableNameCell = tableNameRow.createCell(0);
        tableNameCell.setCellValue(new HSSFRichTextString("设备历史故障记录表"));

        //创建表格的抬头
        Row titleRow = sheet.createRow(1);
        Cell serinalNo = titleRow.createCell(0);
        serinalNo.setCellValue("序号");
        Cell deviceName = titleRow.createCell(1);
        deviceName.setCellValue("设备名称");
        Cell deviceFault = titleRow.createCell(2);
        deviceFault.setCellValue("设备故障");
        Cell dealMethod = titleRow.createCell(3);
        dealMethod.setCellValue("处理方法");
        Cell areaName = titleRow.createCell(4);
        areaName.setCellValue("区域");
        Cell stopTime = titleRow.createCell(5);
        stopTime.setCellValue("停线时间");

        int rowIndex =2;
        for(ReportFaultVo reportFaultVo:reportFaultVos){
            Row row = sheet.createRow(rowIndex);
            Cell serinalNoCell = row.createCell(0);
            serinalNoCell.setCellValue(reportFaultVo.getReportId());
            Cell deviceNameCell = row.createCell(1);
            deviceNameCell.setCellValue(reportFaultVo.getDeviceName());
            Cell deviceFaultCell = row.createCell(2);
            deviceFaultCell.setCellValue(reportFaultVo.getDeviceFault());
            Cell dealMethodCell = row.createCell(3);
            dealMethodCell.setCellValue(reportFaultVo.getDealMethod());
            Cell areaNameCell = row.createCell(4);
            areaNameCell.setCellValue(reportFaultVo.getAreaName());
            Cell stopTimeCell = row.createCell(5);
            stopTimeCell.setCellValue(reportFaultVo.getStopTime());
            rowIndex++;
        }
        try{
            //下载修复验证excel报表
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        }catch (Exception e){
            log.error("导出设备历史故障记录表失败：",e);
        }
    }


    /**
     * 转换参数数据
     * @param faultParam
     * @return
     */
    private ReportFault transferFault(FaultParam faultParam){
        ReportFault reportFault = dozerBeanMapper.map(faultParam,ReportFault.class);
        return reportFault;
    }

    private ReportInfo transferReportInfo(FaultParam faultParam){
        ReportInfo reportInfo = new ReportInfo();
        reportInfo.setReportStatus(faultParam.getReportStatus());
        reportInfo.setReportType(ReportTypeEnum.FAULT.getCode());
        reportInfo.setReportName(faultParam.getDeviceName());
        reportInfo.setId(faultParam.getReportId());
        return reportInfo;
    }

}
