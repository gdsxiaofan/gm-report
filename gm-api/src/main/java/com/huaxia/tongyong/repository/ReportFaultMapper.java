package com.huaxia.tongyong.repository;

import com.huaxia.tongyong.model.ReportFault;
import com.huaxia.tongyong.model.ReportRepair;
import com.huaxia.tongyong.vo.ReportFaultVo;
import com.huaxia.tongyong.vo.ReportRepairVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 *
 * ReportFaultMapper数据库操作接口类
 *
 **/
@Mapper
public interface ReportFaultMapper{

    /**
     * 根据reportId，id获取日报信息
     * @param reportId
     * @param id
     * @return
     */
    public ReportFaultVo selectReportFaultVo(
            @Param("reportId")Long reportId,
            @Param("id")Long id
    );

    /**
     * 保存故障日报记录
     * @param reportFault
     * @return
     */
    public Integer insertSelective(ReportFault reportFault);


    /**
     * 更新对应的日报记录
     * @param reportFault
     * @return
     */
    public Integer updateByPrimaryKeySelective(ReportFault reportFault);

    /**
     * 删除对应的日报信息
     * @param reportId
     * @return
     */
    public Integer updateForDelete(
            @Param("reportId")Long reportId
    );

    /**
     * 根据日报状态，时间来查询需要导出故障日报记录数据
     * @param reportStatus
     * @param startTime
     * @param endTime
     * @return
     */
    public List<ReportFaultVo> selectReportFaultVoListForExcel(
            @Param("reportStatus")Integer reportStatus,
            @Param("startTime") String startTime,
            @Param("endTime")String endTime);
}