package com.huaxia.tongyong.service.impl;

import com.huaxia.tongyong.enums.ReportRepairStatusEnum;
import com.huaxia.tongyong.enums.ReportTypeEnum;
import com.huaxia.tongyong.model.ReportInfo;
import com.huaxia.tongyong.model.ReportRepair;
import com.huaxia.tongyong.model.UserInfo;
import com.huaxia.tongyong.param.RepairParam;
import com.huaxia.tongyong.repository.ReportInfoMapper;
import com.huaxia.tongyong.repository.ReportRepairMapper;
import com.huaxia.tongyong.service.RepairBiz;
import com.huaxia.tongyong.util.date.DateUtil;
import com.huaxia.tongyong.util.json.JSONHelper;
import com.huaxia.tongyong.vo.ReportRepairVo;
import io.jsonwebtoken.lang.Assert;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
 * @Description: 故障日报信息相关业务接口
 * @author: gongdaoshun
 * @Date: 2017/12/16 22:08
 * @className: RepairBizImpl
 */
@Service
@Slf4j
public class RepairBizImpl implements RepairBiz {

    @Autowired
    private ReportRepairMapper reportRepairMapper;

    @Autowired
    private ReportInfoMapper reportInfoMapper;

    @Autowired
    private DozerBeanMapper dozerBeanMapper;

    /**
     * 根据日报id获取故障日报信息
     *
     * @param reportId
     * @return
     */
    @Override
    public ReportRepairVo getReportRepairVo(Long reportId) {
        //1.校验参数
        Assert.notNull(reportId, "reportId不能为空");
        //2.更新为已读
        reportInfoMapper.updateForRead(reportId);
        return reportRepairMapper.selectReportRepairVoByReportIdOrId(reportId, null);
    }

    /**
     * 保存故障日报信息
     *
     * @param repairParam
     */
    @Override
    public void saveReportRepair(RepairParam repairParam) {
        //1.组装日报主体信息
        ReportInfo reportInfo = transferToReportInfo(repairParam);
        reportInfo.setReportType(ReportTypeEnum.REPAIR.getCode());
        reportInfoMapper.insertSelective(reportInfo);
        //2.组装故障日报信息
        ReportRepair reportRepair = dozerBeanMapper.map(repairParam,ReportRepair.class);
        reportRepair.setReportId(reportInfo.getId());
        reportRepairMapper.insertSelective(reportRepair);
    }

    /**
     * 更新故障日报信息
     *
     * @param repairParam
     */
    @Override
    public void updateReportRepair(RepairParam repairParam) {
        //1.组装日报主体信息
        ReportInfo reportInfo = transferToReportInfo(repairParam);
        reportInfo.setReportType(ReportTypeEnum.REPAIR.getCode());
        String userInfoStr = MDC.get("user");
        if(StringUtils.isBlank(userInfoStr)){
            throw new RuntimeException("当前用户未登录，请登录后在操作");
        }
        reportInfo.setCreateTime(new Date());
        UserInfo userInfo = JSONHelper.jsonToObject(userInfoStr, UserInfo.class);
        reportInfo.setCreateUserId(userInfo.getId());
        reportInfo.setCreateUserName(userInfo.getName());
        reportInfo.setChargeUserId(userInfo.getId());
        reportInfo.setCreateUserName(userInfo.getName());
        reportInfoMapper.updateByPrimaryKeySelective(reportInfo);
        //2.组装故障日报信息
        ReportRepair reportRepair = dozerBeanMapper.map(repairParam,ReportRepair.class);
        reportRepair.setReportId(reportInfo.getId());
        reportRepairMapper.updateByPrimaryKeySelective(reportRepair);
    }

    /**
     * 根据参数获取故障日报信息
     *
     * @param reportStatus
     * @param deviceId
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public List<ReportRepairVo> selectReportRepairVoList(Integer reportStatus, Integer deviceId, String startTime, String endTime) {
        return reportRepairMapper.selectReportRepairVoForExcel(reportStatus,deviceId,startTime,endTime);
    }

    @Override
    public void deleteRepair(Long reportId) {
        reportInfoMapper.updateForDelete(reportId);
        reportRepairMapper.updateForDelte(reportId);
    }

    /**
     *
     * @param deviceId
     * @param startTime
     * @param endTime
     * @param response
     * @param request
     */
    @Override
    public void exportExcel(Integer deviceId, String startTime, String endTime, HttpServletResponse response, HttpServletRequest request) {
        //1.根据条件查询对应的数据
        List<ReportRepairVo> reportRepairVos = reportRepairMapper.selectReportRepairVoForExcel(ReportRepairStatusEnum.REPAIRED.getCode(),deviceId,startTime,endTime);

        //2.创建对应的表格数据
        Workbook wb = new HSSFWorkbook();

        //设置合并的单元格
        CellRangeAddress tableNameRange = new CellRangeAddress(0,0,0,8);
        Sheet sheet =wb.createSheet("总车间关键工艺设备修复表");
        sheet.addMergedRegion(tableNameRange);

        //创建表格的表名
        Row tableNameRow =sheet.createRow(0);
        Cell tableNameCell = tableNameRow.createCell(0);
        tableNameCell.setCellValue(new HSSFRichTextString("总车间关键工艺设备修复表"));

        //创建表格的抬头
        Row titleRow = sheet.createRow(1);
        Cell serinalNo = titleRow.createCell(0);
        serinalNo.setCellValue("序号");
        Cell device = titleRow.createCell(1);
        device.setCellValue("维修保养设备");
        Cell repairComment = titleRow.createCell(2);
        repairComment.setCellValue("维修保养内容");
        Cell changePart = titleRow.createCell(3);
        changePart.setCellValue("更换部件");
        Cell checkMetod = titleRow.createCell(4);
        checkMetod.setCellValue("验证措施");
        Cell calibratedVolume = titleRow.createCell(5);
        calibratedVolume.setCellValue("标定体积");
        Cell repairStatus = titleRow.createCell(6);
        repairStatus.setCellValue("状态确认");
        Cell repairUser = titleRow.createCell(7);
        repairUser.setCellValue("维修人员");
        Cell repairCarNo = titleRow.createCell(8);
        repairCarNo.setCellValue("修复后首车号");

        //处理表格数据
        int rowIndex =2;
        for(ReportRepairVo reportRepairVo:reportRepairVos){
            Row row = sheet.createRow(rowIndex);
            Cell serinalNoCell = row.createCell(0);
            serinalNoCell.setCellValue(reportRepairVo.getId());
            Cell deviceCell = row.createCell(1);
            deviceCell.setCellValue(reportRepairVo.getDeviceName());
            Cell repairCommentCell = row.createCell(2);
            repairCommentCell.setCellValue(reportRepairVo.getRepairComment());
            Cell changePartCell = row.createCell(3);
            changePartCell.setCellValue(reportRepairVo.getRepairUserName());
            Cell checkMetodCell = row.createCell(4);
            checkMetodCell.setCellValue(reportRepairVo.getValidateMethod());
            Cell calibratedVolumeCell = row.createCell(5);
            calibratedVolumeCell.setCellValue(reportRepairVo.getCalibratedVolume());
            Cell repairStatusvCell = row.createCell(6);
            repairStatusvCell.setCellValue(reportRepairVo.getRepairStatusName());
            Cell repairUserCell = row.createCell(7);
            repairUserCell.setCellValue(reportRepairVo.getRepairUserName());
            Cell repairCarNoCell = row.createCell(8);
            repairCarNoCell.setCellValue(reportRepairVo.getRepairCarNo());

            rowIndex++;
        }
        try{
            //下载修复验证excel报表
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        }catch (Exception e){
            log.error("导出修复验证excel报表失败：",e);
        }
    }

    /**
     * 转换故障日报信息
     *
     * @param repairParam
     * @return
     */
    private ReportInfo transferToReportInfo(RepairParam repairParam) {
        ReportInfo reportInfo = new ReportInfo();
        reportInfo.setId(repairParam.getReportId());

        reportInfo.setReportStatus(repairParam.getReportStatus());

        return reportInfo;
    }

}
