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
import com.huaxia.tongyong.vo.ReportInfoVo;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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
        List<ReportInfoVo> reportInfoVoList = reportInfoMapper.getReportInfoVoList(reportQueryParams.getReportStatus());

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
            reportTransferLogMapper.insertSelective(reportTransferLog);
        });

        return true;
    }
}
