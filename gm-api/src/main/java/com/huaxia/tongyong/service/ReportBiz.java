package com.huaxia.tongyong.service;

import com.github.pagehelper.PageInfo;
import com.huaxia.tongyong.param.ReportQueryParams;
import com.huaxia.tongyong.vo.ReportInfoVo;

/**
 * @Description:
 * @author: gongdaoshun
 * @Date: 2017/12/15 21:21
 * @className: ReportBiz
 */
public interface ReportBiz {

    /**
     * 获取日报信息列表
     * @param reportQueryParams
     * @return
     */
    public PageInfo<ReportInfoVo> selectReportInfoVoList(ReportQueryParams reportQueryParams);

    /**
     * 交接日报信息
     * @param userId
     * @param idStr
     * @return
     */
    public boolean transferReport(Long userId,String idStr);
}