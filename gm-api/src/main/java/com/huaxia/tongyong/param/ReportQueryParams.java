package com.huaxia.tongyong.param;

import lombok.Data;

/**
 * @Description:
 * @author: gongdaoshun
 * @Date: 2017/12/13 22:19
 * @className: ReportQueryParams
 */
@Data
public class ReportQueryParams extends BaseQueryParams{
    /**
     * 日报类型，1：故障 2，修复验证
     * @see com.huaxia.tongyong.enums.ReportTypeEnum
     */
    private int reportType;

    /**
     * 日报状态
     * 1.已保存 2，已提交
     */
    private int reportStatus;
}
