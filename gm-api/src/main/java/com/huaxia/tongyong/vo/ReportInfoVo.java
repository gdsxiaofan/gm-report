package com.huaxia.tongyong.vo;

import lombok.Data;

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
    String reportTypeCode;
}
