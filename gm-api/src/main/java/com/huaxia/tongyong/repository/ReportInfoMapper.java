package com.huaxia.tongyong.repository;

import com.huaxia.tongyong.model.ReportInfo;
import com.huaxia.tongyong.vo.ReportInfoVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.boot.actuate.endpoint.AutoConfigurationReportEndpoint;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 
 * ReportInfoMapper数据库操作接口类
 * 
 **/
@Repository
public interface ReportInfoMapper{

    /**
     * 查询日报信息
     * @param reportStatus
     * @return
     */
    public List<ReportInfoVo> getReportInfoVoList(
            @Param("reportStatus")Integer reportStatus
    );

    /**
     * 新增日报记录
     * @param reportInfo
     * @return
     */
    public Integer insertSelective(ReportInfo reportInfo);


    /**
     * 更新日报信息
     * @param reportInfo
     * @return
     */
    public Integer updateByPrimaryKeySelective(ReportInfo reportInfo);
}