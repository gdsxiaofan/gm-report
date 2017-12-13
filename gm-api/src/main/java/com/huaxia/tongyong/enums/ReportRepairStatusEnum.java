package com.huaxia.tongyong.enums;

import lombok.Getter;

/**
 * @Description: 日报修复验证确认状态枚举
 * @author: gongdaoshun
 * @Date: 2017/12/13 22:34
 * @className: ReportRepairStatusEnum
 */
public enum ReportRepairStatusEnum {
    UN_REPAIR(1,"未修复"),
    REPAIRED(2,"已修复");

    @Getter
    int code;
    @Getter
    String name;

    ReportRepairStatusEnum(int code,String name){
        this.code = code;
        this.name = name;
    }

    public static ReportRepairStatusEnum valuesOf(int code){
        for(ReportRepairStatusEnum reportRepairStatusEnum:ReportRepairStatusEnum.values()){
            if(reportRepairStatusEnum.getCode() == code){
                return reportRepairStatusEnum;
            }
        }
        return null;
    }
}
