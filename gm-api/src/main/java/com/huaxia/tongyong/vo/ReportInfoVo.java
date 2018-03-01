package com.huaxia.tongyong.vo;

import com.huaxia.tongyong.enums.ReportTypeEnum;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @Description: 日报数据列表数据
 * @author: gongdaoshun
 * @Date: 2017/12/13 22:08
 * @className: ReportInfoVo
 */
@Data
public class ReportInfoVo {
    /**
     * 日报id
     */
    Long reportId;
    /**
     * 日报名称
     */
    String reportName;
    /**
     * 创建时间
     */
    String createTime;
    /**
     * 日报类型文案
     */
    String reportTypeName;
    /**
     * 日报类型code
     */
    Integer reportTypeCode;

    /**已读状态：1，已读 0 未读**/
    private Integer readStatus;

    private void setReportTypeCode(Integer reportTypeCode){
        this.reportTypeCode = reportTypeCode;
        this.reportTypeName = ReportTypeEnum.valuesOf(reportTypeCode).getName();
    }

    private void setReportTypeName(String reportTypeName){
        if(StringUtils.isBlank(this.reportTypeName)){
            this.reportTypeName = reportTypeName;
        }
    }
}
