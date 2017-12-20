package com.huaxia.tongyong.repository;

import com.huaxia.tongyong.model.ReportRepair;
import com.huaxia.tongyong.vo.ReportRepairVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


/**
 * 
 * ReportRepairMapper数据库操作接口类
 * 
 **/
@Mapper
public interface ReportRepairMapper{


    /**
     * 根据日报id和故障id查询故障日报信息
     * @param reportId
     * @param id
     * @return
     */
    public ReportRepairVo selectReportRepairVoByReportIdOrId(
            @Param("reportId")Long reportId,
            @Param("id")Long id
    );

    /**
     * 保存故障日报信息
     * @param reportRepair
     * @return
     */
    public Integer insertSelective(ReportRepair reportRepair);

    /**
     * 更新故障日报信息
     * @param reportRepair
     * @return
     */
    public Integer updateByPrimaryKeySelective(ReportRepair reportRepair);

    /**
     * 根据日报状态，区域id,开始时间，结束时间查询相关的日报信息
     * @param reportStatus
     * @param deviceId
     * @param startTime
     * @param endTime
     * @return
     */
    public List<ReportRepairVo> selectReportRepairVoForExcel(
            @Param("reportStatus")Integer reportStatus,
            @Param("deviceId") Integer deviceId,
            @Param("startTime") String startTime,
            @Param("endTime") String endTime);

    /**
     * 删除对应的日报信息
     * @param reportId
     * @return
     */
    public Integer updateForDelte(
            @Param("reportId")Long reportId
    );
}