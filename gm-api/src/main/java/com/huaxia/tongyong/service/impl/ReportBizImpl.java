package com.huaxia.tongyong.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huaxia.tongyong.param.ReportQueryParams;
import com.huaxia.tongyong.repository.ReportInfoMapper;
import com.huaxia.tongyong.service.ReportBiz;
import com.huaxia.tongyong.vo.ReportInfoVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.ServiceMode;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 日报信息相关业务接口
 * @author: gongdaoshun
 * @Date: 2017/12/15 22:07
 * @className: ReportBizImpl
 */
@Service
public class ReportBizImpl implements ReportBiz {

    @Autowired
    private ReportInfoMapper reportInfoMapper;

    /**
     * 查询对应的日报数据
     * @param reportQueryParams
     * @return
     */
    @Override
    public PageInfo<ReportInfoVo> selectReportInfoVoList(ReportQueryParams reportQueryParams) {
        //1.设置分页
        PageHelper.startPage(reportQueryParams.getPageNum(),reportQueryParams.getPageSize());
        List<ReportInfoVo> reportInfoVoList = reportInfoMapper.getReportInfoVoList(reportQueryParams.getReportStatus());

        //2.当前的查询数据为空，则直接返回null
        if(CollectionUtils.isEmpty(reportInfoVoList)){
            return null;
        }
        PageInfo<ReportInfoVo> pageInfo = new PageInfo<>(reportInfoVoList);
        return pageInfo;
    }
}
