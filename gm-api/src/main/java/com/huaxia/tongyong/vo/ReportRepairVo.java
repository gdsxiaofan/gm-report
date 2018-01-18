package com.huaxia.tongyong.vo;

import com.huaxia.tongyong.enums.ReportRepairStatusEnum;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @Description: 故障日报的信息
 * @author: gongdaoshun
 * @Date: 2017/12/13 22:42
 * @className: ReportRepairVo
 */
@Data
public class ReportRepairVo {
    /****/
    private Long id;

    /**日报信息表id**/
    private Long reportId;

    /**维修保养设备**/
    private String deviceName;

    private Integer deviceId;

    /**维修保养设备**/
    private String repairComment;

    /**更换部件**/
    private String replaceParts;

    /**验证措施**/
    private String validateMethod;

    /**标定体积**/
    private String calibratedVolume;

    /**状态确认：1，未修复，2，已修复**/
    private Integer repairStatus;

    /**
     * 状态名称
     * @see com.huaxia.tongyong.enums.ReportRepairStatusEnum
     */
    private String repairStatusName;

    private String repairUserName;

    /**修复后首车号**/
    private String repairCarNo;

    public void setRepairStatus(Integer repairStatus){
        this.repairStatus = repairStatus;
        this.repairStatusName = ReportRepairStatusEnum.valuesOf(repairStatus).getName();
    }

    public void setRepairStatusName(String repairStatusName){
        if(StringUtils.isBlank(this.repairStatusName)){
            this.repairStatusName = repairStatusName;
        }
    }

}
