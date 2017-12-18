package com.huaxia.tongyong.repository;

import com.huaxia.tongyong.model.ReportRepair;
import com.huaxia.tongyong.vo.ReportRepairVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


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
}